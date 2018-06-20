package mvp.wyyne.douban.moviedouban.hot.city;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.adapter.viewpage.SubjectTitlePageAdapter;
import mvp.wyyne.douban.moviedouban.home.base.BaseActivity;

/**
 * @author XXW
 * @date 2017/8/15.
 */

public class CityActivity extends BaseActivity {
    @BindView(R.id.tv_stills_title)
    TextView mTvCtTitle;
    @BindView(R.id.iv_share)
    ImageView mIvShare;
    @BindView(R.id.ll_title)
    RelativeLayout mLlTitle;
    @BindView(R.id.tl_ct)
    TabLayout mTlCt;
    @BindView(R.id.vp_ct)
    ViewPager mVpCt;


    private SubjectTitlePageAdapter mAdapter;
    private List<String> mTitle;
    private String[] mStrings = {"国内", "海外"};
    private List<Fragment> mList;


    @Override
    protected void refresh() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_city;
    }

    @Override
    protected void initView() {
        mLlTitle.setBackgroundColor(getResources().getColor(R.color.white));
        mTvCtTitle.setText(getString(R.string.city_select));

        mTlCt.addTab(mTlCt.newTab().setText(mStrings[0]));
        mTlCt.addTab(mTlCt.newTab().setText(mStrings[1]));

        mTitle = Arrays.asList(mStrings);
        mList = new ArrayList<>();

        mList.add(new ChinaFragment());
        mList.add(new ForeignFragment());

        mAdapter = new SubjectTitlePageAdapter(getSupportFragmentManager());
        mAdapter.setTitleList(mTitle);
        mAdapter.setFragment(mList);
        mVpCt.setAdapter(mAdapter);


        mTlCt.setupWithViewPager(mVpCt);
    }

    @OnClick({R.id.iv_back})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            default:
                break;
        }
    }


}
