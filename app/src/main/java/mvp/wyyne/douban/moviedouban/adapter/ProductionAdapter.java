package mvp.wyyne.douban.moviedouban.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import java.util.List;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.bean.Avatars;
import mvp.wyyne.douban.moviedouban.api.bean.Subjects;
import mvp.wyyne.douban.moviedouban.api.bean.Works;

/**
 *
 * @author XXW
 * @date 2017/7/15
 */

public class ProductionAdapter extends BaseRvAdapter<Works> implements View.OnClickListener {
    private LinearLayout mLayout;

    public ProductionAdapter(Context context, List<Works> data) {
        super(context, data);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_producation_layout;
    }



    @Override
    public void bindView(BaseItemViewHolder holder, int position) {
        if (mList.size() != 0) {
            Subjects subjects = mList.get(position).getSubject();
            Avatars avatars = subjects.getImages();
            holder.setImgUrl(R.id.iv_still, avatars.getMedium());
            holder.setText(R.id.tv_still_name, subjects.getTitle());
            holder.getStartView(R.id.average).setStartMark((int) subjects.getRating().getAverage());
            holder.setText(R.id.tv_star, String.valueOf(subjects.getRating().getAverage()));
            mLayout = holder.getView(R.id.ll_onclick);
            mLayout.setTag(position);
            mLayout.setOnClickListener(this);

        }
    }

    @Override
    public void bindHeadView(BaseItemViewHolder holder, int position) {

    }

    @Override
    public void bindFooterView(BaseItemViewHolder holder, int position) {

    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        mClick.onItemClick(position, "");
    }
}
