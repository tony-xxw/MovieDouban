package mvp.wyyne.douban.moviedouban.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.util.LruCache;
import android.view.View;

import com.bumptech.glide.disklrucache.DiskLruCache;

import java.io.File;
import java.io.IOException;


/**
 * Created by XXW on 2017/10/26.
 */

public class ImageLoader {

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
                = Environment.getExternalStorageDirectory().equals(Environment.MEDIA_MOUNTED);
        final String diskCachepath;
        if (externalStorageAvailable) {
            diskCachepath = context.getExternalCacheDir().getPath();
        } else {
            diskCachepath = context.getCacheDir().getPath();
        }

        return new File(diskCachepath + File.separator + diskdirname);

    }


}
