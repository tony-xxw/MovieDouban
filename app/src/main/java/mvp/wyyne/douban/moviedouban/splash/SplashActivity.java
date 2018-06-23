package mvp.wyyne.douban.moviedouban.splash;

import android.content.Intent;
import android.os.Handler;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.home.MainActivity;
import mvp.wyyne.douban.moviedouban.home.base.BaseActivity;
import mvp.wyyne.douban.moviedouban.utils.StatusUtils;

/**
 * 闪屏页面
 *
 * @author Wynne
 * @date 2018/6/12
 */

public class SplashActivity extends BaseActivity {
    @Override
    protected void refresh() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onResume() {
        super.onResume();
        StatusUtils.setStatusColor(this, R.color.white, false);
    }

    @Override
    protected void initView() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 2000);
    }
}
