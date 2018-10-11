package mvp.wyyne.douban.moviedouban.adapter;

import android.content.Context;

import java.util.List;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.bean.Article;
import mvp.wyyne.douban.moviedouban.api.bean.PopularCmRv;

/**
 * 评论Adapter
 *
 * @author XXW
 * @date 2017/6/28
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
    protected int getLayoutId() {
        return R.layout.item_reviews_layout;
    }

    @Override
    public void bindView(BaseItemViewHolder holder, int position) {
        if (position < 5 && position < mList.size()) {
            mPopularCm = mList.get(position);
            holder.setText(R.id.tv_reviews_title, mList.get(position).getTitle());
            holder.setText(R.id.tv_reviews_name, mList.get(position).getAuthor().getName());
            holder.setText(R.id.tv_reviews_summary, mList.get(position).getSummary());

            holder.getStartView(R.id.average).setStartMark(mPopularCm.getRating().getValue());

        }
    }


    @Override
    public void bindHeadView(BaseItemViewHolder holder, int position) {
        if (position == 0) {
            holder.setText(R.id.tv_head_comment, "影评");
        }
    }

    @Override
    public void bindFooterView(BaseItemViewHolder holder, int position) {
        if (position == getItemCount() - 1) {
            holder.setText(R.id.tv_footer, "全部短评" + mArticle.getReviews_count() + "个");
        }
    }
}
