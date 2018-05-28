package mvp.wyyne.douban.moviedouban.search;

import java.util.List;

import mvp.wyyne.douban.moviedouban.api.bean.Subjects;
import mvp.wyyne.douban.moviedouban.api.model.SearchModelBean;
import mvp.wyyne.douban.moviedouban.home.IMain;

/**
 * @author Wynne
 * @date 2018/5/27
 */

public interface ISearchMovieMain extends IMain {

    /**
     * 通知刷新结果
     *
     * @param list 电影条目列表
     */
    void notifyResultRefresh(List<Subjects> list);


    /**
     * 通知刷新历史记录
     *
     * @param list
     */
    void notifyHistoryRefresh(List<SearchModelBean> list);

    /**
     * Set
     *
     * @param list
     */
    void setSubject(List<Subjects> list);

    /**
     * get
     *
     * @return
     */
    List<Subjects> getSubject();
}
