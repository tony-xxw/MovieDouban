package mvp.wyyne.douban.moviedouban.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import mvp.wyyne.douban.moviedouban.api.RvItemLongOnClick;
import mvp.wyyne.douban.moviedouban.api.RvItemOnClick;
import mvp.wyyne.douban.moviedouban.utils.DateUtils;

/**
 * @author XXW
 * @date 2017/6/13
 */

public abstract class BaseRvAdapter<T> extends RecyclerView.Adapter<BaseItemViewHolder> {
    private BaseItemViewHolder mBase;
    public Context mContext;
    private View mView;
    protected List<T> mList;
    private int mLayoutResId;
    private LayoutInflater mLayoutInflater;
    private static final int HEAD_TYPE = 0;
    private static final int FOOTER_TYPE = 1;
    private static final int CONTENT_TYPE = 2;
    public View mHeadView;
    public View mFooterView;
    public RvItemOnClick mClick;
    public RvItemLongOnClick mLongClick;

    public void setRvLongOnClick(RvItemLongOnClick mLongClick) {
        this.mLongClick = mLongClick;
    }


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
            mList = new ArrayList<>();
        } else {
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
                mView = mLayoutInflater.inflate(getLayoutId(), parent, false);
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
    protected abstract int getLayoutId();

    /**
     * 绑定子View
     *
     * @param holder   子类ViewHolder
     * @param position item位置
     */
    public abstract void bindView(BaseItemViewHolder holder, int position);


    /**
     * 绑定item的头部
     *
     * @param holder   子类ViewHolder
     * @param position item位置
     */
    public abstract void bindHeadView(BaseItemViewHolder holder, int position);

    /**
     * 绑定item的脚步
     *
     * @param holder   子类ViewHolder
     * @param position item位置
     */
    public abstract void bindFooterView(BaseItemViewHolder holder, int position);


    /**
     * 判断是否上映
     *
     * @param pubdates 上映时间
     * @return
     */
    public boolean getPredate(String pubdates) {
        int predateDate = Integer.parseInt(pubdates.substring(0, pubdates.indexOf("(")).replace("-", ""));
        int currentDate = Integer.parseInt(DateUtils.getCurrentDateYMT());
        return predateDate > currentDate;
    }
}
