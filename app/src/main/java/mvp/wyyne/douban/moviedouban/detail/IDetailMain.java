package mvp.wyyne.douban.moviedouban.detail;

import mvp.wyyne.douban.moviedouban.api.bean.Article;
import mvp.wyyne.douban.moviedouban.home.IMain;

/**
 * @author XXW
 * @date 2017/6/19
 */

public interface IDetailMain extends IMain {


    /**
     * 绑定ViewPage
     */
    void onBindPage();

    /**
     * 初始化详情页海报图片
     *
     * @param article
     */
    void initMovieImg(Article article);

    /**
     * 初始化界面数据
     */
    void initMovieGrade();
}
