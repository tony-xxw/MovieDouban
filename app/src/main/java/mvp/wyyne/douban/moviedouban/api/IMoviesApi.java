package mvp.wyyne.douban.moviedouban.api;


import io.reactivex.Observable;
import mvp.wyyne.douban.moviedouban.api.bean.Article;
import mvp.wyyne.douban.moviedouban.api.bean.CastArticle;
import mvp.wyyne.douban.moviedouban.api.bean.CastPhoto;
import mvp.wyyne.douban.moviedouban.api.bean.HotBean;
import mvp.wyyne.douban.moviedouban.api.bean.MoviesReviews;
import mvp.wyyne.douban.moviedouban.api.bean.Stills;
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
    Observable<HotBean> getHotList();

    /**
     * 即将上映
     *
     * @return 即将上映
     */
    @GET("movie/coming_soon?apikey=0df993c66c0c636e29ecbb5344252a4a")
    Observable<HotBean> getFutureList();

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
    Observable<CastArticle> getCastArticle(@Path("id") String id);


    @GET("movie/search?")
    Observable<HotBean> searchMovieSubject(@Query("q") String text,
                                           @Query("start") String start,
                                           @Query("count") String count);
}

