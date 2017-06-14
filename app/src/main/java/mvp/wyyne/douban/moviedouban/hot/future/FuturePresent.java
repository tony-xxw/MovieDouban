package mvp.wyyne.douban.moviedouban.hot.future;

import android.content.Context;
import android.util.Log;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import mvp.wyyne.douban.moviedouban.api.RetrofitService;
import mvp.wyyne.douban.moviedouban.api.bean.Article;
import mvp.wyyne.douban.moviedouban.api.bean.HotBean;
import retrofit2.Retrofit;

/**
 * Created by XXW on 2017/6/13.
 */

public class FuturePresent implements IFuturePresent {
    private Context mContext;
    private IFutureMain mMain;
//    private List<P>

    public FuturePresent(Context context, IFutureMain main) {
        mContext = context;
        mMain = main;
    }


    @Override
    public void getData() {
        RetrofitService.getFutureList()
                .subscribe(new Observer<HotBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mMain.show();
                    }

                    @Override
                    public void onNext(@NonNull HotBean hotBean) {
                        mMain.initData(hotBean.getSubjectsList());

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        mMain.hide();
                    }
                });
    }

    public void getMovieDate(String movieId) {
        RetrofitService.getArticle(movieId)
                .subscribe(new Observer<Article>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Article article) {
                        List<String> mList = article.getAttrs().getPubdate();
                        for (String s : mList) {
                            if (s.contains("中国大陆")) {
                                Log.d("XXW", "日期------" + s);
                            }
                        }
                        mMain.bindData();
                    } 

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
