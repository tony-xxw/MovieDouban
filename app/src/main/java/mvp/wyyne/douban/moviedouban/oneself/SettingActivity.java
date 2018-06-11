package mvp.wyyne.douban.moviedouban.oneself;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import mvp.wyyne.douban.moviedouban.AndroidApplication;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.home.base.BaseActivity;

/**
 * 设置页面
 *
 * @author XXW
 * @date 2017/8/3
 */

public class SettingActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_stills_title)
    TextView mTvStillsTitle;
    @BindView(R.id.iv_share)
    ImageView mIvShare;
    @BindView(R.id.ll_title)
    RelativeLayout mRlHeadLayout;
    @BindView(R.id.btn_exit)
    Button mBtnExit;

    @Override
    protected void refresh() {

    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        mIvShare.setVisibility(View.GONE);
        mTvStillsTitle.setText(getString(R.string.str_setting));
        mRlHeadLayout.setBackgroundColor(Color.WHITE);

        if (AndroidApplication.getApplication().Login()) {
            mBtnExit.setVisibility(View.VISIBLE);
        }

        initCache();
    }

    private void initCache() {

    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
