package mvp.wyyne.douban.moviedouban.adapter;

import android.content.Context;

import java.util.List;

import mvp.wyyne.douban.moviedouban.api.bean.WeeklySubject;

/**
 * @author Wynne
 * @date 2018/6/19
 */

public class WeeklyMovieAdapter extends BaseRvAdapter<WeeklySubject> {
    public WeeklyMovieAdapter(Context context, List<WeeklySubject> data) {
        super(context, data);
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
