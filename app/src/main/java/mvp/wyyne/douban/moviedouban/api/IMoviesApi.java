package mvp.wyyne.douban.moviedouban.api;


import io.reactivex.Observable;
import mvp.wyyne.douban.moviedouban.api.bean.ActorInfo;
import mvp.wyyne.douban.moviedouban.api.bean.Article;
import mvp.wyyne.douban.moviedouban.api.bean.CastPhoto;
import mvp.wyyne.douban.moviedouban.api.bean.MovieSubject;
import mvp.wyyne.douban.moviedouban.api.bean.MoviesReviews;
import mvp.wyyne.douban.moviedouban.api.bean.Stills;
import mvp.wyyne.douban.moviedouban.api.bean.UsMovieSubject;
import mvp.wyyne.douban.moviedouban.api.bean.WeeklyMovieSubject;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * movie/search?q= &start= 开始&count=结束  搜索接口
 * movie/top250?start= 开始&count=结束  TOP250
 * movie/box  北美票房
 * movie/weekly 口碑榜
 * movie/celebrity/{id}/works 影人作品
 *
 * @author XXW
 * @date 2017/6/5
 */

public interface IMoviesApi {

    /**
     * 获取上映电影列表
     *
     * @return 上映电影实体类
     */
    @GET("movie/in_theaters?apikey=0df993c66c0c636e29ecbb5344252a4a")
    Observable<MovieSubject> getHotList();

    /**
     * 即将上映
     *
     * @return 即将上映
     */
    @GET("movie/coming_soon?apikey=0df993c66c0c636e29ecbb5344252a4a")
    Observable<MovieSubject> getFutureList();

    /**
     * 电影条目信息
     *
     * @param id
     * @return 条目信息
     */
    @GET("movie/subject/{id}?apikey=0df993c66c0c636e29ecbb5344252a4a")
    Observable<Article> getArticle(@Path("id") String id);

    /**
     * 电影长评论条目信息
     *
     * @return 评论bean
     * * @param id
     */
    @GET("movie/subject/{id}/reviews?apikey=0df993c66c0c636e29ecbb5344252a4a")
    Observable<MoviesReviews> getReviews(@Path("id") String id);

    /**
     * 电影剧照
     *
     * @return 剧照bean
     * * @param id
     */
    @GET("movie/subject/{id}/photos?apikey=0df993c66c0c636e29ecbb5344252a4a")
    Observable<Stills> getStillsPhotos(@Path("id") String id);

    /**
     * 影人剧照
     *
     * @return 剧照bean
     * * @param id
     */
    @GET("movie/celebrity/{id}/photos?apikey=0df993c66c0c636e29ecbb5344252a4a")
    Observable<CastPhoto> getCastPhotos(@Path("id") String id);

    /**
     * 影人条
     *
     * @param id
     * @return 影人Bean
     */
    @GET("movie/celebrity/{id}?apikey=0df993c66c0c636e29ecbb5344252a4a")
    Observable<ActorInfo> getCastArticle(@Path("id") String id);


    /**
     * 获取搜索结果
     *
     * @param text  搜索文本
     * @param start 开始
     * @param count 结束
     * @return 电影bean
     */
    @GET("movie/search?apikey=0df993c66c0c636e29ecbb5344252a4a")
    Observable<MovieSubject> searchMovieSubject(@Query("q") String text,
                                                @Query("start") String start,
                                                @Query("count") String count);

    /**
     * Top250
     *
     * @param start 开始
     * @param count 结束
     * @return 电影bean
     */
        @GET("movie/top250?apikey=0df993c66c0c636e29ecbb5344252a4a")
    Observable<MovieSubject> getTopMovie(@Query("start") String start,
                                         @Query("count") String count);

    /**
     * 获取本周口碑榜
     * @return
     */
    @GET("movie/weekly?apikey=0df993c66c0c636e29ecbb5344252a4a")
    Observable<WeeklyMovieSubject> getWeekly();

    /**
     * 获取热门电影
     * @return
     */
    @GET("movie/new_movies?apikey=0df993c66c0c636e29ecbb5344252a4a")
    Observable<MovieSubject> getNowMovies();

    /**
     * 获取北美票房榜
     * @return
     */
    @GET("movie/us_box?apikey=0df993c66c0c636e29ecbb5344252a4a")
    Observable<UsMovieSubject> getUsBox();


}

