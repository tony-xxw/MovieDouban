package mvp.wyyne.douban.moviedouban.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import java.util.List;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.model.WannaTable;

/**
 * @author Wynne
 * @date 2018/6/20
 */

public class WannaAdapter extends BaseRvAdapter<WannaTable> {
    public WannaAdapter(Context context, List<WannaTable> data) {
        super(context, data);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_current_layout;
    }

    @Override
    public void bindView(BaseItemViewHolder holder, final int position) {
        holder.getView(R.id.ll_btn).setVisibility(View.GONE);
        holder.getView(R.id.tv_create_time).setVisibility(View.VISIBLE);
        holder.setText(R.id.tv_create_time, mList.get(position).getCreatetime());
        holder.setText(R.id.tv_title, mList.get(position).getTitle());
        holder.setText(R.id.tv_directors_name, mList.get(position).getDirectors());
        holder.setText(R.id.tv_casts_list, "主演 :" + mList.get(position).getCasts());
        holder.setText(R.id.tv_average_count, mList.get(position).getAverage());
        holder.setImgUrl(R.id.iv_avatars, mList.get(position).getAvatarUrl());
        double average = Double.valueOf(mList.get(position).getAverage());
        holder.getStartView(R.id.average).setStartMark((int) average);

        holder.getView(R.id.rl_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClick.onItemClick(position, "");
            }
        });

        if (!TextUtils.isEmpty(mList.get(position).getReason())) {
            holder.setText(R.id.tv_reason, mList.get(position).getReason());
        }


    }

    @Override
    public void bindHeadView(BaseItemViewHolder holder, int position) {

    }

    @Override
    public void bindFooterView(BaseItemViewHolder holder, int position) {

    }
}
