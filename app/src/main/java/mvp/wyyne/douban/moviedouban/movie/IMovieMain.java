package mvp.wyyne.douban.moviedouban.movie;

import java.util.List;

import mvp.wyyne.douban.moviedouban.api.bean.Subjects;
import mvp.wyyne.douban.moviedouban.api.bean.UsSubjects;
import mvp.wyyne.douban.moviedouban.api.bean.WeeklySubject;
import mvp.wyyne.douban.moviedouban.home.IMain;

/**
 * @author Wynne
 * @date 2018/6/16
 */

public interface IMovieMain extends IMain {
    /**
     * 通知刷新
     *
     * @param list 热门
     */
    void notifyNowRefresh(List<Subjects> list);

    /**
     * 通知刷新
     *
     * @param list 北美
     */
    void notifyUsRefresh(List<UsSubjects> list);

    /**
     * 通知刷新
     *
     * @param list 口碑
     */
    void notifyWeeklyRefresh(List<WeeklySubject> list);

    /**
     * 通知刷新
     *
     * @param list Top
     */
    void notifyTopRefresh(List<Subjects> list);


}
