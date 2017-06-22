package mvp.wyyne.douban.moviedouban.detail;

import android.support.v4.view.ViewPager;

import mvp.wyyne.douban.moviedouban.home.IPresent;

/**
 * Created by XXW on 2017/6/19.
 */

public interface IDetailPresent extends IPresent {

    /**
     * 获取电影条目信息
     *
     * @param subjectId 电影Id
     */
    void getArticle(String subjectId);

    void initPage(ViewPager viewPager);
}
