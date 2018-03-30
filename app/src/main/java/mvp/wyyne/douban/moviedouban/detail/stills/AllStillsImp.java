package mvp.wyyne.douban.moviedouban.detail.stills;

import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import mvp.wyyne.douban.moviedouban.api.RetrofitService;
import mvp.wyyne.douban.moviedouban.api.bean.CastArticle;
import mvp.wyyne.douban.moviedouban.api.bean.CastPhoto;
import mvp.wyyne.douban.moviedouban.api.bean.Stills;

/**
 *
 * @author XXW
 * @date 2017/7/2
 */

public class AllStillsImp implements AllStillsPresent {
    private AllStillMain mMain;

    public AllStillsImp(AllStillMain main) {
        mMain = main;
    }


    @Override
    public void getStills(String id) {
        RetrofitService.getStillsPhotos(id).
                subscribe(new Observer<Stills>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Stills stills) {
                        Log.d("XXW", "stills--" + stills.getCount());
                        mMain.update(stills);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        mMain.updateTitle();
                    }
                });
    }

    @Override
    public void getData() {

    }


    @Override
    public void getCasts(String id) {
        RetrofitService.getCastList(id).
                subscribe(new Observer<CastPhoto>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull CastPhoto stills) {
                        Log.d("XXW", "stills--" + stills.getCount());
                        mMain.updateCast(stills);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        mMain.updateTitle();
                    }
                });
    }
}
