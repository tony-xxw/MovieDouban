package mvp.wyyne.douban.moviedouban.oneself;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
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
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout mToolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout mAppBar;
    @BindView(R.id.rl_title_layout)
    RelativeLayout mRlTitleLayout;


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
        mAppBar.addOnOffsetChangedListener(this);
        mPresent = new OneselfImp();
        initFragment();
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

    @Override
    public void initFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fl_oneself, SubjectWidthFragment.getInstance()).commit();
    }
}
