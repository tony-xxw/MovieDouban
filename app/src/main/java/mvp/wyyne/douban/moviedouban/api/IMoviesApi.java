package mvp.wyyne.douban.moviedouban.api;


import io.reactivex.Observable;
import mvp.wyyne.douban.moviedouban.api.bean.Article;
import mvp.wyyne.douban.moviedouban.api.bean.HotBean;
import retrofit2.http.GET;
import retrofit2.http.Path;

import static android.R.attr.id;

/**
 * Created by XXW on 2017/6/5.
 */

public interface IMoviesApi {

    //正在热映
    @GET("movie/in_theaters?apikey=0df993c66c0c636e29ecbb5344252a4a")
    Observable<HotBean> getHotList();

    //即将上映
    @GET("movie/coming_soon")
    Observable<HotBean> getFutureList();

    //电影条目信息
    @GET("movie/subject/{id}?apikey=0df993c66c0c636e29ecbb5344252a4a")
    Observable<Article> getArticle(@Path("id") String id);
}

