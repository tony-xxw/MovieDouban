package mvp.wyyne.douban.moviedouban.interest;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.bean.FlowBean;
import mvp.wyyne.douban.moviedouban.home.base.BaseFragment;
import mvp.wyyne.douban.moviedouban.widget.FlowView;

/**
 * 看过
 *
 * @author Wynne
 * @date 2018/6/23
 */

public class ReadFragment extends BaseFragment implements FlowView.OnFlowClickListener {
    @BindView(R.id.fvGroup)
    FlowView flowView;
    @BindView(R.id.tv_tag)
    TextView tvTag;
    @BindView(R.id.et_reason)
    EditText etReason;
    private List<FlowBean> mList;
    private List<String> mLabelList;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_read_interest;
    }

    @Override
    protected void initView() {
        initLabel();
        etReason.requestFocus();

    }

    private void initLabel() {
        mLabelList = new ArrayList<>();
        mList = new ArrayList<>();
        mList.add(new FlowBean("2018", 1));
        mList.add(new FlowBean("美国", 2));
        mList.add(new FlowBean("科幻", 3));
        mList.add(new FlowBean("动作", 4));
        mList.add(new FlowBean("探险", 5));
        mList.add(new FlowBean("3D", 6));
        mList.add(new FlowBean("恐怖", 7));
        mList.add(new FlowBean("惊悚", 8));
        mList.add(new FlowBean("幽默", 9));
        mList.add(new FlowBean("喜剧", 10));
        mList.add(new FlowBean("无厘头", 11));
        mList.add(new FlowBean("爱情", 12));
        mList.add(new FlowBean("青春", 12));
        flowView.setOnClick(this);
        flowView.setTags(mList, new FlowView.FlowTextProvider<FlowBean>() {
            @Override
            public CharSequence getFlowText(TextView tag, int position, FlowBean data) {
                return data.getName();
            }
        });
    }


    @OnClick({R.id.tv_tag, R.id.et_reason})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.tv_tag:
                Drawable.ConstantState drawableState = getResources().getDrawable(R.drawable.ic_group_tag_down).getConstantState();
                Drawable.ConstantState currentState = tvTag.getCompoundDrawables()[2].getConstantState();
                if (currentState != null && currentState.equals(drawableState)) {
                    Drawable drawableRight = getResources().getDrawable(R.drawable.ic_group_tag_up);
                    drawableRight.setBounds(0, 0, drawableRight.getMinimumWidth(), drawableRight.getMinimumHeight());
                    tvTag.setCompoundDrawables(null, null, drawableRight, null);
                    flowView.setVisibility(View.VISIBLE);
                } else {
                    Drawable drawableRight = getResources().getDrawable(R.drawable.ic_group_tag_down);
                    drawableRight.setBounds(0, 0, drawableRight.getMinimumWidth(), drawableRight.getMinimumHeight());
                    tvTag.setCompoundDrawables(null, null, drawableRight, null);
                    flowView.setVisibility(View.GONE);
                }
                break;
            case R.id.et_reason:
                etReason.setCursorVisible(true);
                break;
            default:
                break;
        }
    }


    /**
     * @return 获取标签String
     */
    public String getLabelString() {
        String labelString;
        if (mLabelList.size() != 0) {
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < mLabelList.size(); i++) {
                if (i != mLabelList.size() - 1) {
                    buffer.append(mLabelList.get(i) + "/");
                } else {
                    buffer.append(mLabelList.get(i));
                }
            }
            labelString = buffer.toString();
        } else {
            labelString = "";
        }
        return labelString;
    }

    /**
     * 获取想看理由
     *
     * @return
     */
    public String getReasonString() {
        return etReason.getText().toString();
    }

    @Override
    public void onFlowClick(TextView label, Object data, int position, boolean isSelect) {
        FlowBean bean = (FlowBean) data;
        if (isSelect) {
            mLabelList.add(bean.getName());
        } else {
            if (mLabelList.contains(bean.getName())) {
                mLabelList.remove(bean.getName());
            }
        }
    }


}
