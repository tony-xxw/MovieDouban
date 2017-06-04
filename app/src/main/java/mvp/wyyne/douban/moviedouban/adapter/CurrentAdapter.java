package mvp.wyyne.douban.moviedouban.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import mvp.wyyne.douban.moviedouban.R;

/**
 * Created by XXW on 2017/6/4.
 */

public class CurrentAdapter extends RecyclerView.Adapter<CurrentAdapter.CurrentHolder> {

    private CurrentHolder mHolder;
    private View mView;
    private Context mContext;
    private List<String> mList;

    public CurrentAdapter(Context context, List<String> list) {
        mContext = context;
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
        holder.mMoviesName.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class CurrentHolder extends RecyclerView.ViewHolder {
        private TextView mMoviesName;

        public CurrentHolder(View itemView) {
            super(itemView);
            mMoviesName = (TextView) itemView.findViewById(R.id.movie_name);
        }
    }
}
