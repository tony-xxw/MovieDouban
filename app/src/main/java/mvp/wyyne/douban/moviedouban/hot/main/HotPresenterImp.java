package mvp.wyyne.douban.moviedouban.hot.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mvp.wyyne.douban.moviedouban.adapter.viewpage.SubjectTitlePageAdapter;
import mvp.wyyne.douban.moviedouban.hot.current.HotCurrentFragment;
import mvp.wyyne.douban.moviedouban.hot.future.HotFutureFragment;

/**
 * @author XXW
 * @date 2017/6/4
 */

public class HotPresenterImp implements HotPresenter {
    private HotView mHotView;
    private List<Fragment> mHotList = new ArrayList<>();
    private FragmentManager mFragmentManager;
    private String[] title = {"正在热映", "即将上映"};
    private List<String> titleList;


    public HotPresenterImp(HotView hotView, FragmentManager manager) {
        mHotView = hotView;
        mFragmentManager = manager;
        titleList = Arrays.asList(title);
    }


    @Override
    public void initPage(ViewPager viewPager) {
        mHotList.add(new HotCurrentFragment());
        mHotList.add(new HotFutureFragment());
        SubjectTitlePageAdapter mAdapter = new SubjectTitlePageAdapter(mFragmentManager);
        mAdapter.setFragment(mHotList);
        mAdapter.setTitleList(titleList);
        viewPager.setAdapter(mAdapter);
        mHotView.onBindPage();

    }

    @Override
    public void getData() {

    }


}
