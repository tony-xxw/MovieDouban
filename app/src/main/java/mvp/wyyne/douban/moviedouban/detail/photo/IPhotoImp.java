package mvp.wyyne.douban.moviedouban.detail.photo;

import android.util.Log;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import mvp.wyyne.douban.moviedouban.api.RetrofitService;
import mvp.wyyne.douban.moviedouban.api.bean.Stills;
import mvp.wyyne.douban.moviedouban.api.bean.StillsPhotos;

/**
 * Created by XXW on 2017/7/3.
 */

public class IPhotoImp implements IPhotoPresent {

    private IPhotoMain mMain;
    private List<StillsPhotos> mList;

    public IPhotoImp(IPhotoMain main) {
        mMain = main;
    }


    @Override
    public void getData() {

    }

    @Override
    public void getPhoto(String subjectId) {

        RetrofitService.getStillsPhotos(subjectId)
                .subscribe(new Observer<Stills>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mMain.show();
                    }

                    @Override
                    public void onNext(@NonNull Stills stills) {
                        if (stills.getPhotos() != null) {
                            Log.d("XXW", "onNext--" + stills.getPhotos().size());
                            mList = stills.getPhotos();
                            mMain.showPage(mList);
                        }
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
}
