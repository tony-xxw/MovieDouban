package mvp.wyyne.douban.moviedouban.interest;

import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.bean.FlowBean;
import mvp.wyyne.douban.moviedouban.home.base.BaseFragment;
import mvp.wyyne.douban.moviedouban.widget.FlowView;
import mvp.wyyne.douban.moviedouban.widget.StarView;

/**
 * 想看
 *
 * @author Wynne
 * @date 2018/6/23
 */

public class WantFragment extends BaseFragment implements FlowView.OnFlowClickListener {
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

    private List<FlowBean> mList;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_want_interest;
    }

    @Override
    protected void initView() {
        initLabel();
    }

    private void initLabel() {
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
}
