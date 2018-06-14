package mvp.wyyne.douban.moviedouban.adapter;

import android.content.Context;

import java.util.List;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.model.ActorCollectTable;

/**
 * @author Wynne
 * @date 2018/6/14
 */

public class MineActorAdapter extends BaseRvAdapter<ActorCollectTable> {

    public MineActorAdapter(Context context, List<ActorCollectTable> data) {
        super(context, data);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_oneself_actor;
    }

    @Override
    public void bindView(BaseItemViewHolder holder, int position) {
        holder.setImgUrl(R.id.iv_avatar, mList.get(position).getAvatarUrl());
        holder.setText(R.id.tv_name, mList.get(position).getActorName());
        holder.setText(R.id.tv_representative, mList.get(position).getRepresentative());
    }

    @Override
    public void bindHeadView(BaseItemViewHolder holder, int position) {

    }

    @Override
    public void bindFooterView(BaseItemViewHolder holder, int position) {

    }
}
