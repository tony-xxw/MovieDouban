package mvp.wyyne.douban.moviedouban.adapter;

import android.content.Context;

import java.util.List;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.bean.Subjects;
import mvp.wyyne.douban.moviedouban.api.bean.UsSubjects;

/**
 *
 * @author Wynne
 * @date 2018/6/16
 */

public class UsAdapter extends BaseRvAdapter<UsSubjects> {
    public UsAdapter(Context context, List<UsSubjects> data) {
        super(context, data);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_movie_week_uc;
    }

    @Override
    public void bindView(BaseItemViewHolder holder, int position) {
        Subjects subjects = mList.get(position).getSubject();
        holder.setText(R.id.tv_title, subjects.getTitle());
        holder.setText(R.id.tv_number, mList.get(position).getRank() + "");
        holder.setImgUrl(R.id.iv_avatar, subjects.getImages().getMedium());
        String average = (int) subjects.getRating().getAverage() + "";
        holder.getStartView(R.id.average).setStartMark((int) subjects.getRating().getAverage());
        holder.setText(R.id.tv_average_count, average);
        holder.setText(R.id.tv_detail_num, subjects.getCollect_count() + "人评价");
    }

    @Override
    public void bindHeadView(BaseItemViewHolder holder, int position) {

    }

    @Override
    public void bindFooterView(BaseItemViewHolder holder, int position) {

    }
}
