package mvp.wyyne.douban.moviedouban.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.bean.HotSearch;

/**
 * 热门搜索Adapter
 *
 * @author Wynne
 * @date 2018/5/27
 */

public class SearchHotAdapter extends BaseRvAdapter<HotSearch> {

    public SearchHotAdapter(Context context, List<HotSearch> data) {
        super(context, data);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_search_hot;
    }

    @Override
    public void bindView(BaseItemViewHolder holder, final int position) {
        TextView tvMovie = holder.getView(R.id.tv_movie);
        tvMovie.setText(mList.get(position).getSubjects().getTitle());
        ImageView ivMovie = holder.getView(R.id.iv_movie);
        ivMovie.setImageResource(mList.get(position).getTabImg());

        tvMovie.setOnClickListener(new View.OnClickListener() {
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
