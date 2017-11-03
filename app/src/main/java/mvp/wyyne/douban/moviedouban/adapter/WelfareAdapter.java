package mvp.wyyne.douban.moviedouban.adapter;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.bean.WelfarePhotoInfo;
import mvp.wyyne.douban.moviedouban.utils.ImageLoader;

/**
 * Created by XXW on 2017/6/25.
 */

public class WelfareAdapter extends BaseRvAdapter<WelfarePhotoInfo> {

    private int mPhotoWidth;
    private Context mContext;
    private Random mRandom;
    private ImageLoader mLoader;
    private Map<Integer, Integer> mHeightList;
    private String photoUrl;

    public WelfareAdapter(Context context, List<WelfarePhotoInfo> data) {
        super(context, data);
        mContext = context;
        mRandom = new Random();
        int widthPixels = context.getResources().getDisplayMetrics().widthPixels;
        int marginPixels = context.getResources().getDimensionPixelOffset(R.dimen.photo_margin_width);
        mPhotoWidth = widthPixels / 2 - marginPixels;
        mLoader = ImageLoader.build(context);
        mHeightList = new HashMap<>();

    }

    @Override
    int getLayoutId() {
        return R.layout.item_welfare_layout;
    }

    @Override
    void bindView(BaseItemViewHolder holder, int position) {
        ImageView imageView = holder.getView(R.id.iv_photo);
        imageView.setTag(mList.get(position).getUrl());
        final ViewGroup.LayoutParams params = imageView.getLayoutParams();
        int max = ((mPhotoWidth + mPhotoWidth / 2));
        params.width = mPhotoWidth;
        if (mHeightList.get(position) != null) {
            params.height = mHeightList.get(position);
        } else {
            params.height = mRandom.nextInt(max) % (max - mPhotoWidth + 1) + mPhotoWidth;
            mHeightList.put(position, params.height);
        }
        imageView.setLayoutParams(params);


//        Glide.with(mContext).load(mList.get(position).getUrl())
//                .into(imageView);
        photoUrl = mList.get(position).getUrl();
        Log.d("XXW", "photo-URL--" + photoUrl + "===" + position);

        mLoader.bindBitmap(photoUrl, imageView);

    }

    @Override
    void bindHeadView(BaseItemViewHolder holder, int position) {

    }

    @Override
    void bindFooterView(BaseItemViewHolder holder, int position) {

    }


}
