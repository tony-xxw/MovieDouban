package mvp.wyyne.douban.moviedouban.interest;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.OnClick;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.home.base.BaseActivity;
import mvp.wyyne.douban.moviedouban.utils.ResourcesUtils;
import mvp.wyyne.douban.moviedouban.utils.StatusUtils;

/**
 * 想看-看过
 *
 * @author Wynne
 * @date 2018/6/12
 */

public class InterestActivity extends BaseActivity {
    public static final String TAG = InterestActivity.class.getSimpleName();
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;


    @Override
    protected void refresh() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        StatusUtils.setStatusBarColor(this, ResourcesUtils.getColor(R.color.white, this), true);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_interest;
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.GONE);
        ivClose.setVisibility(View.VISIBLE);
        btnConfirm.setVisibility(View.VISIBLE);
    }


    @OnClick({R.id.iv_close, R.id.btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                finish();
                break;
            case R.id.btn_confirm:
                //标记
                break;
            default:
                break;
        }
    }


}
