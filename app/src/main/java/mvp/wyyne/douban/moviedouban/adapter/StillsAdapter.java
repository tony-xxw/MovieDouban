package mvp.wyyne.douban.moviedouban.adapter;

import android.content.Context;
import android.util.Log;

import java.util.List;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.bean.StillsPhotos;

/**
 * Created by XXW on 2017/7/2.
 */

public class StillsAdapter extends BaseRvAdapter<StillsPhotos> {


    public StillsAdapter(Context context, List<StillsPhotos> data) {
        super(context, data);
    }

    @Override
    int getLayoutId() {
        return R.layout.item_stills_layout;
    }


    @Override
    void bindView(BaseItemViewHolder holder, int position) {
        Log.d("XXW", "bindView");
        if (mList != null) {
            holder.setImgUrl(R.id.iv_all_stills, mList.get(position).getImage());
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
