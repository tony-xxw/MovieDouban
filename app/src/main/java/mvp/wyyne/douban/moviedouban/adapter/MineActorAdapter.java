package mvp.wyyne.douban.moviedouban.adapter;

import android.content.Context;
import android.view.View;

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
        return R.layout.item_oneself_actor;
    }

    @Override
    public void bindView(BaseItemViewHolder holder, final int position) {
        holder.setImgUrl(R.id.iv_avatar, mList.get(position).getAvatarUrl());
        holder.setText(R.id.tv_name, mList.get(position).getActorName());
        String representative = "代表作: " + mList.get(position).getRepresentative();
        holder.setText(R.id.tv_representative, representative);
        holder.getView(R.id.rl_item_content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClick.onItemClick(position, "");
            }
        });
    }

    @Override
    public void bindHeadView(BaseItemViewHolder holder, int position) {

    }

    @Override
    public void bindFooterView(BaseItemViewHolder holder, int position) {

    }
}
