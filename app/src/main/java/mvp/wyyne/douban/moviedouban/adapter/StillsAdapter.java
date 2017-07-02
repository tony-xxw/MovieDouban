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
    private List<StillsPhotos> mList;


    public StillsAdapter(Context context, List<StillsPhotos> data) {
        super(context, data);
        Log.d("XXW", "StillsAdapter");
    }

    @Override
    int getLayoutId() {
        return R.layout.item_stills_layout;
    }

    public void setList(List<StillsPhotos> list) {
        Log.d("XXW", "setList-----"+list.size());
        mList = list;
    }

    @Override
    void bindView(BaseItemViewHolder holder, int position) {
        Log.d("XXW", "bindView");
        holder.setImgUrl(R.id.iv_all_stills, mList.get(position).getThumb());
        Log.d("XXW", mList.get(position).getIcon());
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
