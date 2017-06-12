package mvp.wyyne.douban.moviedouban.hot.main;


import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import butterknife.BindView;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.home.BaseFragment;


/**
 * Created by XXW on 2017/6/2.
 */

public class HotFragment extends BaseFragment implements HotView {
    protected static final String TAG ="HotFragment";
    @BindView(R.id.hot_tl)
    TabLayout mHotTl;
    @BindView(R.id.hot_vp)
    ViewPager mHotVp;
    private HotPresenterImp mPresenterImp;


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
        mPresenterImp = new HotPresenterImp(this, getChildFragmentManager());
        mHotTl.setTabMode(TabLayout.MODE_FIXED);
        mHotTl.addTab(mHotTl.newTab().setText("正在热映"));
        mHotTl.addTab(mHotTl.newTab().setText("即将上映"));
        mPresenterImp.initPage(mHotVp);
    }


    @Override
    public void onBindPage() {
        mHotTl.setupWithViewPager(mHotVp);

    }



}
