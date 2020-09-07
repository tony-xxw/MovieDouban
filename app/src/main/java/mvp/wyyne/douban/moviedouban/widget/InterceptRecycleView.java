package mvp.wyyne.douban.moviedouban.widget;

import android.content.Context;

import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.recyclerview.widget.RecyclerView;

import javax.annotation.Nullable;

/**
 * @author Wynne
 * @date 2018/7/6
 */

public class InterceptRecycleView extends RecyclerView {
    public InterceptRecycleView(Context context) {
        super(context);
    }

    public InterceptRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public InterceptRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        return super.onTouchEvent(e);
    }
}
