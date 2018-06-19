package mvp.wyyne.douban.moviedouban.adapter;

import android.content.Context;
import android.view.View;

import java.util.List;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.bean.Subjects;

/**
 * @author Wynne
 * @date 2018/6/19
 */

public class HotMovieAdapter extends BaseRvAdapter<Subjects> {

    public HotMovieAdapter(Context context, List<Subjects> data) {
        super(context, data);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_movie_hot;
    }

    @Override
    public void bindView(BaseItemViewHolder holder, final int position) {
        Subjects subjects = mList.get(position);
        holder.setText(R.id.tv_title, subjects.getTitle());
        holder.setImgUrl(R.id.iv_avatar, subjects.getImages().getMedium());
        holder.getStartView(R.id.average).setStartMark((int) subjects.getRating().getAverage());
        holder.setText(R.id.tv_average_count, subjects.getRating().getAverage() + "");
        String content = subjects.getYear() + "/";
        for (String string : subjects.getGenres()) {
            content = new StringBuffer(content + string + "/").toString();
        }
        for (int i = 0; i < subjects.getCasts().size(); i++) {
            if (i != subjects.getCasts().size() - 1) {
                content = new StringBuffer(content + subjects.getCasts().get(i).getName() + "/").toString();
            } else {
                content = new StringBuffer(content + subjects.getCasts().get(i).getName()).toString();
            }

        }
        holder.setText(R.id.tv_movie_info, content);

        holder.getView(R.id.rl_content).setOnClickListener(new View.OnClickListener() {
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
