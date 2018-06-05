package mvp.wyyne.douban.moviedouban.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.WindowManager;

/**
 * Dialog工具类
 *
 * @author Wynne
 * @date 2018/6/5
 */

public class DialogUtils {

    /**
     * 设置Dialog宽度
     *
     * @param dialog     dialog
     * @param context    activity
     * @param widthRatio 宽度占屏幕比例
     */
    public static void setDialogWidth(AlertDialog dialog, Activity context, double widthRatio) {
        WindowManager windowManager = context.getWindowManager();
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.width = (int) (windowManager.getDefaultDisplay().getWidth() * widthRatio);
        dialog.getWindow().setAttributes(layoutParams);
    }
}
