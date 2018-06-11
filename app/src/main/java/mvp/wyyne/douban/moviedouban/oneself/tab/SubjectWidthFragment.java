package mvp.wyyne.douban.moviedouban.oneself.tab;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
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

    public String[] mString = {"想看", "在看", "看过", "影评", "影人"};
    private List<String> mTitleList;
    private List<Fragment> mFragments;


    public static SubjectWidthFragment getInstance() {
        return new SubjectWidthFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_subject;
    }

    @Override
    protected void initView() {
        mTitleList = Arrays.asList(mString);
        mFragments = new ArrayList<>();
        //初始化TabLayout
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        for (String title : mTitleList) {
            tabLayout.addTab(tabLayout.newTab().setText(title));
        }
        initFragment();
        initPage();

    }

    private void initPage() {
        SubjectTitlePageAdapter mAdapter = new SubjectTitlePageAdapter(getChildFragmentManager());
        mAdapter.setFragment(mFragments);
        mAdapter.setTitleList(mTitleList);
        tabLayout.setupWithViewPager(vpSubject);
        vpSubject.setAdapter(mAdapter);
    }


    public void initFragment() {
        mFragments.add(LanHuFragment.getInstance());
        mFragments.add(SightFragment.getInstance());
        mFragments.add(ReadFragment.getInstance());
        mFragments.add(ReviewFragment.getInstance());
        mFragments.add(CastFragment.getInstance());

    }


}
