package mvp.wyyne.douban.moviedouban.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import android.util.TypedValue;

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
        return ContextCompat.getColor(context, color);
    }

    public static Drawable getContextDrawable(int drwableId, Context context) {
        return ContextCompat.getDrawable(context, drwableId);
    }

    public static Drawable getDrawable(int drawableId, Context context) {
        Drawable drawable = context.getResources().getDrawable(drawableId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        return drawable;
    }

    public static int pxToDp(Context context, int px) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) ((px * scale) + 0.5f);
    }

    /**
     * sp转px
     */
    public static int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }
}
