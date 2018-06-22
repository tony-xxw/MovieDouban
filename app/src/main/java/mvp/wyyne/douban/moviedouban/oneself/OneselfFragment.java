package mvp.wyyne.douban.moviedouban.oneself;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import mvp.wyyne.douban.moviedouban.AndroidApplication;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.home.base.BaseFragment;
import mvp.wyyne.douban.moviedouban.login.LoginActivity;
import mvp.wyyne.douban.moviedouban.oneself.ticket.MovieTicketActivity;
import mvp.wyyne.douban.moviedouban.utils.ResourcesUtils;
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
    @BindView(R.id.avatar)
    CircleImageView avatar;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.my_tickets)
    TextView myTickets;
    @BindView(R.id.login_info_container)
    LinearLayout loginInfoContainer;
    @BindView(R.id.anonymous)
    TextView anonymous;
    @BindView(R.id.ctl_collapsing)
    CollapsingToolbarLayout ctlCollapsing;


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
        mAppBar.addOnOffsetChangedListener(this);
        mPresent = new OneselfImp();
        avatar.setBorderWidth(5);
        avatar.setBorderColor(Color.WHITE);
        initFragment();
    }


    @Override
    public void onResume() {
        super.onResume();
        initDate();
        initStatus(R.color.transparent, false);
    }

    private void initStatus(int color, boolean textLight) {
        StatusUtils.setStatusImage(
                getActivity(), color, textLight);

    }

    private void initDate() {
        if (AndroidApplication.getApplication().isLogin()) {
            avatar.setImageDrawable(getResources().getDrawable(R.drawable.ic_user_avatar));
            anonymous.setVisibility(View.GONE);
            loginInfoContainer.setVisibility(View.VISIBLE);
            name.setText(getString(R.string.user_name));
            mRlHeadLayout.setBackground(ResourcesUtils.getDrawable(R.drawable.ic_my_default_male_bg, getActivity()));
        } else {
            avatar.setImageDrawable(getResources().getDrawable(R.drawable.ic_user_default));
            anonymous.setVisibility(View.VISIBLE);
            loginInfoContainer.setVisibility(View.GONE);
            mRlHeadLayout.setBackground(ResourcesUtils.getDrawable(R.drawable.ic_my_default_bg, getActivity()));
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }


    @OnClick({R.id.iv_setting, R.id.info_container, R.id.my_tickets})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_setting:
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.info_container:
                if (!AndroidApplication.getApplication().isLogin()) {
                    Intent loginIntent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(loginIntent);
                }
                break;
            case R.id.my_tickets:
                //跳转电影票页面
                Intent ticket = new Intent(getActivity(), MovieTicketActivity.class);
                startActivity(ticket);
                break;
            default:
                break;
        }

    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (verticalOffset == 0) {
            //展开
            mTitleText.setVisibility(View.GONE);
            mRlTitleLayout.setBackgroundColor(getResources().getColor(R.color.transparent));
            mIvSetting.setImageDrawable(getResources().getDrawable(R.drawable.ic_setting_white));
            initStatus(R.color.transparent, false);
        } else if (Math.abs(verticalOffset) != mAppBar.getTotalScrollRange()) {
            //滑动中
            mTitleText.setVisibility(View.GONE);
            mIvMovie.setVisibility(View.GONE);
            mRlTitleLayout.setBackgroundColor(getResources().getColor(R.color.transparent));
        } else {
            //滑动结束
            mTitleText.setVisibility(View.VISIBLE);
            mIvMovie.setVisibility(View.VISIBLE);
            mIvSetting.setImageDrawable(getResources().getDrawable(R.drawable.ic_setting_black));
            mRlTitleLayout.setBackgroundColor(getResources().getColor(R.color.colorWhite));
            initStatus(R.color.transparent, true);
            if (AndroidApplication.getApplication().isLogin()) {
                mTitleText.setText("Wynne");
            } else {
                mTitleText.setText("请登录");
            }
        }
    }

    @Override
    public void initFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fl_oneself, SubjectWidthFragment.getInstance()).commit();
    }


}
