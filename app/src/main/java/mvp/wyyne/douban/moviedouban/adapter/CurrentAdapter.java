package mvp.wyyne.douban.moviedouban.adapter;

import android.content.Context;
import android.view.View;

import java.util.List;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.RvItemOnClick;
import mvp.wyyne.douban.moviedouban.api.bean.Avatars;
import mvp.wyyne.douban.moviedouban.api.bean.Casts;
import mvp.wyyne.douban.moviedouban.api.bean.Directors;
import mvp.wyyne.douban.moviedouban.api.bean.Rating;
import mvp.wyyne.douban.moviedouban.api.bean.Subjects;

/**
 * @author XXW
 * @date 2017/6/4
 */

public class CurrentAdapter extends BaseRvAdapter<Subjects> {
    private RvItemOnClick mClick;


    public CurrentAdapter(Context context, List<Subjects> list) {
        super(context, list);
    }

    public void setOnclick(RvItemOnClick click) {
        mClick = click;
    }


    @Override
    int getLayoutId() {
        return R.layout.item_current_layout;
    }

    @Override
    void bindView(BaseItemViewHolder holder, final int position) {
        StringBuffer mName = new StringBuffer();
        List<Casts> casts = mList.get(position).getCasts();
        List<Directors> directors = mList.get(position).getDirectors();
        Avatars avatars = mList.get(position).getImages();
        Rating rating = mList.get(position).getRating();

        String attendance = getAttendance(mList.get(position).getCollect_count());

        holder.setText(R.id.tv_collect_count, attendance);
        for (int i = 0; i < casts.size(); i++) {
            if (i == casts.size() - 1) {
                mName.append(casts.get(i).getName());
            } else if (i == 0) {
                mName.append("主演: " + casts.get(i).getName() + "/");
            } else {
                mName.append(casts.get(i).getName() + "/");
            }
        }
        holder.setText(R.id.tv_casts_list, mName.toString());
        holder.setText(R.id.tv_directors_name, directors.get(0).getName());
        holder.setText(R.id.tv_title, mList.get(position).getTitle());
        holder.setImgUrl(R.id.iv_avatars, avatars.getMedium());
        if ((int) (rating.getAverage()) < 1) {
            holder.setVisibleStatu(R.id.average, View.GONE);
            holder.setText(R.id.tv_average_count, "暂时没有评分");
        } else {
            holder.setVisibleStatu(R.id.average, View.VISIBLE);
            holder.getStartView(R.id.average).setStartMark((int) rating.getAverage());
            holder.setText(R.id.tv_average_count, rating.getAverage() + "");
        }
        holder.getView(R.id.rl_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClick.onItemClick(position, "");
            }
        });
    }

    @Override
    void bindHeadView(BaseItemViewHolder holder, int position) {

    }

    @Override
    void bindFooterView(BaseItemViewHolder holder, int position) {

    }


}
