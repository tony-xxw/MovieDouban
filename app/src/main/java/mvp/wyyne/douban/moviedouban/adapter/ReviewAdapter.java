package mvp.wyyne.douban.moviedouban.adapter;

import android.content.Context;

import java.util.List;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.bean.Reviews;

/**
 * Created by XXW on 2017/6/28.
 */

public class ReviewAdapter extends BaseRvAdapter<Reviews> {

    public ReviewAdapter(Context context, List<Reviews> data) {
        super(context, data);
    }

    @Override
    int getLayoutId() {
        return R.layout.item_reviews_layout;
    }

    @Override
    void bindView(BaseItemViewHolder holder, int position) {
        holder.setText(R.id.tv_reviews_title, mList.get(position).getTitle());
    }

    @Override
    void bindHeadView(BaseItemViewHolder holder, int position) {

    }

    @Override
    void bindFooterView(BaseItemViewHolder holder, int position) {

    }
}
