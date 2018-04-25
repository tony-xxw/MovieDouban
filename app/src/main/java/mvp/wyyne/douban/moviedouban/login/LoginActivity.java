package mvp.wyyne.douban.moviedouban.login;

import android.graphics.Color;

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
}
