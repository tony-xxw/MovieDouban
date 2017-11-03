package mvp.wyyne.douban.moviedouban.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.StatFs;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import mvp.wyyne.douban.moviedouban.R;


/**
 * Created by XXW on 2017/10/26.
 */

public class ImageLoader {

    private static final String TAG = "ImageLoader";
    private Context mContext;
    //图片缩放工具类
    private ImageResizer mResizer = new ImageResizer();
    //內存緩存
    private LruCache<String, Bitmap> mMemoryCache;
    //磁盤緩存
    private DiskLruCache mDiskLruCache;
    //磁盘缓存大小
    private static final long DISK_CACHE_SIZE = 1024 * 1024 * 50;
    //是否创建DisLruCache
    private boolean mIsDiskLruCacheCreated = false;
    //图片Id
    private static final int TAG_KEY_URI = R.id.imageloader_uri;

    private static final int DISK_CACHE_INDEX = 0;

    public static final int MESSAGE_POST_RESULT = 1;
    //CPU个数
    private static final int CPU_COUNT = Runtime.getRuntime()
            .availableProcessors();
    private static final int CORE_POOL_SIZE = CPU_COUNT + 1;
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
    private static final long KEEP_ALIVE = 10L;

    private static final int IO_BUFFER_SIZE = 8 * 1024;

