package mvp.wyyne.douban.moviedouban.adapter;

import android.content.Context;

import java.util.List;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.bean.Trailers;

/**
 * Created by XXW on 2017/7/20.
 */

public class TvHeadAdapter extends BaseRvAdapter<Trailers> {

    public TvHeadAdapter(Context context, List<Trailers> data) {
        super(context, data);
    }

    @Override
    int getLayoutId() {
        return R.layout.item_stills_tv_head_layout;
    }

    @Override
    void bindView(BaseItemViewHolder holder, int position) {
        if (mList.get(position) != null) {
            Trailers trailers = mList.get(position);
            holder.setImgUrl(R.id.iv_still, trailers.getMedium());
            holder.setText(R.id.tv_still, trailers.getTitle());
        }
    }

    @Override
    void bindHeadView(BaseItemViewHolder holder, int position) {

    }

    @Override
    void bindFooterView(BaseItemViewHolder holder, int position) {

    }
}
