package mvp.wyyne.douban.moviedouban.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by XXW on 2017/6/20.
 */

public class BitMapUtils {
    public static BitMapUtils mUtils;
    private Context mContext;

    private BitMapUtils(Context context) {
        mContext = context;
    }

    public static BitMapUtils getInstace(Context context) {
        if (mUtils == null) {
            mUtils = new BitMapUtils(context);
        }
        return mUtils;
    }

    /**
     * 获取bitmap
     *
     * @param id 资源id
     * @return bitmap
     */
    public Bitmap getBitmap(int id) {
        return BitmapFactory.decodeResource(mContext.getResources(), id);
    }

//    public Bitmap getNetWorkBitmap(final String url) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                URL fileUrl = null;
//                Bitmap bitmap = null;
//
//                try {
//                    fileUrl = new URL(url);
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                }
//
//                try {
//                    HttpURLConnection conn = (HttpURLConnection) fileUrl
//                            .openConnection();
//                    conn.setDoInput(true);
//                    conn.connect();
//                    InputStream is = conn.getInputStream();
//                    bitmap = BitmapFactory.decodeStream(is);
//                    is.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                return bitmap;
//            }
//
//        }).start();
//        return null;
//    }


}
