package mvp.wyyne.douban.moviedouban.api;


import io.reactivex.Observable;
import mvp.wyyne.douban.moviedouban.api.bean.HotBean;
import retrofit2.http.GET;

/**
 * Created by XXW on 2017/6/5.
 */

public interface IMoviesApi {

    @GET("movie/in_theaters")
    Observable<HotBean> getHotList();

    @GET("movie/coming_soon")
    Observable<HotBean> getFutureList();
}

