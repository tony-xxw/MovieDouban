package mvp.wyyne.douban.moviedouban.utils;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Field;

import mvp.wyyne.douban.moviedouban.R;

/**
 * 状态栏工具类
 *
 * @author Wynne
 * @date 2018/4/24
 */

public class StatusUtils {

    /**
     * 4.4改变状态栏颜色解决方案
     *
     * @param context
     */
    public static void setStastusBar(Activity context) {
        ViewGroup root = (ViewGroup) context.getWindow().getDecorView().findViewById(android.R.id.content);
        root.setPadding(0, getStatusBarHeight(context), 0, 0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            context.getWindow().setStatusBarColor(Color.WHITE);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //5.0 以上直接设置状态栏颜色
            context.getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else {
            //根布局添加占位状态栏
            ViewGroup decorView = (ViewGroup) context.getWindow().getDecorView();
            View statusBarView = new View(context);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    getStatusBarHeight(context));
            statusBarView.setBackgroundColor(Color.TRANSPARENT);
            decorView.addView(statusBarView, lp);
        }


    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        //获取状态栏高度的资源id
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static void setStatusBarActivity(Activity activity, boolean isOneself, int color) {
        int option;

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

//        hideActionBar(activity);
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


    public static void hideActionBar(Activity activity) {
        ActionBar actionBar = activity.getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }


}
