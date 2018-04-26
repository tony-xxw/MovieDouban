package mvp.wyyne.douban.moviedouban.login;

import android.graphics.Color;
import android.view.View;

import butterknife.OnClick;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.home.base.BaseActivity;
import mvp.wyyne.douban.moviedouban.utils.StatusUtils;

/**
 * @author Wynne
 * @date 2018/4/24
 */

public class LoginActivity extends BaseActivity {
    @Override
    protected void refresh() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        StatusUtils.setStatusBarActivity(this, false, Color.TRANSPARENT);
    }


    @OnClick({R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            default:
                break;
        }
    }
}
