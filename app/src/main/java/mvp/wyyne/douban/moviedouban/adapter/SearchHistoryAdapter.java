package mvp.wyyne.douban.moviedouban.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.model.SearchModelTable;

/**
 * 搜索记录
 *
 * @author Wynne
 * @date 2018/5/27
 */

public class SearchHistoryAdapter extends BaseRvAdapter<SearchModelTable> {
    public static final String TAG = SearchHistoryAdapter.class.getSimpleName();

    public SearchHistoryAdapter(Context context, List<SearchModelTable> data) {
        super(context, data);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_search_history;
    }

    @Override
    public void bindView(BaseItemViewHolder holder, final int position) {
        TextView tvName = holder.getView(R.id.tv_name);
        tvName.setText(mList.get(position).getName());
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
