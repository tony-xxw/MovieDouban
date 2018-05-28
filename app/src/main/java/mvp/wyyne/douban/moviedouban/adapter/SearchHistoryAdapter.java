package mvp.wyyne.douban.moviedouban.adapter;

import android.content.Context;
import android.widget.TextView;

import java.util.List;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.model.SearchModelBean;

/**
 * 搜索记录
 *
 * @author Wynne
 * @date 2018/5/27
 */

public class SearchHistoryAdapter extends BaseRvAdapter<SearchModelBean> {
    public static final String TAG = SearchHistoryAdapter.class.getSimpleName();

    public SearchHistoryAdapter(Context context, List<SearchModelBean> data) {
        super(context, data);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_search_history;
    }

    @Override
    public void bindView(BaseItemViewHolder holder, int position) {
        TextView tvName = holder.getView(R.id.tv_name);
        tvName.setText(mList.get(position).getName());
    }

    @Override
    public void bindHeadView(BaseItemViewHolder holder, int position) {

    }

    @Override
    public void bindFooterView(BaseItemViewHolder holder, int position) {

    }
}
