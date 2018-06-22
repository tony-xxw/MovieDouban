package mvp.wyyne.douban.moviedouban.utils;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v7.widget.Toolbar;
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


    public static void translucentStatusBar(Activity activity, int color) {
        Window window = activity.getWindow();
        //设置状态栏颜色 需要添加FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS Flag 可绘制模式
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(color);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);


        ViewGroup mContentView = (ViewGroup) window.findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = mContentView.getChildAt(0);

        if (mChildView != null) {
            //设置系统不自动扩充
            ViewCompat.setFitsSystemWindows(mChildView, false);
            //添加到Window
            ViewCompat.requestApplyInsets(mChildView);
        }
    }

    static void setStatusBarColorForCollapsingToolbar(Activity activity, final AppBarLayout appBarLayout, final CollapsingToolbarLayout collapsingToolbarLayout,
                                                      Toolbar toolbar, final int statusColor) {
        final Window window = activity.getWindow();

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);

        ViewCompat.setOnApplyWindowInsetsListener(collapsingToolbarLayout, new OnApplyWindowInsetsListener() {
            @Override
            public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
                return insets;
            }
        });

        ViewGroup mContentView = (ViewGroup) window.findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {
            ViewCompat.setFitsSystemWindows(mChildView, false);
            ViewCompat.requestApplyInsets(mChildView);
        }

        ((View) appBarLayout.getParent()).setFitsSystemWindows(false);
        appBarLayout.setFitsSystemWindows(false);

        toolbar.setFitsSystemWindows(false);
        if (toolbar.getTag() == null) {
            CollapsingToolbarLayout.LayoutParams lp = (CollapsingToolbarLayout.LayoutParams) toolbar.getLayoutParams();
            int statusHeight = StatusUtils.getStatusBarHeight(activity);
            lp.height += statusHeight;
            toolbar.setLayoutParams(lp);
            toolbar.setPadding(toolbar.getPaddingLeft(), toolbar.getPaddingTop() + statusHeight,
                    toolbar.getPaddingRight(), toolbar.getPaddingBottom());
            toolbar.setTag(true);
        }

        CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams()).getBehavior();
        if (behavior != null && behavior instanceof AppBarLayout.Behavior) {
            int verticalOffset = ((AppBarLayout.Behavior) behavior).getTopAndBottomOffset();
            if (Math.abs(verticalOffset) > appBarLayout.getHeight() - collapsingToolbarLayout.getScrimVisibleHeightTrigger()) {
                window.setStatusBarColor(statusColor);
            } else {
                window.setStatusBarColor(Color.TRANSPARENT);
            }
        } else {
            window.setStatusBarColor(Color.TRANSPARENT);
        }

        collapsingToolbarLayout.setFitsSystemWindows(false);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (Math.abs(verticalOffset) > appBarLayout.getHeight() - collapsingToolbarLayout.getScrimVisibleHeightTrigger()) {
                    if (window.getStatusBarColor() != statusColor) {
                        startColorAnimation(window.getStatusBarColor(), statusColor, collapsingToolbarLayout.getScrimAnimationDuration(), window);
                    }
                } else {
                    if (window.getStatusBarColor() != Color.TRANSPARENT) {
                        startColorAnimation(window.getStatusBarColor(), Color.TRANSPARENT, collapsingToolbarLayout.getScrimAnimationDuration(), window);
                    }
                }
            }
        });
        collapsingToolbarLayout.getChildAt(0).setFitsSystemWindows(false);
        collapsingToolbarLayout.setStatusBarScrimColor(statusColor);
    }


    static void startColorAnimation(int startColor, int endColor, long duration, final Window window) {
        if (sAnimator != null) {
            sAnimator.cancel();
        }
        sAnimator = ValueAnimator.ofArgb(startColor, endColor)
                .setDuration(duration);
        sAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (window != null) {
                    window.setStatusBarColor((Integer) valueAnimator.getAnimatedValue());
                }
            }
        });
        sAnimator.start();
    }

    private static ValueAnimator sAnimator;


}
