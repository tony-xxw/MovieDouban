package mvp.wyyne.douban.moviedouban.adapter;

import android.content.Context;

import java.security.PublicKey;
import java.util.List;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.bean.Photos;

/**
 * Created by XXW on 2017/6/24.
 */

public class PhotoAdapter extends BaseRvAdapter<Photos> {

    public PhotoAdapter(Context context, List<Photos> data) {
        super(context, data);
    }

    public void setList(List<Photos> photoses) {
        mList = photoses;
    }


    @Override
    int getLayoutId() {
        return R.layout.movie_detail_stills;
    }

    @Override
    void bindView(BaseItemViewHolder holder, int position) {
        holder.setImgUrl(R.id.iv_stills, mList.get(position).getImage());

    }

}
