package mvp.wyyne.douban.moviedouban.utils;

import android.content.Context;

/**
 * 资源文件工具类
 *
 * @author Wynne
 * @date 2018/6/21
 */

public class ResourcesUtils {

    /**
     * 返回XML颜色
     *
     * @param color
     * @param context
     * @return
     */
    public static int getColor(int color, Context context) {
        return context.getResources().getColor(color);
    }
}
