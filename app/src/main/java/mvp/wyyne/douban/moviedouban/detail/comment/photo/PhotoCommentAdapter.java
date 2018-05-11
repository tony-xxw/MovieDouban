package mvp.wyyne.douban.moviedouban.detail.comment.photo;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.adapter.BaseItemViewHolder;
import mvp.wyyne.douban.moviedouban.adapter.BaseRvAdapter;
import mvp.wyyne.douban.moviedouban.api.bean.Reviews;

/**
 * @author Wynne
 * @date 2018/5/8
 */

public class PhotoCommentAdapter extends BaseRvAdapter<Reviews> {
    private Context mContext;

    public PhotoCommentAdapter(Context context, List<Reviews> data) {
        super(context, data);
        mContext = context;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_photo_comment;
    }

    @Override
    public void bindView(BaseItemViewHolder holder, int position) {
        ImageView avatar = holder.getView(R.id.riv_avatar);
        TextView tvContent = holder.getView(R.id.tv_content);
        TextView tvUserName = holder.getView(R.id.tv_user);
        TextView tvCreateTime = holder.getView(R.id.tv_date);
        String avatarUrl = mList.get(position).getAuthor().getAvatar();
        String tvContentStr = mList.get(position).getContent();
        String tvUserNameStr = mList.get(position).getAuthor().getName();
        String tvCreateTimeStr = mList.get(position).getCreated_at();
        if (!TextUtils.isEmpty(avatarUrl)) {
            Glide.with(mContext).load(avatarUrl).into(avatar);

        }
        if (!TextUtils.isEmpty(tvContentStr)) {
            tvContent.setText(tvContentStr);
        }
        if (!TextUtils.isEmpty(tvUserNameStr)) {
            tvUserName.setText(tvUserNameStr);
        }
        if (!TextUtils.isEmpty(tvCreateTimeStr)) {
            tvCreateTime.setText(tvCreateTimeStr);
        }


    }

    @Override
    public void bindHeadView(BaseItemViewHolder holder, int position) {

    }

    @Override
    public void bindFooterView(BaseItemViewHolder holder, int position) {

    }
}
