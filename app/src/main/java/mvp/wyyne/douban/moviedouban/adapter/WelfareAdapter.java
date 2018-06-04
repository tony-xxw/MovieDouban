package mvp.wyyne.douban.moviedouban.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.bean.WelfarePhotoInfo;
import mvp.wyyne.douban.moviedouban.utils.ImageLoader;

/**
 * @author XXW
 * @date 2017/6/25
 */

public class WelfareAdapter extends BaseRvAdapter<WelfarePhotoInfo> implements View.OnClickListener, View.OnLongClickListener {
    private ImageLoader mLoader;
    private ArrayList<Integer> mHeightList;
    private String photoUrl;
    private Context mContext;

    public WelfareAdapter(Context context, List<WelfarePhotoInfo> data) {
        super(context, data);
        mContext = context;
        mLoader = ImageLoader.build(context);
        mHeightList = new ArrayList<>();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_welfare_layout;
    }

    @Override
    public void bindView(BaseItemViewHolder holder, final int position) {
        initItemHeight();
        ImageView imageView = holder.getView(R.id.iv_photo);
        CardView cardView = holder.getView(R.id.ll_item);
        ViewGroup.LayoutParams params = imageView.getLayoutParams();
        params.height = mHeightList.get(position);
        imageView.setLayoutParams(params);
        TextView textView = holder.getView(R.id.tv_date);
        textView.setText(mList.get(position).getDesc());
        photoUrl = mList.get(position).getUrl();
        Glide.with(mContext).load(photoUrl).centerCrop().into(imageView);
        cardView.setTag(position);
        cardView.setOnClickListener(this);
        cardView.setOnLongClickListener(this);
    }

    private void initItemHeight() {
        if (mList.size() != 0) {
            for (WelfarePhotoInfo welfarePhotoInfo : mList) {
                mHeightList.add((int) (300 + Math.random() * 400));
            }
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
        switch (v.getId()) {
            case R.id.ll_item:
                mClick.onItemClick((Integer) v.getTag(), "");
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onLongClick(View v) {
        mLongClick.onItemLongClick((Integer) v.getTag(), "");
        return true;
    }
}
