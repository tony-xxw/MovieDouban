package mvp.wyyne.douban.moviedouban.detail.comment.photo;

import android.content.Context;

import java.util.List;

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
        return 0;
    }

    @Override
    public void bindView(BaseItemViewHolder holder, int position) {

    }

    @Override
    public void bindHeadView(BaseItemViewHolder holder, int position) {

    }

    @Override
    public void bindFooterView(BaseItemViewHolder holder, int position) {

    }
}
