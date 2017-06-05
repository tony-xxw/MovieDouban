package mvp.wyyne.douban.moviedouban.hot;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by XXW on 2017/6/4.
 */

public class HotPresenterImp implements HotPresenter {
    private HotView mHotView;
    private ViewPager mViewPager;
    private List<Fragment> mHotList;
    private HotPagerAdapter mAdapter;
    private FragmentManager mFragmentManager;


    public HotPresenterImp(HotView hotView, FragmentManager manager) {
        mHotView = hotView;
        mFragmentManager = manager;
        mHotList = new ArrayList<>();
    }


    @Override
    public void initPage(ViewPager viewPager) {
        mViewPager = viewPager;
        mHotList.add(new HotCurrentFragment());
        mHotList.add(new HotFutureFragment());
        mAdapter = new HotPagerAdapter(mFragmentManager);
        mAdapter.setFragment(mHotList);
        mViewPager.setAdapter(mAdapter);
        mHotView.onBindPage();

    }


    class HotPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> mList;
        private String[] mTitl = {"正在热映", "即将上映"};

        public HotPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        private void setFragment(List<Fragment> list) {
            mList = list;
        }

        @Override
        public Fragment getItem(int position) {
            return mList.get(position);
        }

        @Override
        public int getCount() {
            return mList.size();
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return mTitl[position];
        }
    }
}
