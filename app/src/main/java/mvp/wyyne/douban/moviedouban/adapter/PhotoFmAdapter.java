package mvp.wyyne.douban.moviedouban.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.bean.Photos;

/**
 * Created by XXW on 2017/7/8.
 */

public class PhotoFmAdapter extends BaseRvAdapter<Photos> {
    public static final String ALL = "all";
    public static final String SINGLE = "single";
    private TextView mAll;
    private RelativeLayout mSingle;

    public PhotoFmAdapter(Context context, List<Photos> data) {
        super(context, data);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_cast_article_fm;
    }

    @Override
    public void bindView(BaseItemViewHolder holder, final int position) {
        if (mList != null && position < 8) {
            if (position == 7) {
                mAll = holder.getView(R.id.iv_all);
                mAll.setVisibility(View.VISIBLE);
                mAll.setTag(position);
                mAll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mClick.onItemClick(position, ALL);
                    }
                });
            } else {
                holder.getView(R.id.iv_all).setVisibility(View.GONE);
                Log.d("XXW", "photo--" + mList.get(position).getImage());
                holder.setImgUrl(R.id.iv_cast, mList.get(position).getCover());
                mSingle = holder.getView(R.id.rl_onclick);
                mSingle.setTag(position);
                mSingle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mClick.onItemClick(position, SINGLE);
                    }
                });
            }
        }

    }

    @Override
    public void bindHeadView(BaseItemViewHolder holder, int position) {

    }

    @Override
    public void bindFooterView(BaseItemViewHolder holder, int position) {

    }


}
