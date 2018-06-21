package mvp.wyyne.douban.moviedouban.widget;

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

import mvp.wyyne.douban.moviedouban.utils.MovieType;

/**
 * @author XXW
 * @date 2017/6/14
 */

public class TitleRecycleItemDecoration extends RecyclerView.ItemDecoration {
    private int mTitleHeight;
    private List<MovieType> mData;
    private Paint mPaint;
    private Rect mBound;
    private static int COLOR_TITLE_BG = Color.parseColor("#FFF5F5F5");
    private static int COLOR_TITLE_FONT = Color.parseColor("#FF666666");
    private static int LEFT_DP = 0;


    public TitleRecycleItemDecoration(Context context, List<MovieType> data) {
        super();
        mTitleHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, context.getResources().getDisplayMetrics());
        mData = data;
        mBound = new Rect();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        LEFT_DP = (int) (context.getResources().getDisplayMetrics().density * 15);

    }

    public void setData(List<MovieType> data) {
        mData = data;
    }

    @Override
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(canvas, parent, state);
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams pams = (RecyclerView.LayoutParams) child.getLayoutParams();
            int position = pams.getViewLayoutPosition();
            if (position > -1) {
                //第一次
                if (position == 0) {
                    drawTitleArea(canvas, left, right, child, pams, position);

                } else {//其他的通过判断
                    if (null != mData.get(position).getTags() && !(mData.get(position).getTags().equals(mData.get(position - 1).getTags()))) {
                        //不为空 且跟前一个tag不一样了，说明是新的分类，也要title
                        drawTitleArea(canvas, left, right, child, pams, position);
                    } else {
                        //none
                    }
                }
            }
        }
    }


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

        mPaint.getTextBounds(mData.get(position).getTags(), 0, mData.get(position).getTags().length(), mBound);
        c.drawText(mData.get(position).getTags(), child.getPaddingLeft() + LEFT_DP, child.getTop() - params.topMargin - (mTitleHeight / 2 - mBound.height() / 2), mPaint);
    }


    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int pos = ((LinearLayoutManager) parent.getLayoutManager()).findFirstVisibleItemPosition();
        String tags = mData.get(pos).getTags();
        Log.d("XXW", "onDrawOver====" + mData.get(pos).getTags());
        Log.d("XXW", "onDrawOver==Next==" + mData.get(pos + 1).getTags());
        View child = parent.findViewHolderForLayoutPosition(pos).itemView;
        //定义一个flag，Canvas是否位移过的标志
        boolean flag = false;
        //防止数组越界（一般情况不会出现）
        if ((pos + 1) < mData.size()) {
            //当前第一个可见的Item的tag，不等于其后一个item的tag，说明悬浮的View要切换了
            if (null != tags && !tags.equals(mData.get(pos + 1).getTags())) {
                //当getTop开始变负，它的绝对值，是第一个可见的Item移出屏幕的距离，
                if (child.getHeight() + child.getTop() < mTitleHeight) {
                    //当第一个可见的item在屏幕中还剩的高度小于title区域的高度时，我们也该开始做悬浮Title的“交换动画”
                    c.save();//每次绘制前 保存当前Canvas状态，
                    flag = true;

                    //一种头部折叠起来的视效，个人觉得也还不错~
                    //可与123行 c.drawRect 比较，只有bottom参数不一样，由于 child.getHeight() + child.getTop() < mTitleHeight，所以绘制区域是在不断的减小，有种折叠起来的感觉
                    //c.clipRect(parent.getPaddingLeft(), parent.getPaddingTop(), parent.getRight() - parent.getPaddingRight(), parent.getPaddingTop() + child.getHeight() + child.getTop());

                    //类似饿了么点餐时,商品列表的悬停头部切换“动画效果”
                    //上滑时，将canvas上移 （y为负数） ,所以后面canvas 画出来的Rect和Text都上移了，有种切换的“动画”感觉
                    c.translate(0, child.getHeight() + child.getTop() - mTitleHeight);
                }
            }
        }
        mPaint.setColor(COLOR_TITLE_BG);
        c.drawRect(parent.getPaddingLeft(), parent.getPaddingTop(),
                parent.getRight() - parent.getPaddingRight(), parent.getPaddingTop() + mTitleHeight, mPaint);
        mPaint.setColor(COLOR_TITLE_FONT);
        mPaint.setTextSize(36);
        mPaint.getTextBounds(tags, 0, tags.length(), mBound);
        c.drawText(tags, child.getPaddingLeft() + LEFT_DP, parent.getPaddingTop() + mTitleHeight - (mTitleHeight / 2 - mBound.height() / 2), mPaint);
        if (flag) {
            c.restore();//恢复画布到之前保存的状态
        }
//        Log.d("XXW", "onDrawOver-------" + mTitleHeight + "----Bound----" + mBound.height());
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        Log.e("XXW", "getItemOffsets");
        int position = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        if (position > -1) {
            if (position == 0) {
                outRect.set(0, mTitleHeight, 0, 0);
            } else {
                if (null != mData.get(position).getTags() && mData.get(position).getTags().equals(mData.get(position - 1).getTags())) {
                    outRect.set(0, 0, 0, 0);
                } else {
                    Log.d("XXW", "position>0");
                    outRect.set(0, mTitleHeight, 0, 0);
                }
            }
        }
    }
}
