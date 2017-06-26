package mvp.wyyne.douban.moviedouban.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import de.hdodenhof.circleimageview.CircleImageView;
import mvp.wyyne.douban.moviedouban.widget.StartView;

/**
 * Created by XXW on 2017/6/13.
 */

public class BaseItemViewHolder extends RecyclerView.ViewHolder {
    private View convertView;
    private final SparseArray<View> mViews;
    private Context mContext;

    protected BaseItemViewHolder(View itemView, Context context) {
        super(itemView);
        this.mViews = new SparseArray<>();
        convertView = itemView;
        mContext = context;
    }


    public BaseItemViewHolder setText(int viewId, CharSequence value) {
        TextView tv = getView(viewId);
        tv.setText(value);
        return this;
    }


    public BaseItemViewHolder setImgUrl(int viewId, String url) {
        ImageView iv = getView(viewId);
        Glide.with(mContext).load(url).into(iv);
        return this;
    }

    public BaseItemViewHolder setImaCircleUrl(int viewId, String url) {
        CircleImageView circle = getView(viewId);
        Glide.with(mContext).load(url).into(circle);
        return this;
    }

    public BaseItemViewHolder setVisibleStatu(int viewId, int statu) {
        View iv = getView(viewId);
        iv.setVisibility(statu);
        return this;
    }

    public StartView getStartView(int viewId) {
        StartView sv = getView(viewId);
        return sv;
    }

    public void setValue(int viewId, int value) {
        RatingBar rating = getView(viewId);
        rating.setRating((float) value);
    }


    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = convertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }


}
