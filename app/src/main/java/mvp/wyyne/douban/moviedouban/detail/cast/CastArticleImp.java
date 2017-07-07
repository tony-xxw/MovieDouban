package mvp.wyyne.douban.moviedouban.detail.cast;

import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import mvp.wyyne.douban.moviedouban.api.RetrofitService;
import mvp.wyyne.douban.moviedouban.api.bean.CastArticle;

/**
 * Created by XXW on 2017/7/7.
 */

public class CastArticleImp implements ICastPresent {
    private ICastMain mMain;

    public CastArticleImp(ICastMain main) {
        mMain = main;
    }


    @Override
    public void getData() {

    }

    @Override
    public void getCastArticle(String castId) {
        RetrofitService.getCastArticle(castId)
                .subscribe(new Observer<CastArticle>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mMain.show();
                    }

                    @Override
                    public void onNext(@NonNull CastArticle castArticle) {
                        if (castArticle != null) {
                            Log.d("XXW", "星座--" + castArticle.getConstellation());
                            mMain.showPage(castArticle);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("XXW", "castArticle--" + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        mMain.hide();
                    }
                });
    }
}
