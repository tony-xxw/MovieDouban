package mvp.wyyne.douban.moviedouban.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.support.annotation.ColorInt;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import io.reactivex.annotations.NonNull;
import mvp.wyyne.douban.moviedouban.R;

/**
 * 5.1以上的状态栏 工具类
 *
 * @author Wynne
 * @date 2018/6/21
 */
@TargetApi(21)
public class StatusLollipopUtils {


    public static void setStatusBarColor(@NonNull Activity activity, @ColorInt int color) {
        Window window = activity.getWindow();

        //清除透明状态栏才可以设置状态栏颜色
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //设置状态栏颜色 需要添加FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS Flag 可绘制模式
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(color);
        //设置系统UI可见
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);

        ViewGroup mContentView = (ViewGroup) window.findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = mContentView.getChildAt(0);

        if (mChildView != null) {
            //设置系统不自动扩充
            ViewCompat.setFitsSystemWindows(mChildView, false);
            //添加到Window
            ViewCompat.requestApplyInsets(mChildView);
        }
    }


    public static void translucentStatusBar(Activity activity, boolean hideStatusBarBackground) {
        Window window = activity.getWindow();
        //设置状态栏颜色 需要添加FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS Flag 可绘制模式
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (hideStatusBarBackground) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ResourcesUtils.getColor(R.color.transparent, activity));
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        }

        ViewGroup mContentView = (ViewGroup) window.findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = mContentView.getChildAt(0);

        if (mChildView != null) {
            //设置系统不自动扩充
            ViewCompat.setFitsSystemWindows(mChildView, false);
            //添加到Window
            ViewCompat.requestApplyInsets(mChildView);
        }
    }


}
