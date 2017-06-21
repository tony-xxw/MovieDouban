package mvp.wyyne.douban.moviedouban.detail;

import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import mvp.wyyne.douban.moviedouban.api.RetrofitService;
import mvp.wyyne.douban.moviedouban.api.bean.Article;

/**
 * Created by XXW on 2017/6/19.
 */

public class DetailMoviePresent implements IDetailPresent {
    private IDetailMain mMain;


    public DetailMoviePresent(IDetailMain main) {
        mMain = main;
    }


    @Override
    public void getData() {

    }


    @Override
    public void getArticle(String subjectId) {
        RetrofitService.getArticle(subjectId)
                .subscribe(new Observer<Article>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Article article) {
                        Log.d("XXW", article.getTitle());
                        mMain.initMovieImg(article);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("XXW", e.toString());
                    }

                    @Override
                    public void onComplete() {
                        mMain.initMovieGrade();
                    }
                });

    }
}
