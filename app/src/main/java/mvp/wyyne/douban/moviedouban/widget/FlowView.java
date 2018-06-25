package mvp.wyyne.douban.moviedouban.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.utils.ResourcesUtils;

/**
 * 流式布局
 *
 * @author Wynne
 * @date 2018/6/23
 */

public class FlowView extends ViewGroup implements View.OnClickListener {
    private Context mContext;
    private ColorStateList mTextColor;
    private float mTextSize;
    private Drawable mFlowBg;
    private int mTextPaddingLeft;
    private int mTextPaddingTop;
    private int mTextPaddingRight;
    private int mTextPaddingBottom;
    private int mWordMargin;
    private int mLineMargin;
    private int mMaxSelect;
    private SelectType mSelectType;
    private OnFlowClickListener mOnFlowClick;

    /**
     * 用于保存tag数据的key
     **/
    private static final int KEY_DATA = R.id.tag_key_data;
    /**
     * 用于保存tag位置的key
     **/
    private static final int KEY_POSITION = R.id.tag_key_position;

    public FlowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        getAttributeSet(context, attrs);
    }

    public FlowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        getAttributeSet(context, attrs);
    }

    private void getAttributeSet(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.flow_view);
            int type = typedArray.getInt(R.styleable.flow_view_selectType, 1);
            mSelectType = SelectType.get(type);

            mMaxSelect = typedArray.getInteger(R.styleable.flow_view_maxSelect, 0);
            mTextColor = typedArray.getColorStateList(R.styleable.flow_view_flowTextColor);
            mTextSize = typedArray.getDimension(
                    R.styleable.flow_view_flowTextSize, ResourcesUtils.sp2px(mContext, 14));
            mTextPaddingLeft = typedArray.getDimensionPixelOffset(R.styleable.flow_view_flowTextPaddingLeft, 0);
            mTextPaddingTop = typedArray.getDimensionPixelOffset(R.styleable.flow_view_flowTextPaddingTop, 0);
            mTextPaddingRight = typedArray.getDimensionPixelOffset(R.styleable.flow_view_flowTextPaddingRight, 0);
            mTextPaddingBottom = typedArray.getDimensionPixelOffset(R.styleable.flow_view_flowTextPaddingBottom, 0);
            mLineMargin = typedArray.getDimensionPixelOffset(R.styleable.flow_view_lineMargin, 0);
            mWordMargin = typedArray.getDimensionPixelOffset(R.styleable.flow_view_wordMargin, 0);
            int labelBgResId = typedArray.getResourceId(R.styleable.flow_view_flowBackground, 0);
            mFlowBg = mContext.getResources().getDrawable(labelBgResId);
            typedArray.recycle();
        }
    }

    @Override
    public void onClick(View v) {
        if (v instanceof TextView) {
            TextView tag = (TextView) v;

            setMaxSelect(tag.isSelected(), tag);
//            if (mOnFlowClick != null) {
//                mOnFlowClick.onFlowClick(tag, tag.getTag(KEY_DATA), (Integer) tag.getTag(KEY_POSITION));
//            }
        }
    }

    public void setOnClick(OnFlowClickListener onFlowClickListener) {
        mOnFlowClick = onFlowClickListener;
    }


    public enum SelectType {

        //不可选中，也不响应选中事件回调。（默认）
        NONE(1),
        //单选,可以反选。
        SINGLE(2),
        //单选,不可以反选。这种模式下，至少有一个是选中的，默认是第一个
        SINGLE_IRREVOCABLY(3),
        //多选
        MULTI(4);

        int value;

        SelectType(int value) {
            this.value = value;
        }

        static SelectType get(int value) {
            switch (value) {
                case 1:
                    return NONE;
                case 2:
                    return SINGLE;
                case 3:
                    return SINGLE_IRREVOCABLY;
                case 4:
                    return MULTI;
                default:
                    break;
            }
            return NONE;
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //获取子类个数
        int count = getChildCount();
        //获取手机屏幕宽度
        int maxWidth = ((MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft() - getPaddingRight()));
        //记录View的高度
        int contentHeight = 0;
        //记录一行中View的宽度和
        int lineWidth = 0;
        //记录多行中最大的宽度 做为ViewGroup的宽度
        int maxLineWidth = 0;
        //记录一行中最高的item 高度
        int maxItemHeight = 0;
        //是否是一行的开头
        boolean isBegin = true;

        //测量每一个子view
        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);

            if (!isBegin) {
                lineWidth += mWordMargin;
            } else {
                isBegin = false;
            }

            //换行
            if (lineWidth + childView.getMeasuredWidth() >= maxWidth) {
                //记录当前行的高度
                contentHeight += maxItemHeight + mLineMargin;
                maxItemHeight = 0;
                maxLineWidth = Math.max(maxLineWidth, lineWidth);
                lineWidth = 0;
                isBegin = true;
            }

            //上一个View与这个一View相比 保存最高的高度
            maxItemHeight = Math.max(childView.getMeasuredHeight(), maxItemHeight);
            //记录一行中各个子View 相加起来起来的高度
            lineWidth += childView.getMeasuredWidth();
        }

        //最后View的高度
        contentHeight += maxItemHeight;
        //最后的宽度
        maxLineWidth = Math.max(maxLineWidth, lineWidth);

        setMeasuredDimension(
                measureWidth(widthMeasureSpec, maxLineWidth),
                measureHeight(heightMeasureSpec, contentHeight)

        );

    }


    private int measureWidth(int widthMeasureSpec, int maxLineWidth) {
        int result = 0;
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);

        //如果ViewGroup:layout_width 为Match_parent或固定值
        if (specMode == MeasureSpec.EXACTLY) {
            return specSize;
        } else {
            //如果ViewGroup:layout_height 为Warp_content
            result = maxLineWidth + getPaddingLeft() + getPaddingRight();
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        //当如设置有minWidth的时候设置minWidth的值
        result = Math.max(result, getSuggestedMinimumWidth());

        return result;

    }

    private int measureHeight(int heightMeasureSpec, int maxLineHeight) {
        int result = 0;
        int specMode = MeasureSpec.getMode(heightMeasureSpec);
        int specSize = MeasureSpec.getSize(heightMeasureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            return specSize;
        } else {
            result = maxLineHeight + getPaddingTop() + getPaddingBottom();
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        result = Math.max(result, getSuggestedMinimumHeight());

        return result;
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int x = getPaddingLeft();
        int y = getPaddingTop();

        int contentWidth = ((r - l));
        int maxItemHeight = 0;

        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);

            //需要换行
            if (x + childView.getMeasuredWidth() + getPaddingRight() > contentWidth) {
                x = getPaddingLeft();
                y += mLineMargin + maxItemHeight;
                maxItemHeight = 0;
            }

            childView.layout(x, y, x + childView.getMeasuredWidth(), y + childView.getMeasuredHeight());
            x += childView.getMeasuredWidth() + mWordMargin;
            maxItemHeight = Math.max(maxItemHeight, childView.getMeasuredHeight());

        }
    }


    public <T> void setTags(List<T> tags, FlowTextProvider<T> provider) {
        //清空原有的标签
        removeAllViews();

        if (tags != null) {
            int size = tags.size();
            for (int i = 0; i < size; i++) {
                addLabel(tags.get(i), i, provider);
            }
        }
    }


    public void setMaxSelect(boolean select, TextView flow) {

        flow.setSelected(!select);

        if (mOnFlowClick != null) {
            mOnFlowClick.onFlowClick(flow, flow.getTag(KEY_DATA),
                    (int) flow.getTag(KEY_POSITION), flow.isSelected());
        }
    }

    private <T> void addLabel(T data, int position, FlowTextProvider<T> provider) {
        final TextView tags = new TextView(mContext);
        tags.setPadding(mTextPaddingLeft, mTextPaddingTop, mTextPaddingRight, mTextPaddingBottom);
        tags.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
        tags.setTextColor(mTextColor != null ? mTextColor : ColorStateList.valueOf(0xFF000000));
        //设置给label的背景(Drawable)是一个Drawable对象的拷贝，
        // 因为如果所有的标签都共用一个Drawable对象，会引起背景错乱。
        tags.setBackgroundDrawable(mFlowBg.getConstantState().newDrawable());
        tags.setTag(KEY_DATA, data);
        tags.setTag(KEY_POSITION, position);
        tags.setOnClickListener(this);
        addView(tags);
        tags.setText(provider.getFlowText(tags, position, data));
    }


    public interface OnFlowClickListener {

        /**
         * @param label    标签
         * @param data     标签对应的数据
         * @param position 标签位置
         */
        void onFlowClick(TextView label, Object data, int position, boolean isSelect);
    }

    public interface FlowTextProvider<T> {

        /**
         * 根据data和position返回label需要需要显示的数据。
         *
         * @param tag
         * @param position
         * @param data
         * @return
         */
        CharSequence getFlowText(TextView tag, int position, T data);
    }


}
