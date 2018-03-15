package mvp.wyyne.douban.moviedouban.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.RvItemOnClick;
import mvp.wyyne.douban.moviedouban.api.bean.Avatars;
import mvp.wyyne.douban.moviedouban.api.bean.Casts;
import mvp.wyyne.douban.moviedouban.api.bean.Directors;
import mvp.wyyne.douban.moviedouban.api.bean.Rating;
import mvp.wyyne.douban.moviedouban.api.bean.Subjects;
import mvp.wyyne.douban.moviedouban.hot.current.HotCurrentFragment;

/**
 * @author XXW
 * @date 2017/6/4
 */

public class CurrentAdapter extends BaseRvAdapter<Subjects> {
    private RvItemOnClick mClick;
    private Subjects mSubjects;
    private Context mContext;


    public CurrentAdapter(Context context, List<Subjects> list) {
        super(context, list);
        mContext = context;
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
        mSubjects = mList.get(position);
        List<Casts> casts = mSubjects.getCasts();
        List<Directors> directors = mSubjects.getDirectors();
        Avatars avatars = mSubjects.getImages();
        Rating rating = mSubjects.getRating();

        String attendance = getAttendance(mSubjects.getCollect_count());

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
        holder.setText(R.id.tv_title, mSubjects.getTitle());
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

        if (getPredate(mSubjects.getPubdates().get(0))) {

            Button button = holder.getView(R.id.btn_shop);
            GradientDrawable drawable = (GradientDrawable) button.getBackground();
            drawable.setStroke(1, mContext.getResources().getColor(R.color.colorOrange));
            button.setTextColor(mContext.getResources().getColor(R.color.colorOrange));
            button.setText(mContext.getString(R.string.reserve));
        }

        Log.d("XXW", HotCurrentFragment.TAG + "===" + getPredate(mSubjects.getPubdates().get(0)));


    }

    @Override
    void bindHeadView(BaseItemViewHolder holder, int position) {

    }

    @Override
    void bindFooterView(BaseItemViewHolder holder, int position) {

    }


}
