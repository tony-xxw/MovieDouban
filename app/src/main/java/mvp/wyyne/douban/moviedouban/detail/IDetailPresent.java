package mvp.wyyne.douban.moviedouban.detail;


import androidx.viewpager.widget.ViewPager;

import mvp.wyyne.douban.moviedouban.home.IPresent;

/**
 * @author XXW
 * @date 2017/6/19
 */

public interface IDetailPresent extends IPresent {

    /**
     * 获取电影条目信息
     *
     * @param subjectId 电影Id
     */
    void getArticle(String subjectId);

    /**
     * 初始化ViewPage
     *
     * @param viewPager
     */
    void initPage(ViewPager viewPager);


}
