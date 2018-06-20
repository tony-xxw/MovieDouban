package mvp.wyyne.douban.moviedouban.adapter.viewpage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @author XXW
 * @date 2017/7/22
 */

public class SubjectTitlePageAdapter extends FragmentPagerAdapter {

    private List<Fragment> mList;
    private List<String> mTitle;

    public SubjectTitlePageAdapter(FragmentManager fm) {
        super(fm);
    }


    public void setFragment(List<Fragment> list) {
        mList = list;
    }

    public void setTitleList(List<String> title) {
        mTitle = title;
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
        return mTitle.get(position);
    }
}
