package mvp.wyyne.douban.moviedouban.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ScrollView;

/**
 *
 * @author XXW
 * @date 2017/6/22
 */

public class ObservableScrollView extends ScrollView {
    private ScrollViewListener mListener;

    public ObservableScrollView(Context context) {
        super(context);
    }

    public ObservableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ObservableScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public interface ScrollViewListener {
        void onScrollChange(ObservableScrollView scrollView, int x, int y, int oldx, int oldy);
    }

    public void setListener(ScrollViewListener listener) {
        Log.d("XXW", "onScrollChanged");
        mListener = listener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {

        super.onScrollChanged(l, t, oldl, oldt);
        Log.d("XXW", "onScrollChanged");
        if (mListener != null) {
            mListener.onScrollChange(this, l, t, oldl, oldt);
        }
    }




}
