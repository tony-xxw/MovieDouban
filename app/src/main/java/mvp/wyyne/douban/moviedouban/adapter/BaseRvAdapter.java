package mvp.wyyne.douban.moviedouban.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import mvp.wyyne.douban.moviedouban.api.RvItemOnClick;

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
    protected RvItemOnClick mClick;

    public void setList(List<T> list) {
        mList = list;
    }


    public void setRvOnClick(RvItemOnClick onClick) {
        mClick = onClick;
    }

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
                mBase = new BaseItemViewHolder(mFooterView, mContext);
                break;
            case CONTENT_TYPE:
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

    /**
     * 布局文件
     *
     * @return Item布局文件
     */
    abstract int getLayoutId();

    /**
     * 绑定子View
     *
     * @param holder   子类ViewHolder
     * @param position item位置
     */
    abstract void bindView(BaseItemViewHolder holder, int position);


    /**
     * 绑定item的头部
     *
     * @param holder   子类ViewHolder
     * @param position item位置
     */
    abstract void bindHeadView(BaseItemViewHolder holder, int position);

    /**
     * 绑定item的脚步
     *
     * @param holder   子类ViewHolder
     * @param position item位置
     */
    abstract void bindFooterView(BaseItemViewHolder holder, int position);


    /**
     * @return 观影人数
     */
    public String getAttendance(int count) {
        DecimalFormat df = new DecimalFormat("######0.0");
        DecimalFormat dd = new DecimalFormat("#####0.0");

        String attendance = null;
        if (count > 100000) {
            attendance = df.format(count / 10000.0) + "万人观看";
        } else if (count > 10000) {
            attendance = dd.format(count / 1000.0) + "万人观看";
        } else {
            attendance = count + "人观看";
        }

        return attendance;
    }
}
