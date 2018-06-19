package mvp.wyyne.douban.moviedouban.adapter;

import android.content.Context;
import android.view.View;

import java.util.List;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.bean.Subjects;

/**
 * @author Wynne
 * @date 2018/6/16
 */

public class NowTopAdapter extends BaseRvAdapter<Subjects> {
    private String TAG;

    public NowTopAdapter(Context context, List<Subjects> data) {
        super(context, data);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_now_top;
    }

    public void setTag(String tag) {
        TAG = tag;
    }

    @Override
    public void bindView(BaseItemViewHolder holder, final int position) {
        holder.setText(R.id.tv_title, mList.get(position).getTitle());
        holder.setImgUrl(R.id.iv_avatar, mList.get(position).getImages().getSmall());
        String average = (int) mList.get(position).getRating().getAverage() + "";
        holder.getStartView(R.id.average).setStartMark((int) mList.get(position).getRating().getAverage());
        holder.setText(R.id.tv_average_count, average);

        holder.getView(R.id.ll_content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClick.onItemClick(position, TAG);
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
