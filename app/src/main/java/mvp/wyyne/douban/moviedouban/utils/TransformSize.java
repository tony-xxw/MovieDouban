package mvp.wyyne.douban.moviedouban.utils;

import android.content.Context;

/**
 * 尺寸转换
 *
 * @author Wynne
 * @date 2018/6/6
 */

public class TransformSize {


    public static int pxToDp(Context context, int px) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) ((px * scale) + 0.5f);
    }
}
