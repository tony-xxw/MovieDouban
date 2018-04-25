package mvp.wyyne.douban.moviedouban.hot.main;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.blankj.utilcode.util.BarUtils;

import butterknife.BindView;
import butterknife.OnClick;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.bean.User;
import mvp.wyyne.douban.moviedouban.home.base.BaseFragment;
import mvp.wyyne.douban.moviedouban.hot.city.CityActivity;
import mvp.wyyne.douban.moviedouban.utils.StatusUtils;


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
    @BindView(R.id.ll_hot)
    LinearLayout mLlHotLayout;


    public static HotFragment getInstance() {
        return new HotFragment();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initView() {
        StatusUtils.setStatusColor(getActivity(), Color.WHITE);
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
