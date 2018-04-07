package mvp.wyyne.douban.moviedouban.adapter;

import android.content.Context;

import java.util.List;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.bean.Article;
import mvp.wyyne.douban.moviedouban.api.bean.PopularCm;
import mvp.wyyne.douban.moviedouban.api.bean.User;

/**
 *
 * @author XXW
 * @date 2017/6/24
 */

public class CommentAdapter extends BaseRvAdapter<PopularCm> {
    private User mUser;
    private PopularCm mPopularCm;
    protected Article mArticle;

    public void setArticle(Article article) {
        mArticle = article;
    }


    public CommentAdapter(Context context, List<PopularCm> data) {
        super(context, data);
    }


    @Override
    int getLayoutId() {
        return R.layout.item_comment_layout;
    }

    @Override
    void bindView(BaseItemViewHolder holder, int position) {
        mUser = mList.get(position).getAuthor();
        mPopularCm = mList.get(position);
        holder.setImgUrl(R.id.iv_avatars, mUser.getAvatar());
        holder.setText(R.id.tv_comment_name, mUser.getName());
        holder.setValue(R.id.rb_comment, mPopularCm.getRating().getValue());
        holder.setText(R.id.tv_comment_star_num, String.valueOf(mPopularCm.getUseful_count()));
        holder.setText(R.id.tv_comment, mPopularCm.getContent());
        holder.setText(R.id.tv_comment_date, mList.get(position).getCreated_at());


    }



    @Override
    void bindHeadView(BaseItemViewHolder holder, int position) {
        if (position == 0) {
            holder.setText(R.id.tv_head_comment, "短评");
        }
    }

    @Override
    public void bindFooterView(BaseItemViewHolder holder, int position) {
        if (position == getItemCount() - 1) {
            holder.setText(R.id.tv_footer, "全部短评" + mArticle.getComments_count() + "个");
        }
    }

}
