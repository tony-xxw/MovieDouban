package mvp.wyyne.douban.moviedouban.utils;

import android.app.Activity;

import androidx.annotation.ColorInt;
import androidx.core.view.ViewCompat;
import androidx.appcompat.widget.Toolbar;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import io.reactivex.annotations.NonNull;

/**
 * 4.4 状态栏处理
 *
 * @author Wynne
 * @date 2018/6/21
 */

public class StatusKitkatUtils {
    /**
     * 假的状态栏TAG
     */
    private static final String TAG_FAKE_STATUS_BAR_VIEW = "statusBarView";

    private static final String TAG_MARGIN_ADDED = "marginAdded";

    public static void setStatusBarColor(@NonNull Activity activity, @ColorInt int color) {
        Window window = activity.getWindow();
        //设置Window透明状态栏
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        ViewGroup mContentView = (ViewGroup) window.findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = mContentView.getChildAt(0);
        int statusBarHeight = StatusUtils.getStatusBarHeight(activity);

        removeFakeStatusBarViewIfExist(activity);
        addFakeStatusBarView(activity, color, statusBarHeight);
        addMarginTop(mChildView, statusBarHeight);

        if (mChildView != null) {
            ViewCompat.setFitsSystemWindows(mChildView, false);
        }
    }

    /**
     * 通过设置MarginTop 来模仿FitsSystemWindow true 效果
     *
     * @param mChildView
     * @param statusBarHeight
     */
    private static void addMarginTop(View mChildView, int statusBarHeight) {
        if (mChildView == null) {
            return;
        }

        //如果没有设置过TAG  来确定是否设置过MarginTop
        if (!TAG_MARGIN_ADDED.equals(mChildView.getTag())) {
            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mChildView.getLayoutParams();
            lp.topMargin += statusBarHeight;
            mChildView.setLayoutParams(lp);
            mChildView.setTag(TAG_MARGIN_ADDED);
        }
    }

    private static View addFakeStatusBarView(Activity activity, int color, int statusBarHeight) {
        Window window = activity.getWindow();
        //获取decorView
        ViewGroup mDecorView = (ViewGroup) window.getDecorView();
        //创建状态栏
        View mStatusBarView = new View(activity);
        //设置属性
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight);
        layoutParams.gravity = Gravity.TOP;
        mStatusBarView.setLayoutParams(layoutParams);
        mStatusBarView.setBackgroundColor(color);
        mStatusBarView.setTag(TAG_FAKE_STATUS_BAR_VIEW);
        mDecorView.addView(mStatusBarView);

        return mStatusBarView;
    }

    /**
     * 删除假冒StatusView
     *
     * @param activity
     */
    private static void removeFakeStatusBarViewIfExist(Activity activity) {
        Window window = activity.getWindow();
        ViewGroup mDecorView = (ViewGroup) window.getDecorView();

        View fakeView = mDecorView.findViewWithTag(TAG_FAKE_STATUS_BAR_VIEW);
        if (fakeView != null) {
            mDecorView.removeView(fakeView);
        }
    }


    public static void translucentStatusBar(Activity activity) {
        Window window = activity.getWindow();
        //设置透明状态栏FLAG
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        ViewGroup mContentView = (ViewGroup) window.findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = mContentView.getChildAt(0);

        removeFakeStatusBarViewIfExist(activity);
        removeMarginTopIfContentChild(mChildView, StatusUtils.getStatusBarHeight(activity));

        if (mChildView != null) {
            ViewCompat.setFitsSystemWindows(mChildView, false);
        }
    }

    private static void removeMarginTopIfContentChild(View mChildView, int statusBarHeight) {
        if (mChildView == null) {
            return;
        }

        if (TAG_MARGIN_ADDED.equals(mChildView.getTag())) {
            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mChildView.getLayoutParams();
            lp.topMargin -= statusBarHeight;
            mChildView.setLayoutParams(lp);
            mChildView.setTag(null);
        }
    }


    static void setStatusBarColorForCollapsingToolbar(Activity activity, final AppBarLayout appBarLayout, final CollapsingToolbarLayout collapsingToolbarLayout,
                                                      Toolbar toolbar, int statusColor) {

    }
}
