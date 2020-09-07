package mvp.wyyne.douban.moviedouban.widget;

import android.content.Context;
import androidx.core.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author Wynne
 * @date 2018/7/5
 */

public class InterceptScrollView extends NestedScrollView {
    public InterceptScrollView(Context context) {
        super(context);
    }

    public InterceptScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InterceptScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        boolean intercept = false;
        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            intercept = true;
        }
//        super.onInterceptTouchEvent(ev);
        return intercept;
    }
}
