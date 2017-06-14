package mvp.wyyne.douban.moviedouban.utils;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
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

    public TitleRecycleItemDecoration(Context context, List<MovieType> data) {
        super();
        mTitleHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, context.getResources().getDisplayMetrics());
        mData = data;

    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        Log.d("XXW", "onDraw");
    }


    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        Log.d("XXW", "onDrawOver");
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        if (position > -1) {
            if (position == 0) {
                Log.d("XXW","position=0");
                outRect.set(0, mTitleHeight, 0, 0);
            } else {
                Log.d("XXW","position>0");
                if (null != mData.get(position).getTags() && mData.get(position).getTags().equals(mData.get(position - 1).getTags())) {
                    outRect.set(0, mTitleHeight, 0, 0);
                } else {
                    outRect.set(0, 0, 0, 0);
                }
            }
        }
        Log.d("XXW", "getItemOffsets-----position---" + position + "-----");
    }
}
