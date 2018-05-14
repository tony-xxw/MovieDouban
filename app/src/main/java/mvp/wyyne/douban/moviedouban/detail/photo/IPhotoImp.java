package mvp.wyyne.douban.moviedouban.detail.photo;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import mvp.wyyne.douban.moviedouban.api.RetrofitService;
import mvp.wyyne.douban.moviedouban.api.bean.Stills;
import mvp.wyyne.douban.moviedouban.api.bean.StillsPhotos;

/**
 * @author XXW
 * @date 2017/7/3
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
                            mList = stills.getPhotos();
                            mMain.showPage(stills);
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

    @Override
    public void downloadToLocal(int position) {
        mList.get(position).getImage();
    }
}