    private static final ThreadFactory sThreadFactory = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        public Thread newThread(Runnable r) {
            return new Thread(r, "ImageLoader#" + mCount.getAndIncrement());
        }
    };


    public static final Executor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(
            CORE_POOL_SIZE, MAXIMUM_POOL_SIZE,
            KEEP_ALIVE, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(), sThreadFactory);


    private Handler mMainHandle = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            LoaderResult result = (LoaderResult) msg.obj;
            ImageView imageView = result.imageView;
            String uri = (String) imageView.getTag(TAG_KEY_URI);
            if (uri.equals(result.uri)) {
                imageView.setImageBitmap(result.bitmap);
            } else {
                Log.w(TAG, "set image bitmap,but url has changed, ignored!");
            }
        }
    };

    public static ImageLoader build(Context context) {
        return new ImageLoader(context);
    }


    public ImageLoader(Context context) {
        mContext = context.getApplicationContext();
        //获取进程大小
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        //缓存大小
        int cacheSize = maxMemory / 8;
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
            }
        };
        //创建磁盘缓存路径
        File diskCache = getDiskCacheDir(mContext, "bitmap");
        if (!diskCache.exists()) {
            diskCache.mkdirs();
        }
        if (getUsableSpace(diskCache) > DISK_CACHE_SIZE) {
            try {
                mDiskLruCache = DiskLruCache.open(diskCache, 1, 1, DISK_CACHE_SIZE);
                mIsDiskLruCacheCreated = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 获取磁盘空间可以用大小
     *
     * @param diskCache 磁盘缓存文件
     * @return
     */
    private long getUsableSpace(File diskCache) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            return diskCache.getUsableSpace();
        }
        //获取磁盘可用大小
        final StatFs statFs = new StatFs(diskCache.getPath());
        return statFs.getBlockSizeLong() * (long) statFs.getAvailableBlocks();
    }

    /**
     * 获取磁盘存储路径
     *
     * @param context
     * @param diskdirname 缓存名
     * @return
     */
    private File getDiskCacheDir(Context context, String diskdirname) {
        //判断是否有外部SD卡
        boolean externalStorageAvailable
                = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        final String diskCachepath;
        if (externalStorageAvailable) {
            diskCachepath = context.getExternalCacheDir().getPath();
        } else {
            diskCachepath = context.getCacheDir().getPath();
        }

        return new File(diskCachepath + File.separator + diskdirname);

    }

    public void bindBitmap(final String uri, final ImageView imageView) {
        bindBitmap(uri, imageView, 0, 0);
    }

    public void bindBitmap(final String url, final ImageView imageView, final int reqWidth, final int reqHeight) {
        imageView.setTag(TAG_KEY_URI, url);
        //从内存获取Bitmap
        final Bitmap bitmap = loadBitmapFromMemCache(url);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
            return;
        }

        Runnable loadBitmapTask = new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = loadBitmap(url, reqWidth, reqHeight);
                if (bitmap != null) {
                    LoaderResult result = new LoaderResult(imageView, url, bitmap);
                    mMainHandle.obtainMessage(MESSAGE_POST_RESULT, result).sendToTarget();
                }
            }
        };

        THREAD_POOL_EXECUTOR.execute(loadBitmapTask);

    }


    private Bitmap loadBitmap(String url, int reqWidth, int reqHeight) {
        // 1.首先从内存中获取
        Bitmap bitmap = loadBitmapFromMemCache(url);
        if (bitmap != null) {
            Log.d(TAG, "loadBitmapFromMemCache,url:" + url);
            return bitmap;
        }
        //2.从磁盘获取
        try {
            bitmap = loadBitmapFromDiskCache(url, reqWidth, reqHeight);
            if (bitmap != null) {
                Log.d(TAG, "loadBitmapFromDisk,url:" + url);
                return bitmap;
            }
            bitmap = loadBitmapFromHttp(url, reqWidth, reqHeight);
            Log.d(TAG, "loadBitmapFromHttp,url:" + url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (bitmap == null && !mIsDiskLruCacheCreated) {
            Log.w(TAG, "encounter error, DiskLruCache is not created.");
            bitmap = downloadBitmapFromUrl(url);
        }
        return bitmap;
    }

    private Bitmap loadBitmapFromHttp(String url, int reqWidth, int reqHeight) throws IOException {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new RuntimeException("can not visit network from UI Thread.");
        }
        if (mDiskLruCache == null) {
            return null;
        }
        String key = hashKeyFormUrl(url);
        DiskLruCache.Editor editor = mDiskLruCache.edit(key);
        if (editor != null) {
            OutputStream outputStream = editor.newOutputStream(DISK_CACHE_INDEX);
            if (downloadUrlToStream(url, outputStream)) {
                editor.commit();
            } else {
                editor.abort();
            }
            mDiskLruCache.flush();
        }
        return loadBitmapFromDiskCache(url, reqWidth, reqHeight);
    }


    private Bitmap downloadBitmapFromUrl(String urlString) {
        Bitmap bitmap = null;
        HttpURLConnection urlConnection = null;
        BufferedInputStream in = null;

        try {
            final URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream(),
                    IO_BUFFER_SIZE);
            bitmap = BitmapFactory.decodeStream(in);
        } catch (final IOException e) {
            Log.e(TAG, "Error in downloadBitmap: " + e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            IOUtils.close(in);
        }
        return bitmap;
    }

    private boolean downloadUrlToStream(String urlString, OutputStream outputStream) {
        HttpURLConnection urlConnection = null;
        BufferedOutputStream out = null;
        BufferedInputStream in = null;

        try {
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream(), IO_BUFFER_SIZE);
            out = new BufferedOutputStream(outputStream);
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            return true;
        } catch (IOException e) {
            Log.e(TAG, "downloadBitmap failed." + e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            IOUtils.close(out);
            IOUtils.close(in);
        }

        return false;
    }

    private Bitmap loadBitmapFromDiskCache
            (String url, int reqWidth, int reqHeight) throws IOException {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Log.w(TAG, "load bitmap from UI Thread, it's not recommended!");
        }
        if (mDiskLruCache == null) {
            return null;
        }
        Bitmap bitmap = null;
        String key = hashKeyFormUrl(url);
        DiskLruCache.Snapshot snapshot = mDiskLruCache.get(key);
        if (snapshot != null) {
            FileInputStream inputStream = (FileInputStream) snapshot.getInputStream(DISK_CACHE_INDEX);
            FileDescriptor fileDescriptor = inputStream.getFD();
            bitmap = mResizer.decodeSampledBitmapFromFileDescriptor(fileDescriptor, reqWidth, reqHeight);
            if (bitmap != null) {
                addBitmapToMemoryCache(key, bitmap);
            }
        }
        return bitmap;
    }

    private void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    private Bitmap loadBitmapFromMemCache(String url) {
        //从MD中通过url获取对应bitmap的key
        final String key = hashKeyFormUrl(url);
        //通过key 去内存中获取Bitmap;
        Bitmap bitmap = getBitmapFromMemCache(key);
        return bitmap;
    }

    private Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }

    private String hashKeyFormUrl(String url) {
        String cachelKey;
        try {
            //获取MD5算法
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            //处理图片URL
            mDigest.update(url.getBytes());
            //完成哈希计算
            cachelKey = byteToHexString(mDigest.digest());

        } catch (NoSuchAlgorithmException e) {
            cachelKey = String.valueOf(url.hashCode());
        }
        return cachelKey;
    }

    private String byteToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0XFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');

            }
            sb.append(hex);
        }
        return sb.toString();
    }

    private static class LoaderResult {
        public ImageView imageView;
        public String uri;
        public Bitmap bitmap;

        public LoaderResult(ImageView imageView, String uri, Bitmap bitmap) {
            this.imageView = imageView;
            this.uri = uri;
            this.bitmap = bitmap;
        }
    }

}
