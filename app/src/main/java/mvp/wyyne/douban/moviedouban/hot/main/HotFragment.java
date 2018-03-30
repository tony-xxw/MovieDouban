package mvp.wyyne.douban.moviedouban.hot.main;


import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import butterknife.BindView;
import butterknife.OnClick;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.home.base.BaseFragment;
import mvp.wyyne.douban.moviedouban.hot.city.CityActivity;


/**
 * @author XXW
 * @date 2017/6/2
 */

public class HotFragment extends BaseFragment<HotPresenterImp> implements HotView {
    public static final String TAG = HotFragment.class.getSimpleName();
    @BindView(R.id.hot_tl)
    TabLayout mHotTl;
    @BindView(R.id.hot_vp)
    ViewPager mHotVp;


    public static HotFragment getInstance() {
        return new HotFragment();
    }

    @Override
    protected void refresh() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initView() {
        //Fragment里 嵌套Fragment的Manager要用getChildFragmentManager()
        mPresent = new HotPresenterImp(this, getChildFragmentManager());
        mHotTl.setTabMode(TabLayout.MODE_FIXED);
        mHotTl.addTab(mHotTl.newTab().setText("正在热映"));
        mHotTl.addTab(mHotTl.newTab().setText("即将上映"));
        mPresent.initPage(mHotVp);
    }


    @Override
    public void onBindPage() {
        mHotTl.setupWithViewPager(mHotVp);

    }


    @OnClick(R.id.tv_city)
    public void onViewClicked() {
        startActivity(new Intent(getActivity(), CityActivity.class));
    }
}
