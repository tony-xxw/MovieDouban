package mvp.wyyne.douban.moviedouban.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.bean.Casts;
import mvp.wyyne.douban.moviedouban.api.bean.Directors;

/**
 * Created by XXW on 2017/6/24.
 */

public class CastAdapter extends BaseRvAdapter<Casts> {
    private Casts mCasts;
    private List<Directors> mDirectorses;
    private Context mContext;

    public CastAdapter(Context context, List<Casts> data) {
        super(context, data);
        mContext = context;
    }

    public void setList(List<Casts> list) {
        mList = list;
    }

    public void setDirectorses(List<Directors> list) {
        mDirectorses = list;
    }

    @Override
    int getLayoutId() {
        return R.layout.item_cast_layout;
    }

    @Override
    void bindView(BaseItemViewHolder holder, int position) {
        if (mList.size() != 0) {
            mCasts = mList.get(position);
            holder.setText(R.id.tv_identity, mCasts.getName());
            if (mCasts.getAvatars() != null) {
                holder.setImgUrl(R.id.iv_casts, mCasts.getAvatars().getMedium());
            }
        }
    }

    public void bindHeadView(BaseItemViewHolder holder, int position) {
        if (position == 0 && mDirectorses.size() != 0) {
            for (Directors directorse : mDirectorses) {
                TextView textView = (TextView) mHeadView.findViewById(R.id.tv_identity);
                textView.setText(directorse.getName());
                ImageView imageView = (ImageView) mHeadView.findViewById(R.id.iv_casts);
                Glide.with(mContext).load(directorse.getAvatars().getMedium()).into(imageView);
            }
        }
    }

    @Override
    void bindFooterView(BaseItemViewHolder holder, int position) {

    }

}
