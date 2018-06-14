package mvp.wyyne.douban.moviedouban.api;

import android.support.annotation.NonNull;
import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import mvp.wyyne.douban.moviedouban.AndroidApplication;
import mvp.wyyne.douban.moviedouban.api.bean.Article;
import mvp.wyyne.douban.moviedouban.api.bean.ActorInfo;
import mvp.wyyne.douban.moviedouban.api.bean.CastPhoto;
import mvp.wyyne.douban.moviedouban.api.bean.HotBean;
import mvp.wyyne.douban.moviedouban.api.bean.MoviesReviews;
import mvp.wyyne.douban.moviedouban.api.bean.Stills;
import mvp.wyyne.douban.moviedouban.api.bean.Subjects;
import mvp.wyyne.douban.moviedouban.api.bean.WelfarePhotoList;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author XXW
 * @date 2017/6/5
 */

public class RetrofitService {
    private static final String MOVIES_HOST = "https://api.douban.com/v2/";
    private static final String WELFARE_HOST = "http://gank.io";

    private static IMoviesApi mMoviesApi;
    private static IWelfareApi mWelfareApi;

    public static void init() {
        Cache cache = new Cache(new File(AndroidApplication.getApplication().getCacheDir(), "HttpCache"), 1024 * 1024 * 100);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                //是否断线重连
                .retryOnConnectionFailure(true)
                .addInterceptor(mLoggingInterceptor)
                .addInterceptor(sRewriteCacheControlInterceptor)
                .addNetworkInterceptor(sRewriteCacheControlInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(MOVIES_HOST)
                .build();

        Retrofit welfare = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(WELFARE_HOST)
                .build();

        mMoviesApi = retrofit.create(IMoviesApi.class);
        mWelfareApi = welfare.create(IWelfareApi.class);
    }


    private static String cacheControl = "Cache-Control: public, max-age=3600";
    /**
     * 云端响应头拦截器，用来配置缓存策略
     * Dangerous interceptor that rewrites the server's cache-control header.
     */
    private static final Interceptor sRewriteCacheControlInterceptor = new Interceptor() {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            Response originalResponse = chain.proceed(request);


            return originalResponse.newBuilder()
                    .addHeader("Cache-Control", cacheControl)
                    .removeHeader("Pragma")
                    .build();

        }
    };

    private static final Interceptor mLoggingInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            final Request request = chain.request();
            Buffer requestBuffer = new Buffer();
            if (request.body() != null) {
                request.body().writeTo(requestBuffer);
            } else {
                Log.d("XXW", "request.body()==null");
            }

            Log.i("XXW", request.url() + (request.body() != null ? "?" + parseParams(request.body(), requestBuffer) : ""));
            final Response response = chain.proceed(request);
            return response;
        }
    };


    @NonNull
    private static String parseParams(RequestBody body, Buffer requestBuffer) throws UnsupportedEncodingException {
        if (body.contentType() != null && !body.contentType().toString().contains("multipart")) {
            return URLDecoder.decode(requestBuffer.readUtf8(), "UTF-8");
        }
        return "null";
    }


    public static Observable<List<Subjects>> getHotList() {
        return subscribeOnThread(mMoviesApi.getHotList().map(new Function<HotBean, List<Subjects>>() {
            @Override
            public List<Subjects> apply(HotBean hotBean) throws Exception {
                return hotBean.getSubjectsList();
            }
        }));
    }


    public static Observable<List<Subjects>> getFutureList() {
        return subscribeOnThread(mMoviesApi.getFutureList().map(new Function<HotBean, List<Subjects>>() {
            @Override
            public List<Subjects> apply(HotBean hotBean) throws Exception {
                return hotBean.getSubjectsList();
            }
        }));
    }

    public static Observable<Article> getArticle(String id) {
        return subscribeOnThread(mMoviesApi.getArticle(id));
    }

    public static Observable<WelfarePhotoList> getPhotoList(int page) {
        return subscribeOnThread(mWelfareApi.getWelfarePhoto(page));
    }

    public static Observable<MoviesReviews> getReviews(String subjectId) {
        return subscribeOnThread(mMoviesApi.getReviews(subjectId));
    }

    public static Observable<Stills> getStillsPhotos(String subjectId) {
        return subscribeOnThread(mMoviesApi.getStillsPhotos(subjectId));
    }

    public static Observable<ActorInfo> getCastArticle(String castId) {
        return subscribeOnThread(mMoviesApi.getCastArticle(castId));
    }

    public static Observable<HotBean> searchMovieSubject(String text, String start, String count) {
        return subscribeOnThread(mMoviesApi.searchMovieSubject(text, start, count));
    }

    public static Observable<CastPhoto> getCastList(String castId) {
        return subscribeOnThread(mMoviesApi.getCastPhotos(castId));
    }


    private static <T> Observable<T> subscribeOnThread(Observable<T> observable) {
        return observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
