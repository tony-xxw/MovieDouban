package mvp.wyyne.douban.moviedouban.adapter;

import android.content.Context;
import android.util.Log;

import java.util.List;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.bean.Article;
import mvp.wyyne.douban.moviedouban.api.bean.PopularCm;
import mvp.wyyne.douban.moviedouban.api.bean.PopularCmRv;

/**
 * Created by XXW on 2017/6/28.
 */

public class ReviewAdapter extends BaseRvAdapter<PopularCmRv> {
    private PopularCmRv mPopularCm;

    public ReviewAdapter(Context context, List<PopularCmRv> data) {
        super(context, data);
    }

    protected Article mArticle;

    public void setArticle(Article article) {
        mArticle = article;
    }

    @Override
    int getLayoutId() {
        return R.layout.item_reviews_layout;
    }

    @Override
    void bindView(BaseItemViewHolder holder, int position) {
        Log.d("XXW", "size--" + mList.size());
        if (position < 5 && position < mList.size()) {
            mPopularCm = mList.get(position);
            Log.d("XXW", mPopularCm.toString());
            holder.setText(R.id.tv_reviews_title, mList.get(position).getTitle());
            holder.setText(R.id.tv_reviews_name, mList.get(position).getAuthor().getName());
            holder.setValue(R.id.rb_reviews_comment, mPopularCm.getRating().getValue());
            holder.setText(R.id.tv_reviews_summary, mList.get(position).getSummary());
            Log.d("XXW", "Title---------" + mList.get(position).getTitle());

        }
    }

    @Override
    public int getItemCount() {
        return mList.size() > 5 ? 7 : mList.size() + getFooterViewCount() + getHeadViewCount();
    }

    @Override
    void bindHeadView(BaseItemViewHolder holder, int position) {
        if (position == 0) {
            holder.setText(R.id.tv_head_comment, "影评");
        }
    }

    @Override
    void bindFooterView(BaseItemViewHolder holder, int position) {
        if (position == getItemCount() - 1) {
            holder.setText(R.id.tv_footer, "全部短评" + mArticle.getReviews_count() + "个");
        }
    }
}
