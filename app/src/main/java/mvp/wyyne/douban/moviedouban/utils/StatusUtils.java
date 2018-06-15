package mvp.wyyne.douban.moviedouban.utils;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import mvp.wyyne.douban.moviedouban.R;

/**
 * 状态栏工具类
 *
 * @author Wynne
 * @date 2018/4/24
 */

public class StatusUtils {


    /**
     * 设置状态栏颜色
     *
     * @param activity  activity
     * @param isOneself 是否改变状态字体
     * @param color     状态栏颜色
     */
    public static void setStatusBarActivity(Activity activity, boolean isOneself, int color) {
        int option;
        Log.d("XXW", "color -->" + color);
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //4.4设置透明状态栏
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            View decorView = activity.getWindow().getDecorView();

            option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            activity.getWindow().setStatusBarColor(color);
        }

        if (!isOneself) {
            setStatusTextColor(activity);
        }
    }


    /**
     * 设置状态栏 图标 文字颜色
     *
     * @param activity
     */
    public static void setStatusTextColor(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    public static void setStatusTextWithColor(Activity activity, boolean lightStatusBar) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = activity.getWindow().getDecorView();
            int ui = decor.getSystemUiVisibility();
            if (lightStatusBar) {
                ui |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            } else {
                ui &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            }
            decor.setSystemUiVisibility(ui);

        }
    }

    public static void setStatusColor(Activity activity, int color, boolean isLightStatus) {
        activity.getWindow().setStatusBarColor(color);
        setStatusTextWithColor(activity, isLightStatus);
    }


    public static void tabSwitch(boolean isOneself, Activity activity) {
        if (isOneself) {
            StatusUtils.setStatusColor(activity, Color.WHITE, true);
        } else {
            StatusUtils.setStatusColor(activity, R.color.color_green, true);
        }
    }


}
