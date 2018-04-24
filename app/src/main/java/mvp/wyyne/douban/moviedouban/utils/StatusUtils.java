package mvp.wyyne.douban.moviedouban.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

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

    public static void setStatusBarActivity(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }


}
