package mvp.wyyne.douban.moviedouban.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author Wynne
 * @date 2018/5/29
 */

public class GridRecycleItemDecoration extends RecyclerView.ItemDecoration {
    /**
     * 分割线的宽度
     */
    private int mDividerHeight;

    private Context mContext;

    private Paint mPaint;


    public GridRecycleItemDecoration(Context context, int dividerHeight, int dividerColor) {
        mContext = context;
        mDividerHeight = dividerHeight;
        mPaint = new Paint();
        mPaint.setColor(dividerColor);
    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        drawHorizontalDivider(c, parent);
        drawVerticalDivider(c, parent);
    }

    private void drawVerticalDivider(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int top = child.getTop() - params.topMargin;
            int bottom = child.getBottom() + params.bottomMargin;

            int left = 0;
            int right = 0;


        }
    }

    private void drawHorizontalDivider(Canvas c, RecyclerView parent) {

    }
}
