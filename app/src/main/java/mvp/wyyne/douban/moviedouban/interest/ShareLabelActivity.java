package mvp.wyyne.douban.moviedouban.interest;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.home.base.BaseActivity;
import mvp.wyyne.douban.moviedouban.utils.ResourcesUtils;
import mvp.wyyne.douban.moviedouban.utils.StatusUtils;

/**
 * 分享电影标签
 *
 * @author Wynne
 * @date 2018/6/28
 */

public class ShareLabelActivity extends BaseActivity {

    @BindView(R.id.iv_close_white)
    ImageView ivCloseWhite;
    @BindView(R.id.tv_title_center)
    TextView tvTitleCenter;
    @BindView(R.id.rl_content)
    RelativeLayout rlContent;
    @BindView(R.id.iv_back)
    ImageView ivBack;

    @Override
    protected void refresh() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_share_label;
    }

    @Override
    protected void initView() {
        StatusUtils.setStatusImage(this, R.color.transparent, true);
        ivCloseWhite.setVisibility(View.VISIBLE);
        ivBack.setVisibility(View.GONE);
        rlContent.setBackgroundColor(ResourcesUtils.getColor(R.color.transparent, this));
        tvTitleCenter.setText("标记分享");

    }


}
