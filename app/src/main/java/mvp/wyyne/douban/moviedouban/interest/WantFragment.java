package mvp.wyyne.douban.moviedouban.interest;

import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
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
import mvp.wyyne.douban.moviedouban.utils.ResourcesUtils;
import mvp.wyyne.douban.moviedouban.widget.FlowView;
import mvp.wyyne.douban.moviedouban.widget.StarView;

/**
 * 想看
 *
 * @author Wynne
 * @date 2018/6/23
 */

public class WantFragment extends BaseFragment implements FlowView.OnFlowClickListener, TextWatcher {
    @BindView(R.id.sv_star)
    StarView svStar;
    @BindView(R.id.tv_tag)
    TextView tvTag;
    @BindView(R.id.fvGroup)
    FlowView fvGroup;
    @BindView(R.id.et_reason)
    EditText etReason;
    @BindView(R.id.tv_label)
    TextView tvLabel;
    private List<String> mLabelList;

    private List<FlowBean> mList;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_want_interest;
    }

    @Override
    protected void initView() {
        initLabel();
        etReason.addTextChangedListener(this);
        fvGroup.setVisibility(View.GONE);
        tvTag.setCompoundDrawables(null, null,
                ResourcesUtils.getDrawable(R.drawable.ic_group_tag_down, getActivity()), null);
    }

    private void initLabel() {
        mList = new ArrayList<>();
        mLabelList = new ArrayList<>();
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
        fvGroup.setOnClick(this);
        fvGroup.setTags(mList, new FlowView.FlowTextProvider<FlowBean>() {
            @Override
            public CharSequence getFlowText(TextView tag, int position, FlowBean data) {
                return data.getName();
            }
        });
    }


    @Override
    public void onFlowClick(TextView label, Object data, int position, boolean isSelect) {

    }

    @OnClick({R.id.tv_tag, R.id.et_reason})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_tag:
                Drawable.ConstantState drawableState = getResources().getDrawable(R.drawable.ic_group_tag_down).getConstantState();
                Drawable.ConstantState currentState = tvTag.getCompoundDrawables()[2].getConstantState();
                if (currentState != null && currentState.equals(drawableState)) {
                    Drawable drawableRight = ResourcesUtils.getDrawable(R.drawable.ic_group_tag_up, getActivity());
                    tvTag.setCompoundDrawables(null, null, drawableRight, null);
                    fvGroup.setVisibility(View.VISIBLE);
                } else {
                    Drawable drawableRight = ResourcesUtils.getDrawable(R.drawable.ic_group_tag_down, getActivity());
                    tvTag.setCompoundDrawables(null, null, drawableRight, null);
                    fvGroup.setVisibility(View.GONE);
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


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        ((InterestActivity) getActivity()).mReason = s.toString();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
