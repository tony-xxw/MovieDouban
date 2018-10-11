package mvp.wyyne.douban.moviedouban.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.widget.Scroller;

/**
 * @author Wynne
 * @date 2018/7/6
 */

public class VerticalViewGroup extends ViewPager {
    private static final String TAG = "VerticalViewGroup";

    private int mChildrenSize;
    private int mChildWidth;
    private int mChildIndex;

    // 分别记录上次滑动的坐标
    private int mLastX = 0;
    private int mLastY = 0;
    // 分别记录上次滑动的坐标(onInterceptTouchEvent)
    private int mLastXIntercept = 0;
    private int mLastYIntercept = 0;

    private Scroller mScroller;
    private VelocityTracker mVelocityTracker;

    public VerticalViewGroup(Context context) {
        super(context);
    }

    public VerticalViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);

    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }
}
