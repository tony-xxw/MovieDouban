package mvp.wyyne.douban.moviedouban.adapter.viewpage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.List;

/**
 * Created by XXW on 2017/7/22.
 */

public class OneselfPageAdapter extends FragmentPagerAdapter {

    private List<Fragment> mList;
    private List<String> mTitle;

    public OneselfPageAdapter(FragmentManager fm) {
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
        Log.d("XXW", "title---" + mTitle.get(position));
        return mTitle.get(position);
    }
}
