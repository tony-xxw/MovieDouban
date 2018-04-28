package mvp.wyyne.douban.moviedouban.oneself.tab;


import android.content.res.ColorStateList;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

import butterknife.Unbinder;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.adapter.viewpage.SubjectTitlePageAdapter;
import mvp.wyyne.douban.moviedouban.home.base.BaseFragment;

/**
 * @author Wynne
 * @date 2018/4/27
 */

public class SubjectWidthFragment extends BaseFragment {
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.vp_subject)
    ViewPager vpSubject;

    public String[] mString = {"讨论", "想看", "在看", "看过", "影评", "影人"};
    private List<String> mTitle;
    private List<Fragment> mFragments;
    private SubjectTitlePageAdapter mAdapter;
    private boolean isLogin;


    public static SubjectWidthFragment getInstance() {
        return new SubjectWidthFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_subject;
    }

    @Override
    protected void initView() {
        mTitle = Arrays.asList(mString);
        mFragments = new ArrayList<>();
        //初始化TabLayout
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        for (String title : mTitle) {
            tabLayout.addTab(tabLayout.newTab().setText(title));
        }

        isLogin = false;
        initPage();

    }

    private void initPage() {
        initFragment();
        mAdapter = new SubjectTitlePageAdapter(getChildFragmentManager());
//        mAdapter.setFragment(mFragments);
//        mAdapter.setTitleList(mTitle);
//        vpSubject.setAdapter(mAdapter);

        tabLayout.setupWithViewPager(vpSubject);
    }


    public void initFragment() {
        if (isLogin) {
            mFragments.clear();
            mFragments.add(LanHuFragment.getInstance());
            mFragments.add(SightFragment.getInstance());
            mFragments.add(ReadFragment.getInstance());
            mFragments.add(ReviewFragment.getInstance());
            mFragments.add(CastFragment.getInstance());
            mFragments.add(CastFragment.getInstance());
        }
    }


}
