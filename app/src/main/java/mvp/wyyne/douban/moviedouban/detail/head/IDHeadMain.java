package mvp.wyyne.douban.moviedouban.detail.head;

import java.util.List;

import mvp.wyyne.douban.moviedouban.api.bean.Article;
import mvp.wyyne.douban.moviedouban.api.bean.Casts;
import mvp.wyyne.douban.moviedouban.api.bean.Directors;
import mvp.wyyne.douban.moviedouban.api.bean.Photos;
import mvp.wyyne.douban.moviedouban.home.IMain;

/**
 * @author XXW
 * @date 2017/6/30
 */

public interface IDHeadMain extends IMain {

    /**
     * 初始化电影列表
     *
     * @param article 电影列表数据
     */
    void initMovieInfo(Article article);

    /**
     * 初始化演员列表
     *
     * @param castsList 演员列表
     * @param directors 导演列表
     */
    void initCastInfo(List<Casts> castsList, List<Directors> directors);

    /**
     * 初始化演员剧照
     *
     * @param photosList 剧照列表
     */
    void initCastPhoto(List<Photos> photosList);

}
