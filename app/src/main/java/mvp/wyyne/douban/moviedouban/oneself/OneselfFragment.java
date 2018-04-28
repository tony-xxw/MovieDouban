package mvp.wyyne.douban.moviedouban.oneself;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
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
import mvp.wyyne.douban.moviedouban.home.base.BaseFragment;
import mvp.wyyne.douban.moviedouban.oneself.tab.CastFragment;
import mvp.wyyne.douban.moviedouban.oneself.tab.LanHuFragment;
import mvp.wyyne.douban.moviedouban.oneself.tab.ReadFragment;
import mvp.wyyne.douban.moviedouban.oneself.tab.ReviewFragment;
import mvp.wyyne.douban.moviedouban.oneself.tab.SightFragment;
import mvp.wyyne.douban.moviedouban.oneself.tab.SubjectWidthFragment;
import mvp.wyyne.douban.moviedouban.utils.StatusUtils;

/**
 * @author XXW
 * @date 2017/6/2
 */

public class OneselfFragment extends BaseFragment<OneselfPresent>
        implements OneselfMain, AppBarLayout.OnOffsetChangedListener {
    public static final String TAG = OneselfFragment.class.getSimpleName();
    @BindView(R.id.iv_movie)
    ImageView mIvMovie;
    @BindView(R.id.iv_setting)
    ImageView mIvSetting;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.app_bar)
    AppBarLayout mAppBar;
    @BindView(R.id.rl_title_layout)
    RelativeLayout mRlTitleLayout;
    @BindView(R.id.rl_head_layout)
    RelativeLayout mRlHeadLayout;
    @BindView(R.id.ct_layout)
    CoordinatorLayout ctLayout;
    @BindView(R.id.tv_title)
    TextView mTitleText;


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
        StatusUtils.setStatusColor(getActivity(), R.color.transparent, false);
        mAppBar.addOnOffsetChangedListener(this);
        mPresent = new OneselfImp();
        initFragment();
        Log.d("XXW", "Dpi-- " + getResources().getDisplayMetrics().density);


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

        if (verticalOffset == 0) {
            Log.d("XXW", "展开");
            mTitleText.setVisibility(View.GONE);
            mRlTitleLayout.setBackgroundColor(getResources().getColor(R.color.transparent));
            mIvSetting.setImageDrawable(getResources().getDrawable(R.drawable.ic_setting_white));
            StatusUtils.setStatusColor(getActivity(), getResources().getColor(R.color.color_green), false);
        } else if (Math.abs(verticalOffset) != mAppBar.getTotalScrollRange()) {
            //滑动中
            Log.d("XXW", "滑动中");
            mTitleText.setVisibility(View.GONE);
            mRlTitleLayout.setBackgroundColor(getResources().getColor(R.color.transparent));
        } else {

            //滑动结束
            Log.d("XXW", "折叠");
            mTitleText.setVisibility(View.VISIBLE);
            mIvSetting.setImageDrawable(getResources().getDrawable(R.drawable.ic_setting_black));
            mRlTitleLayout.setBackgroundColor(getResources().getColor(R.color.colorWhite));
            StatusUtils.setStatusColor(getActivity(), getResources().getColor(R.color.colorWhite), true);
        }
    }

    @Override
    public void initFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fl_oneself, SubjectWidthFragment.getInstance()).commit();
    }
}
