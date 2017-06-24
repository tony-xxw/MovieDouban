package mvp.wyyne.douban.moviedouban.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
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
    private static final int HEAD_TYPE = 0;
    private static final int FOOTER_TYPE = 1;
    private static final int CONTENT_TYPE = 2;


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
        switch (viewType) {
            case HEAD_TYPE:
                Log.d("XXW","-----"+viewType);
                mView = mLayoutInflater.inflate(getHeadId(), null);
                mBase = new BaseItemViewHolder(mView, mContext);
                break;
            case FOOTER_TYPE:
                Log.d("XXW","-----"+viewType);
                mView = mLayoutInflater.inflate(getFooterId(), null);
                mBase = new BaseItemViewHolder(mView, mContext);
                break;
            case CONTENT_TYPE:
                Log.d("XXW","-----"+viewType);
                mView = mLayoutInflater.inflate(getLayoutId(), null);
                mBase = new BaseItemViewHolder(mView, mContext);
                break;
            default:
                break;
        }
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


    @Override
    public int getItemViewType(int position) {
        if (getHeadId() != 0 && position == 0) {
            return HEAD_TYPE;
        } else if (getFooterId() != 0 && position == mList.size() - 1) {
            return FOOTER_TYPE;
        } else {
            return CONTENT_TYPE;
        }
    }

    abstract int getLayoutId();

    abstract void bindView(BaseItemViewHolder holder, int position);

    abstract int getHeadId();

    abstract int getFooterId();
}
