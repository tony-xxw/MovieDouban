package mvp.wyyne.douban.moviedouban.utils;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import java.util.List;

import mvp.wyyne.douban.moviedouban.api.bean.Subjects;

/**
 * Created by XXW on 2017/6/14.
 */

public class TitleRecycleItemDecoration extends RecyclerView.ItemDecoration {
    private int mTitleHeight;
    private List<MovieType> mData;
    private Paint mPaint;
    private Rect mBound;
    private static int COLOR_TITLE_BG = Color.parseColor("#FFDFDFDF");
    private static int COLOR_TITLE_FONT = Color.parseColor("#FF000000");


    public TitleRecycleItemDecoration(Context context, List<MovieType> data) {
        super();
        mTitleHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, context.getResources().getDisplayMetrics());
        mData = data;
        mBound = new Rect();
        mPaint = new Paint();

    }
//
//    @Override
//    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
//        super.onDraw(c, parent, state);
//        final int left = parent.getPaddingLeft();
//        final int right = parent.getWidth() - parent.getPaddingRight();
//        final int childCount = parent.getChildCount();
//        for (int i = 0; i < childCount; i++) {
//            final View child = parent.getChildAt(i);
//            final RecyclerView.LayoutParams pams = (RecyclerView.LayoutParams) child.getLayoutParams();
//            int position = pams.getViewLayoutPosition();
//            if (position > -1) {
//                if (position == 0) {//等于0肯定要有title的
//                    drawTitleArea(c, left, right, child, pams, position);
//
//                } else {//其他的通过判断
//                    if (null != mData.get(position).getTags() && !mData.get(position).getTags().equals(mData.get(position - 1).getTags())) {
//                        //不为空 且跟前一个tag不一样了，说明是新的分类，也要title
//                        drawTitleArea(c, left, right, child, pams, position);
//                    } else {
//                        //none
//                    }
//                }
//            }
//        }
////        Log.d("XXW", "onDraw");
//    }


    /**
     * 绘制Title区域背景和文字的方法
     *
     * @param c
     * @param left
     * @param right
     * @param child
     * @param params
     * @param position
     */
    private void drawTitleArea(Canvas c, int left, int right, View child, RecyclerView.LayoutParams params, int position) {//最先调用，绘制在最下层
        mPaint.setColor(COLOR_TITLE_BG);
        c.drawRect(left, child.getTop() - params.topMargin - mTitleHeight, right, child.getTop() - params.topMargin, mPaint);
        mPaint.setColor(COLOR_TITLE_FONT);
/*
        Paint.FontMetricsInt fontMetrics = mPaint.getFontMetricsInt();
        int baseline = (getMeasuredHeight() - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;*/

        mPaint.getTextBounds(mData.get(position).getTags(), 0, mData.get(position).getTags().length(), mBound);
        c.drawText(mData.get(position).getTags(), child.getPaddingLeft(), child.getTop() - params.topMargin - (mTitleHeight / 2 - mBound.height() / 2), mPaint);
    }



    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int pos = ((LinearLayoutManager) parent.getLayoutManager()).findFirstVisibleItemPosition();
        String tags = mData.get(pos).getTags();
        View child = parent.findViewHolderForLayoutPosition(pos).itemView;
        mPaint.setColor(COLOR_TITLE_BG);
        c.drawRect(parent.getPaddingLeft(), parent.getPaddingTop(),
                parent.getRight() - parent.getPaddingRight(), parent.getPaddingTop() + mTitleHeight, mPaint);
        mPaint.setColor(COLOR_TITLE_FONT);
        mPaint.setTextSize(28);
        mPaint.getTextBounds(tags, 0, tags.length(), mBound);
        c.drawText(tags, child.getPaddingLeft(), parent.getPaddingTop() + mTitleHeight - (mTitleHeight / 2 - mBound.height() / 2), mPaint);
        Log.d("XXW", "onDrawOver-------" + mTitleHeight + "----Bound----" + mBound.height());
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        if (position > -1) {
            if (position == 0) {
//                Log.d("XXW", "position=0");
                outRect.set(0, mTitleHeight, 0, 0);
            } else {
                if (null != mData.get(position).getTags() && mData.get(position).getTags().equals(mData.get(position - 1).getTags())) {
                    outRect.set(0, mTitleHeight, 0, 0);
                } else {
                    Log.d("XXW", "position>0");
                    outRect.set(0, 0, 0, 0);
                }
            }
        }
//        Log.d("XXW", "getItemOffsets-----position---" + position + "-----");
    }
}
