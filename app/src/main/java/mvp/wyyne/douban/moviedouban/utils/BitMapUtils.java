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

    public Bitmap getNetWorkBitmap(String url) {
        Bitmap bm = null;
        try {
            URL iconUrl = new URL(url);
            URLConnection conn = iconUrl.openConnection();
            HttpURLConnection http = (HttpURLConnection) conn;

            int length = http.getContentLength();

            conn.connect();
            // 获得图像的字符流
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is, length);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();// 关闭流
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bm;
    }

}
