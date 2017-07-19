package mvp.wyyne.douban.moviedouban.adapter;

import android.content.Context;

import java.util.List;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.bean.TvBean;

/**
 * Created by XXW on 2017/7/19.
 */

public class TvAdapter extends BaseRvAdapter<TvBean> {


    public TvAdapter(Context context, List<TvBean> data) {
        super(context, data);
    }

    @Override
    int getLayoutId() {
        return R.layout.item_stills_tv_layout;
    }

    @Override
    void bindView(BaseItemViewHolder holder, int position) {
        TvBean mBean = mList.get(position);

        if (mBean.getAvatar() != null) {

        } else {
            holder.setImgResource(R.id.iv_logo, mBean.getUrl());
            holder.setText(R.id.tv_tv, mBean.getTvName());
            holder.setText(R.id.tv_money, mBean.getMoney());
        }
    }

    @Override
    void bindHeadView(BaseItemViewHolder holder, int position) {

    }

    @Override
    void bindFooterView(BaseItemViewHolder holder, int position) {

    }
}
