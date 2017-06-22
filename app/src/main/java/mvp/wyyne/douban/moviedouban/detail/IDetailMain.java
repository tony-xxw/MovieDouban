package mvp.wyyne.douban.moviedouban.detail;

import mvp.wyyne.douban.moviedouban.api.bean.Article;
import mvp.wyyne.douban.moviedouban.home.IMain;

/**
 * Created by XXW on 2017/6/19.
 */

public interface IDetailMain extends IMain {

    //初始化详情页海报图片
    void initMovieImg(Article article);

    //初始化界面数据
    void initMovieGrade();

    //绑定ViewPage
    void onBindPage();
}
