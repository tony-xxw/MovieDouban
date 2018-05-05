package mvp.wyyne.douban.moviedouban.welfare;

import android.content.Context;
import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import mvp.wyyne.douban.moviedouban.api.RetrofitService;
import mvp.wyyne.douban.moviedouban.api.bean.WelfarePhotoList;

/**
 * @author XXW
 * @date 2017/6/25
 */

public class WelfarePresent implements IWelfarePresent {
    private IWelfareMain mMain;


    public WelfarePresent(IWelfareMain main, Context context) {
        mMain = main;


    }

    @Override
    public void getData() {
        RetrofitService.getPhotoList(0)
                .subscribe(new Observer<WelfarePhotoList>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull WelfarePhotoList welfarePhotoList) {
                        mMain.showImg(welfarePhotoList.getResults());
                        for (int i = 0; i < welfarePhotoList.getResults().size(); i++) {
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("XXW", e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


}
