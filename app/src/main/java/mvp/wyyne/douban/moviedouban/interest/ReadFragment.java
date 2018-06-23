package mvp.wyyne.douban.moviedouban.interest;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

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

public class ReadFragment extends BaseFragment {
    @BindView(R.id.fvGroup)
    FlowView flowView;
    @BindView(R.id.tv_tag)
    TextView tvTag;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_read_interest;
    }

    @Override
    protected void initView() {
        ArrayList<FlowBean> testList = new ArrayList<>();
        testList.add(new FlowBean("Android", 1));
        testList.add(new FlowBean("IOS", 2));
        testList.add(new FlowBean("前端", 3));
        testList.add(new FlowBean("后台", 4));
        testList.add(new FlowBean("微信开发", 5));
        testList.add(new FlowBean("游戏开发", 6));
        testList.add(new FlowBean("Java", 7));
        testList.add(new FlowBean("JavaScript", 8));
        testList.add(new FlowBean("C++", 9));
        testList.add(new FlowBean("PHP", 10));
        testList.add(new FlowBean("Python", 11));
        testList.add(new FlowBean("Swift", 12));
        flowView.setTags(testList, new FlowView.FlowTextProvider<FlowBean>() {
            @Override
            public CharSequence getFlowText(TextView label, int position, FlowBean data) {
                return data.getName();
            }
        });

    }


    @OnClick(R.id.tv_tag)
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
            default:
                break;
        }
    }


}
