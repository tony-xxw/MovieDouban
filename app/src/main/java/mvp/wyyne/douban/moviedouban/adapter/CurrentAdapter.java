package mvp.wyyne.douban.moviedouban.adapter;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.bean.Avatars;
import mvp.wyyne.douban.moviedouban.api.bean.Casts;
import mvp.wyyne.douban.moviedouban.api.bean.Directors;
import mvp.wyyne.douban.moviedouban.api.bean.HotBean;
import mvp.wyyne.douban.moviedouban.api.bean.Rating;
import mvp.wyyne.douban.moviedouban.api.bean.Subjects;

/**
 * Created by XXW on 2017/6/4.
 */

public class CurrentAdapter extends RecyclerView.Adapter<CurrentAdapter.CurrentHolder> {

    private CurrentHolder mHolder;
    private View mView;
    private Context mContext;
    private List<Subjects> mList;

    public CurrentAdapter(Context context, List<Subjects> list) {
        mContext = context;
        mList = list;
    }

    public void setList(List<Subjects> list) {
        mList = list;
    }


    @Override
    public CurrentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mView = LayoutInflater.from(mContext).inflate(R.layout.item_current_layout, parent, false);
        mHolder = new CurrentHolder(mView);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(CurrentHolder holder, int position) {
        List<Casts> casts = mList.get(position).getCasts();
        List<Directors> directors = mList.get(position).getDirectors();
        Avatars avatars = mList.get(position).getImages();
        Rating rating = mList.get(position).getRating();
        holder.mCollectCount.setText(mList.get(position).getCollect_count() + "");
        holder.mCastsList.setText(casts.get(0).getName() + "/" + casts.get(1).getName() + "/" + casts.get(2).getName());
        holder.mDirectors.setText(directors.get(0).getName());
        holder.mTitle.setText(mList.get(position).getTitle());
        Glide.with(mContext).load(avatars.getMedium()).into(holder.mAvatars);
//        holder.mMoviesName.setText(mList.get(position));
        Log.d("XXW", mList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class CurrentHolder extends RecyclerView.ViewHolder {
        private ImageView mAvatars;
        private TextView mTitle;
        private TextView mAverage;
        private TextView mDirectors;
        private TextView mCastsList;
        private TextView mCollectCount;

        public CurrentHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.tv_title);
            mAverage = (TextView) itemView.findViewById(R.id.tv_average);
            mDirectors = (TextView) itemView.findViewById(R.id.tv_directors_name);
            mCastsList = (TextView) itemView.findViewById(R.id.tv_casts_list);
            mCollectCount = (TextView) itemView.findViewById(R.id.tv_collect_count);
            mAvatars = (ImageView) itemView.findViewById(R.id.iv_avatars);

        }
    }
}
