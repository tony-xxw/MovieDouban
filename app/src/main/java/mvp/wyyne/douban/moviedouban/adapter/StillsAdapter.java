package mvp.wyyne.douban.moviedouban.adapter;

import android.content.Context;
import android.view.View;

import java.util.List;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.RvItemOnClick;
import mvp.wyyne.douban.moviedouban.api.bean.StillsPhotos;

/**
 *
 * @author XXW
 * @date 2017/7/2
 */

public class StillsAdapter extends BaseRvAdapter<StillsPhotos> {
    private RvItemOnClick mClick;
    private final static String TAG = "Stills";

    public StillsAdapter(Context context, List<StillsPhotos> data) {
        super(context, data);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_stills_layout;
    }

    public void setOnItemClick(RvItemOnClick click) {
        mClick = click;
    }

    @Override
    public void bindView(BaseItemViewHolder holder, final int position) {
        if (mList != null) {
            holder.setImgUrl(R.id.iv_all_stills, mList.get(position).getImage());
            holder.getView(R.id.iv_all_stills).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClick.onItemClick(position, TAG);
                }
            });
        }
    }

    @Override
    public void bindHeadView(BaseItemViewHolder holder, int position) {

    }

    @Override
    public void bindFooterView(BaseItemViewHolder holder, int position) {

    }
}
