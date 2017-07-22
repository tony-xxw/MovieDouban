package mvp.wyyne.douban.moviedouban.oneself;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.home.BaseFragment;

/**
 * Created by XXW on 2017/6/2.
 */

public class OneselfFragment extends BaseFragment {

    @BindView(R.id.iv_movie)
    ImageView mIvMovie;
    @BindView(R.id.iv_setting)
    ImageView mIvSetting;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout mToolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout mAppBar;
    @BindView(R.id.tl_detail)
    TabLayout mTlDetail;
    @BindView(R.id.vp_detail)
    ViewPager mVpDetail;
    @BindView(R.id.nv_detail)
    NestedScrollView mNvDetail;
    Unbinder unbinder;

    @Override
    protected void refresh() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_oneself;
    }

    @Override
    protected void initView() {
        mTlDetail.setTabMode(TabLayout.MODE_FIXED);
        mTlDetail.addTab(mTlDetail.newTab().setText("想看"));
        mTlDetail.addTab(mTlDetail.newTab().setText("在看"));
        mTlDetail.addTab(mTlDetail.newTab().setText("看过"));
        mTlDetail.addTab(mTlDetail.newTab().setText("影评"));
        mTlDetail.addTab(mTlDetail.newTab().setText("影人"));
        mTlDetail.setTabTextColors(ColorStateList.valueOf(ContextCompat.getColor(getActivity(), R.color.colorBlack)));
    }

}
