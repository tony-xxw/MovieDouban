package mvp.wyyne.douban.moviedouban.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import java.util.List;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.bean.Trailers;

/**
 *
 * @author XXW
 * @date 2017/7/20
 */

public class TvHeadAdapter extends BaseRvAdapter<Trailers> implements View.OnClickListener {
    private LinearLayout mLayout;

    public TvHeadAdapter(Context context, List<Trailers> data) {
        super(context, data);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_stills_tv_head_layout;
    }

    @Override
    public void bindView(BaseItemViewHolder holder, int position) {
        if (mList.get(position) != null) {
            Trailers trailers = mList.get(position);
            holder.setImgUrl(R.id.iv_still, trailers.getMedium());
            holder.setText(R.id.tv_still, trailers.getTitle());
        }
        mLayout = holder.getView(R.id.ll_onclick);
        mLayout.setTag(position);
        mLayout.setOnClickListener(this);
    }

    @Override
    public void bindHeadView(BaseItemViewHolder holder, int position) {

    }

    @Override
    public void bindFooterView(BaseItemViewHolder holder, int position) {

    }

    @Override
    public void onClick(View v) {
        mClick.onItemClick((int) (v.getTag()), "");
    }
}
