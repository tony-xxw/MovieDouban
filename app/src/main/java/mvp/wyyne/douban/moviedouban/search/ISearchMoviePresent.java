package mvp.wyyne.douban.moviedouban.search;

import java.util.List;

import mvp.wyyne.douban.moviedouban.api.bean.HotSearch;
import mvp.wyyne.douban.moviedouban.home.IPresent;

/**
 * @author Wynne
 * @date 2018/5/27
 */

public interface ISearchMoviePresent extends IPresent {


    /**
     * 获取电影条目列表
     *
     * @return
     */
    List<HotSearch> getSubjectsList();


    /**
     * 获取搜索结果
     *
     * @param text  关键字
     * @param start 开始
     * @param count 结束
     * @return
     */
    void searchMovieSubject(String text, String start, String count);


    /**
     * 处理搜索历史记录
     *
     * @return
     */
    List<String> handleHistorySet();


}
