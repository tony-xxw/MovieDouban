package mvp.wyyne.douban.moviedouban.oneself.ticket;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.home.MainActivity;
import mvp.wyyne.douban.moviedouban.home.base.BaseActivity;

/**
 * 电影票
 *
 * @author Wynne
 * @date 2018/6/12
 */

public class MovieTicketActivity extends BaseActivity {
    @BindView(R.id.tv_stills_title)
    TextView tvStillsTitle;

    @Override
    protected void refresh() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ticket;
    }

    @Override
    protected void initView() {
        tvStillsTitle.setText("我的电影票");

    }


    @OnClick({R.id.iv_back, R.id.btn_be_on})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_be_on:
                Intent mainIntent = new Intent(this, MainActivity.class);
                startActivity(mainIntent);
                break;
            default:
                break;
        }

    }
}
