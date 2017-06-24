package mvp.wyyne.douban.moviedouban.hot.main;

import android.support.v4.view.ViewPager;

import mvp.wyyne.douban.moviedouban.home.IPresent;

/**
 * Created by XXW on 2017/6/4.
 */

public interface HotPresenter extends IPresent{


    //初始化ViewPager
    public void initPage(ViewPager viewPager);
}
