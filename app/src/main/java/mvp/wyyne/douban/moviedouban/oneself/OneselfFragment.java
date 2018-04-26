package mvp.wyyne.douban.moviedouban.oneself;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.adapter.viewpage.BaseTitlePageAdapter;
import mvp.wyyne.douban.moviedouban.home.base.BaseFragment;
import mvp.wyyne.douban.moviedouban.oneself.cast.CastFragment;
import mvp.wyyne.douban.moviedouban.oneself.lanhu.LanHuFragment;
import mvp.wyyne.douban.moviedouban.oneself.read.ReadFragment;
import mvp.wyyne.douban.moviedouban.oneself.review.ReviewFragment;
import mvp.wyyne.douban.moviedouban.oneself.setting.SettingActivity;
import mvp.wyyne.douban.moviedouban.oneself.sight.SightFragment;
import mvp.wyyne.douban.moviedouban.utils.StatusUtils;

/**
 * @author XXW
 * @date 2017/6/2
 */

public class OneselfFragment extends BaseFragment<OneselfPresent> implements OneselfMain, AppBarLayout.OnOffsetChangedListener {
    public static final String TAG = OneselfFragment.class.getSimpleName();
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
    @BindView(R.id.rl_title_layout)
    RelativeLayout mRlTitleLayout;
    public String[] mString = {"讨论","想看", "在看", "看过", "影评", "影人"};
    private List<String> mTitle;
    private List<Fragment> mFragments;
    private BaseTitlePageAdapter mAdapter;


    public static OneselfFragment getInstance() {
        return new OneselfFragment();
    }


    @Override
    protected void refresh() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_oneself;
    }

    @Override
    protected void initView() {
        StatusUtils.setStatusColor(getActivity(), getResources().getColor(R.color.color_green), false);
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
        mFragments.add(LanHuFragment.getInstance());
        mFragments.add(SightFragment.getInstance());
        mFragments.add(ReadFragment.getInstance());
        mFragments.add(ReviewFragment.getInstance());
        mFragments.add(CastFragment.getInstance());
        mAdapter = new BaseTitlePageAdapter(getChildFragmentManager());
        mAdapter.setFragment(mFragments);
        mAdapter.setTitleList(mTitle);
        mVpDetail.setAdapter(mAdapter);
        mTlDetail.setupWithViewPager(mVpDetail);

        mAppBar.addOnOffsetChangedListener(this);
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }


    @OnClick(R.id.iv_setting)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(), SettingActivity.class);
        startActivity(intent);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        Log.d("XXW", " verticalOffset: " + verticalOffset);
        if (verticalOffset == 0) {
            mRlTitleLayout.setVisibility(View.GONE);
        } else {
            mRlTitleLayout.setVisibility(View.VISIBLE);
        }
    }
}
