package mvp.wyyne.douban.moviedouban.adapter;

import android.content.Context;
import android.util.Log;

import java.util.List;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.bean.Photos;

/**
 * Created by XXW on 2017/7/8.
 */

public class PhotoFmAdapter extends BaseRvAdapter<Photos> {
    public PhotoFmAdapter(Context context, List<Photos> data) {
        super(context, data);
    }

    @Override
    int getLayoutId() {
        return R.layout.item_cast_article_fm;
    }

    @Override
    void bindView(BaseItemViewHolder holder, int position) {
        if (mList != null && mList.size() < 8) {
            Log.d("XXW", "photo--" + mList.get(position).getImage());
            holder.setImgUrl(R.id.iv_cast, mList.get(position).getIcon());
        }

    }

    @Override
    void bindHeadView(BaseItemViewHolder holder, int position) {

    }

    @Override
    void bindFooterView(BaseItemViewHolder holder, int position) {

    }
}
