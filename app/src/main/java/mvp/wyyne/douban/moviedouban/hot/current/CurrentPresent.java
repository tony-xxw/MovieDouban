package mvp.wyyne.douban.moviedouban.hot.current;

import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import mvp.wyyne.douban.moviedouban.api.RetrofitService;
import mvp.wyyne.douban.moviedouban.api.bean.HotBean;

/**
 * Created by XXW on 2017/6/12.
 */

public class CurrentPresent implements ICurrentPresent {
    private ICurrentMain mMain;

    public CurrentPresent(ICurrentMain main) {
        mMain = main;
    }


    @Override
    public void getData() {
        RetrofitService.getHotList()
                .subscribe(new Observer<HotBean>() {
                               @Override
                               public void onSubscribe(@NonNull Disposable d) {
                                   mMain.show();
                               }

                               @Override
                               public void onNext(@NonNull HotBean hotBeen) {
                                   mMain.initData(hotBeen.getSubjectsList());
                               }

                               @Override
                               public void onError(@NonNull Throwable e) {
                               }

                               @Override
                               public void onComplete() {
                                   mMain.hide();
                               }
                           }
                );
    }
}
