package mvp.wyyne.douban.moviedouban.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.bean.Avatars;
import mvp.wyyne.douban.moviedouban.api.bean.Casts;
import mvp.wyyne.douban.moviedouban.api.bean.Directors;
import mvp.wyyne.douban.moviedouban.api.bean.Rating;
import mvp.wyyne.douban.moviedouban.api.bean.Subjects;
import mvp.wyyne.douban.moviedouban.widget.StartView;

/**
 * Created by XXW on 2017/6/13.
 */

public class FutureAdapter extends RecyclerView.Adapter<FutureAdapter.CurrentHolder> {
    private FutureAdapter.CurrentHolder mHolder;
    private View mView;
    private Context mContext;
    private List<Subjects> mList;

    public FutureAdapter(Context context, List<Subjects> list) {
        mContext = context;
        mList = list;
    }

    public void setList(List<Subjects> list) {
        mList = list;
    }


    @Override
    public FutureAdapter.CurrentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mView = LayoutInflater.from(mContext).inflate(R.layout.item_current_layout, parent, false);
        mHolder = new FutureAdapter.CurrentHolder(mView);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(FutureAdapter.CurrentHolder holder, int position) {
        List<Casts> casts = mList.get(position).getCasts();
        List<Directors> directors = mList.get(position).getDirectors();
        Avatars avatars = mList.get(position).getImages();
        Rating rating = mList.get(position).getRating();
        holder.mCollectCount.setText(mList.get(position).getCollect_count() + "人看过");
        for (Casts cast : casts) {
            holder.mCastsList.setText(cast.getName());
        }
        holder.mDirectors.setText(directors.get(0).getName());
        holder.mTitle.setText(mList.get(position).getTitle());
        Glide.with(mContext).load(avatars.getMedium()).into(holder.mAvatars);
        holder.mStar.setVisibility(View.GONE);
        holder.mShop.setText("想看");
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class CurrentHolder extends RecyclerView.ViewHolder {
        private ImageView mAvatars;
        private TextView mTitle;
        private StartView mAverage;
        private TextView mDirectors;
        private TextView mCastsList;
        private TextView mCollectCount;
        private TextView mAverage_count;
        private Button mShop;
        private LinearLayout mStar;

        public CurrentHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.tv_title);
            mAverage = (StartView) itemView.findViewById(R.id.average);
            mDirectors = (TextView) itemView.findViewById(R.id.tv_directors_name);
            mCastsList = (TextView) itemView.findViewById(R.id.tv_casts_list);
            mCollectCount = (TextView) itemView.findViewById(R.id.tv_collect_count);
            mAvatars = (ImageView) itemView.findViewById(R.id.iv_avatars);
            mAverage_count = (TextView) itemView.findViewById(R.id.tv_average_count);
            mShop = (Button) itemView.findViewById(R.id.btn_shop);
            mStar = (LinearLayout) itemView.findViewById(R.id.ll_star);

        }
    }
}
