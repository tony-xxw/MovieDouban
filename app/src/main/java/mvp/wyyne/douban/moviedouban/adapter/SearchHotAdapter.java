package mvp.wyyne.douban.moviedouban.adapter;

import android.content.Context;

import java.util.List;

import mvp.wyyne.douban.moviedouban.api.bean.Subjects;

/**
 * 热门搜索Adapter
 *
 * @author Wynne
 * @date 2018/5/27
 */

public class SearchHotAdapter extends BaseRvAdapter<Subjects> {

    public SearchHotAdapter(Context context, List<Subjects> data) {
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
