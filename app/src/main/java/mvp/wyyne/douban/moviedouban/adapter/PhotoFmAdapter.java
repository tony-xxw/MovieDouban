package mvp.wyyne.douban.moviedouban.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;

import java.util.List;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.bean.Photos;

/**
 * Created by XXW on 2017/7/8.
 */

public class PhotoFmAdapter extends BaseRvAdapter<Photos> implements View.OnClickListener {
    public PhotoFmAdapter(Context context, List<Photos> data) {
        super(context, data);
    }

    @Override
    int getLayoutId() {
        return R.layout.item_cast_article_fm;
    }

    @Override
    void bindView(BaseItemViewHolder holder, int position) {
        if (mList != null && position < 8) {
            if (position == 7) {
                holder.getView(R.id.iv_all).setVisibility(View.VISIBLE);
                holder.getView(R.id.iv_all).setOnClickListener(this);
            } else {
                holder.getView(R.id.iv_all).setVisibility(View.GONE);
                Log.d("XXW", "photo--" + mList.get(position).getImage());
                holder.setImgUrl(R.id.iv_cast, mList.get(position).getCover());
                holder.getView(R.id.iv_cast).setOnClickListener(this);
            }
        }

    }

    @Override
    void bindHeadView(BaseItemViewHolder holder, int position) {

    }

    @Override
    void bindFooterView(BaseItemViewHolder holder, int position) {

    }

    @Override
    public void onClick(View v) {

    }
}
