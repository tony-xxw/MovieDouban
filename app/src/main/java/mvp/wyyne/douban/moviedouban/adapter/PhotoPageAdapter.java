package mvp.wyyne.douban.moviedouban.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.bean.StillsPhotos;

/**
 * Created by XXW on 2017/7/4.
 */

public class PhotoPageAdapter extends PagerAdapter {
    private List<StillsPhotos> mList;
    private Context mContext;

    public PhotoPageAdapter(Context context, List<StillsPhotos> list) {
        mContext = context;
        mList = list;
    }

    public void setList(List<StillsPhotos> list) {
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.page_photo_layout, null, false);
        final ImageView imageView = (ImageView) view.findViewById(R.id.iv_stills_photo);
        Log.d("XXW", "----" + mList.get(position).getImage());
        Glide.with(mContext).load(mList.get(position).getImage()).into(imageView);
        container.addView(view);
        return view;
    }
}
