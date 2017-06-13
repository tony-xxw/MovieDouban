package mvp.wyyne.douban.moviedouban.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import mvp.wyyne.douban.moviedouban.api.bean.Subjects;

/**
 * Created by XXW on 2017/6/13.
 */

public abstract class BaseRvAdapter<T> extends RecyclerView.Adapter<BaseItemViewHolder> {
    private BaseItemViewHolder mBase;
    private Context mContext;
    private View mView;
    protected List<T> mList;
    private int mLayoutResId;
    private LayoutInflater mLayoutInflater;


    public BaseRvAdapter(Context context, List<T> data) {
        mLayoutResId = getLayoutId();
        if (mLayoutResId == 0) {
            throw new IllegalAccessError("Layout resource ID must be valid!");
        }
        if (data == null) {
            mList = new ArrayList<>();
        } else {
            this.mList = data;
        }
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public BaseItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mView = mLayoutInflater.inflate(getLayoutId(), null);
        mBase = new BaseItemViewHolder(mView,mContext);
        return mBase;
    }

    @Override
    public void onBindViewHolder(BaseItemViewHolder holder, int position) {
        bindView(holder, position);
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }





    abstract int getLayoutId();

    abstract void bindView(BaseItemViewHolder holder, int position);
}
