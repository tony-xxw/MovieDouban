package mvp.wyyne.douban.moviedouban.movie.top;

import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.home.base.BaseActivity;
import mvp.wyyne.douban.moviedouban.utils.StatusUtils;

/**
 * @author Wynne
 * @date 2018/6/20
 */

public class TopActivity extends BaseActivity {
    @BindView(R.id.tl_top)
    TabLayout tlTop;
    @BindView(R.id.vp_top)
    ViewPager vpTop;
    @BindView(R.id.tv_stills_title)
    TextView tvStillsTitle;

    @Override
    protected void refresh() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_top_movie;
    }

    @Override
    protected void initView() {
        tvStillsTitle.setText("豆瓣Top250");
        tlTop.setTabMode(TabLayout.MODE_FIXED);
        tlTop.addTab(tlTop.newTab().setText("Top1-50"));
        tlTop.addTab(tlTop.newTab().setText("51-100"));
        tlTop.addTab(tlTop.newTab().setText("101-150"));
        tlTop.addTab(tlTop.newTab().setText("151-200"));
        tlTop.addTab(tlTop.newTab().setText("201-250"));
        tlTop.setupWithViewPager(vpTop);
    }

    @Override
    protected void onResume() {
        super.onResume();
        StatusUtils.setStatusBarActivity(this, false, ContextCompat.getColor(this, R.color.white));
    }

    @OnClick({R.id.iv_back})
    public void onViewClick() {
        finish();
    }

}
