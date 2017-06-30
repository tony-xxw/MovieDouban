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

import mvp.wyyne.douban.moviedouban.api.bean.Article;
import mvp.wyyne.douban.moviedouban.api.bean.Photos;
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
    protected View mHeadView;
    protected View mFooterView;

    public BaseRvAdapter(Context context, List<T> data) {
        mLayoutResId = getLayoutId();
        if (mLayoutResId == 0) {
            throw new IllegalAccessError("Layout resource ID must be valid!");
        }
        if (data == null) {
            Log.d("XXW", "null---");
            mList = new ArrayList<>();
        } else {
            Log.d("XXW", "null===");
            this.mList = data;
        }
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void setHeadView(View view) {
        mHeadView = view;
        notifyItemInserted(0);
    }

    public void setFooterView(View view) {
        mFooterView = view;
        notifyItemInserted(getItemCount() - 1);
    }

    @Override
    public BaseItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case HEAD_TYPE:
                mBase = new BaseItemViewHolder(mHeadView, mContext);
                break;
            case FOOTER_TYPE:
                Log.d("XXW", "-----" + viewType);
                mBase = new BaseItemViewHolder(mFooterView, mContext);
                break;
            case CONTENT_TYPE:
                Log.d("XXW", "-----" + viewType);
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
        if (getItemViewType(position) == HEAD_TYPE) {
            bindHeadView(holder, position);
            return;
        } else if (getItemViewType(position) == FOOTER_TYPE) {
            bindFooterView(holder, position);
            return;
        }
        bindView(holder, getLayoutPosition(holder));
    }

    public int getLayoutPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return position - getHeadViewCount();

    }


    @Override
    public int getItemCount() {
        return mList.size() + getHeadViewCount() + getFooterViewCount();
    }

    public int getHeadViewCount() {
        return mHeadView != null ? 1 : 0;
    }

    public int getFooterViewCount() {
        return mFooterView != null ? 1 : 0;
    }


    @Override
    public int getItemViewType(int position) {
        if (mHeadView != null && position == 0) {
            return HEAD_TYPE;
        } else if (mFooterView != null && position == getItemCount() - 1) {
            return FOOTER_TYPE;
        } else {
            return CONTENT_TYPE;
        }
    }

    abstract int getLayoutId();

    abstract void bindView(BaseItemViewHolder holder, int position);

    abstract void bindHeadView(BaseItemViewHolder holder, int position);

    abstract void bindFooterView(BaseItemViewHolder holder, int position);


}
