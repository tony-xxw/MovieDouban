package mvp.wyyne.douban.moviedouban.hot.main;


import androidx.viewpager.widget.ViewPager;

import mvp.wyyne.douban.moviedouban.home.IPresent;

/**
 * @author XXW
 * @date 2017/6/4
 */

public interface HotPresenter extends IPresent {


    /**
     * 初始化ViewPager
     *
     * @param viewPager 初始化的轮播图
     */
    void initPage(ViewPager viewPager);
}
