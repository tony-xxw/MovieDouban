package mvp.wyyne.douban.moviedouban.adapter;

import android.content.Context;
import android.view.View;

import java.util.List;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.bean.Avatars;
import mvp.wyyne.douban.moviedouban.api.bean.Casts;
import mvp.wyyne.douban.moviedouban.api.bean.Directors;
import mvp.wyyne.douban.moviedouban.api.bean.Subjects;

/**
 *
 * @author XXW
 * @date 2017/6/13
 */

public class FutureAdapter extends BaseRvAdapter<Subjects> {

    public FutureAdapter(Context context, List<Subjects> list) {
        super(context, list);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.item_current_layout;
    }

    @Override
    public void bindView(BaseItemViewHolder holder, int position) {
        List<Casts> casts = mList.get(position).getCasts();
        List<Directors> directors = mList.get(position).getDirectors();
        Avatars avatars = mList.get(position).getImages();
        holder.setText(R.id.tv_collect_count, mList.get(position).getCollect_count() + "人看过");
        for (Casts cast : casts) {
            holder.setText(R.id.tv_casts_list, cast.getName());
        }
        holder.setText(R.id.tv_directors_name, directors.get(0).getName());
        holder.setText(R.id.tv_title, mList.get(position).getTitle());
        holder.setImgUrl(R.id.iv_avatars, avatars.getMedium());
        holder.setVisibleStatu(R.id.ll_star, View.GONE);
        holder.setText(R.id.btn_shop, "想看");

    }

    @Override
    public void bindHeadView(BaseItemViewHolder holder, int position) {

    }

    @Override
    public void bindFooterView(BaseItemViewHolder holder, int position) {

    }


}

