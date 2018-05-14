package mvp.wyyne.douban.moviedouban.utils;

import android.os.Environment;

/**
 * @author Wynne
 * @date 2018/5/14
 */

public class SdCardUtils {

    /**
     * @return 返回本地相册图片路径
     */
    public static String getPicturesPath() {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath();
    }
}
