package mvp.wyyne.douban.moviedouban.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.bean.WelfarePhotoInfo;
import mvp.wyyne.douban.moviedouban.utils.ImageLoader;
import mvp.wyyne.douban.moviedouban.utils.TransformSize;

/**
 * @author XXW
 * @date 2017/6/25
 */

public class WelfareAdapter extends BaseRvAdapter<WelfarePhotoInfo> implements View.OnClickListener, View.OnLongClickListener {
    private ImageLoader mLoader;
    private ArrayList<Integer> mHeightList;
    private String photoUrl;
    private Context mContext;
    public static final String LINEAR = "linear";
    public static final String GRIDS = "grids";

    private String layoutManager;

    public WelfareAdapter(Context context, List<WelfarePhotoInfo> data) {
        super(context, data);
        mContext = context;
        mLoader = ImageLoader.build(context);
        mHeightList = new ArrayList<>();

    }

    public void setLayoutManager(String layoutManager) {
        this.layoutManager = layoutManager;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_welfare_layout;
    }

    @Override
    public void bindView(BaseItemViewHolder holder, final int position) {
        ImageView imageView = holder.getView(R.id.iv_photo);
        CardView cardView = holder.getView(R.id.ll_item);
        TextView textView = holder.getView(R.id.tv_date);
        cardView.setTag(position);
        cardView.setOnClickListener(this);
        cardView.setOnLongClickListener(this);
        textView.setText(mList.get(position).getDesc());
        photoUrl = mList.get(position).getUrl();

        if (!TextUtils.isEmpty(layoutManager)) {

            if (layoutManager.equals(LINEAR)) {

                ViewGroup.LayoutParams params = imageView.getLayoutParams();
                params.height = TransformSize.pxToDp(mContext, 150);
                imageView.setLayoutParams(params);

                Glide.with(mContext).
                        load(photoUrl).
                        thumbnail(0.5f).
                        diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageView);


            } else if (layoutManager.equals(GRIDS)) {
                ViewGroup.LayoutParams params = imageView.getLayoutParams();
                params.height = TransformSize.pxToDp(mContext, 120);
                imageView.setLayoutParams(params);

                Glide.with(mContext).
                        load(photoUrl).
                        diskCacheStrategy(DiskCacheStrategy.ALL).
                        into(imageView);

            }
        } else {

            initItemHeight();
            ViewGroup.LayoutParams params = imageView.getLayoutParams();
            params.height = mHeightList.get(position);
            imageView.setLayoutParams(params);


            Glide.with(mContext).
                    load(photoUrl).
                    thumbnail(0.7f).
                    diskCacheStrategy(DiskCacheStrategy.ALL).
                    into(imageView);
        }


    }

    private void switchLayoutManager() {

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
