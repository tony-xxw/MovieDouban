package mvp.wyyne.douban.moviedouban.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;

import io.reactivex.annotations.NonNull;
import mvp.wyyne.douban.moviedouban.R;

/**
 * 状态栏工具类
 *
 * @author Wynne
 * @date 2018/4/24
 */

public class StatusUtils {


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


    @TargetApi(21)
    public static void setStatusColor(Activity activity, int color, boolean isLightStatus) {
        setStatusBarColor(activity, color, isLightStatus);
    }


    public static void tabSwitch(boolean isOneself, Activity activity) {
        if (isOneself) {
            StatusUtils.setStatusColor(activity, ResourcesUtils.getColor(R.color.white, activity), true);
        } else {
            StatusUtils.setStatusColor(activity, ResourcesUtils.getColor(R.color.color_green, activity), false);
        }
    }


    /**
     * 设置状态栏颜色
     */
    public static void setStatusBarColor(@NonNull Activity activity, @ColorInt int color, boolean textColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            StatusLollipopUtils.setStatusBarColor(activity, color);

            if (textColor) {
                setStatusTextColor(activity);
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            StatusKitkatUtils.setStatusBarColor(activity, color);
        }


    }


    /**
     * 设置状态栏 Collapsing
     */

    public static void setStatusMaterial(@android.support.annotation.NonNull Activity activity, AppBarLayout appBarLayout, CollapsingToolbarLayout collapsingToolbarLayout,
                                         Toolbar toolbar, @ColorInt int statusColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            StatusLollipopUtils.setStatusBarColorForCollapsingToolbar(activity, appBarLayout, collapsingToolbarLayout, toolbar, statusColor);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            StatusKitkatUtils.setStatusBarColorForCollapsingToolbar(activity, appBarLayout, collapsingToolbarLayout, toolbar, statusColor);
        }
    }

    /**
     * 设置状态栏 图片
     *
     * @param activity        上下文
     * @param isHideStatusBar 是否隐藏状态栏颜色
     */
    public static void setStatusImage(@NonNull Activity activity, boolean isHideStatusBar) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            StatusLollipopUtils.translucentStatusBar(activity, isHideStatusBar);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            StatusKitkatUtils.translucentStatusBar(activity);
        }

    }

    public static void setStatusImage(@NonNull Activity activity, int color, boolean textLight) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            StatusLollipopUtils.translucentStatusBar(activity, color);
            if (textLight) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    activity.getWindow().getDecorView().setSystemUiVisibility(
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                }
            }

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            StatusKitkatUtils.translucentStatusBar(activity);
        }

    }


    /**
     * 返回状态栏高度
     *
     * @param context 上下文
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            result = context.getResources().getDimensionPixelOffset(resId);
        }
        return result;
    }

}
