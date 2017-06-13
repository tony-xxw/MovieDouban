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
    @GET("movie/in_theaters")
    Observable<HotBean> getHotList();

    //即将上映
    @GET("movie/coming_soon")
    Observable<HotBean> getFutureList();

    @GET("movie/{id}")
    Observable<Article> getMovieDate(@Path("id") String id);
}

