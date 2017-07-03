package mvp.wyyne.douban.moviedouban.adapter;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;
import android.view.View;

import java.util.List;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.RvItemOnClick;
import mvp.wyyne.douban.moviedouban.api.bean.StillsPhotos;

/**
 * Created by XXW on 2017/7/2.
 */

public class StillsAdapter extends BaseRvAdapter<StillsPhotos> {
    private RvItemOnClick mClick;
    private final static String TAG = "Stills";

    public StillsAdapter(Context context, List<StillsPhotos> data) {
        super(context, data);
    }

    @Override
    int getLayoutId() {
        return R.layout.item_stills_layout;
    }

    public void setOnItemClick(RvItemOnClick click) {
        mClick = click;
    }

    @Override
    void bindView(BaseItemViewHolder holder, final int position) {
        Log.d("XXW", "bindView");
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
    void bindHeadView(BaseItemViewHolder holder, int position) {
        Log.d("XXW", "bindHeadView");
    }

    @Override
    void bindFooterView(BaseItemViewHolder holder, int position) {
        Log.d("XXW", "bindFooterView");
    }
}
