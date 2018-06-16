package mvp.wyyne.douban.moviedouban.movie;

import mvp.wyyne.douban.moviedouban.home.IPresent;

/**
 * @author Wynne
 * @date 2018/6/16
 */

public interface IMoviePresent extends IPresent {
    /**
     * Top250
     */
    void getTopMovie();

    /**
     * 本周口碑榜
     */
    void getWeekly();

    /**
     * 热门榜
     */
    void getNowMovies();

    /**
     * 北美排行
     */
    void getUsBox();


}
