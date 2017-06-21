package mvp.wyyne.douban.moviedouban.detail;

import mvp.wyyne.douban.moviedouban.api.bean.Article;
import mvp.wyyne.douban.moviedouban.home.IMain;

/**
 * Created by XXW on 2017/6/19.
 */

public interface IDetailMain extends IMain {

    void initMovieImg(Article article);

    void initMovieGrade();
}
