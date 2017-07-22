package mvp.wyyne.douban.moviedouban.oneself;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.adapter.viewpage.OneselfPageAdapter;
import mvp.wyyne.douban.moviedouban.home.BaseFragment;
import mvp.wyyne.douban.moviedouban.oneself.cast.CastFragment;
import mvp.wyyne.douban.moviedouban.oneself.lanhu.LanHuFragment;
import mvp.wyyne.douban.moviedouban.oneself.read.ReadFragment;
import mvp.wyyne.douban.moviedouban.oneself.review.ReviewFragment;
import mvp.wyyne.douban.moviedouban.oneself.sight.SightFragment;

/**
 * Created by XXW on 2017/6/2.
 */

public class OneselfFragment extends BaseFragment<OneselfPresent> implements OneselfMain {

    public static final String 影人 = "影人";
    public static final String 影人1 = "影人";
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
    public String[] mString = {"想看", "在看", "看过", "影评", "影人"};
    private List<String> mTitle;
    private List<Fragment> mFragments;
    private OneselfPageAdapter mAdapter;

    @Override
    protected void refresh() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_oneself;
    }

    @Override
    protected void initView() {
        mTitle = Arrays.asList(mString);
        mFragments = new ArrayList<>();
        //初始化TabLayout
        mTlDetail.setTabMode(TabLayout.MODE_FIXED);
        for (String title : mTitle) {
            mTlDetail.addTab(mTlDetail.newTab().setText(title));
            mTlDetail.addTab(mTlDetail.newTab().setText(title));
            mTlDetail.addTab(mTlDetail.newTab().setText(title));
            mTlDetail.addTab(mTlDetail.newTab().setText(title));
            mTlDetail.addTab(mTlDetail.newTab().setText(title));
        }

        mTlDetail.setTabTextColors(ColorStateList.valueOf(ContextCompat.getColor(getActivity(), R.color.colorBlack)));
        initPage();
    }

    public void initPage() {
        Log.d("XXW", "initPage");
        mFragments.add(LanHuFragment.getInstace());
        mFragments.add(SightFragment.getInstace());
        mFragments.add(ReadFragment.getInstace());
        mFragments.add(ReviewFragment.getInstace());
        mFragments.add(CastFragment.getInstace());
        mAdapter = new OneselfPageAdapter(getChildFragmentManager());
        mAdapter.setFragment(mFragments);
        mAdapter.setTitleList(mTitle);
        mVpDetail.setAdapter(mAdapter);
        mTlDetail.setupWithViewPager(mVpDetail);

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }
}
