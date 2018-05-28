package mvp.wyyne.douban.moviedouban.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.bean.Casts;
import mvp.wyyne.douban.moviedouban.api.bean.Directors;

/**
 * @author XXW
 * @date 2017/6/24
 */

public class CastAdapter extends BaseRvAdapter<Casts> implements View.OnClickListener {
    public static final String CAST = "cast";
    private Casts mCasts;
    private List<Directors> mDirectorses;
    private Context mContext;
    private View mContent;

    public CastAdapter(Context context, List<Casts> data) {
        super(context, data);
        mContext = context;
    }


    public void setDirectorses(List<Directors> list) {
        mDirectorses = list;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.item_cast_layout;
    }

    @Override
    public void bindView(BaseItemViewHolder holder, int position) {
        if (mList.size() != 0) {
            mCasts = mList.get(position);
            holder.setText(R.id.tv_identity, mCasts.getName());
            if (mCasts.getAvatars() != null) {
                holder.setImgUrl(R.id.iv_casts, mCasts.getAvatars().getMedium());
            }
        }

        mContent = holder.getView(R.id.ll_cast);
        mContent.setOnClickListener(this);
        mContent.setTag(position);

    }

    @Override
    public void bindHeadView(BaseItemViewHolder holder, int position) {
        if (position == 0 && mDirectorses.size() != 0) {
            for (Directors directorse : mDirectorses) {
                TextView textView = (TextView) mHeadView.findViewById(R.id.tv_identity);
                textView.setText(directorse.getName());
                ImageView imageView = (ImageView) mHeadView.findViewById(R.id.iv_casts);
                if (directorse.getAvatars() != null) {
                    String url = directorse.getAvatars().getMedium();
                    if (!TextUtils.isEmpty(url)) {
                        Glide.with(mContext).load(directorse.getAvatars().getMedium()).into(imageView);
                    }
                }
            }
        }
    }

    @Override
    public void bindFooterView(BaseItemViewHolder holder, int position) {

    }


    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        switch (v.getId()) {
            case R.id.ll_cast:
                mClick.onItemClick(position, CAST);
                break;
            default:
                break;
        }
    }
}
