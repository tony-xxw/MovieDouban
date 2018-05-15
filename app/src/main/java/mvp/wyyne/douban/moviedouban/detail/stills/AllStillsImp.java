package mvp.wyyne.douban.moviedouban.detail.stills;

import mvp.wyyne.douban.moviedouban.api.RetrofitService;
import mvp.wyyne.douban.moviedouban.api.bean.CastPhoto;
import mvp.wyyne.douban.moviedouban.api.bean.Stills;
import mvp.wyyne.douban.moviedouban.home.BaseObserver;

/**
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
                subscribe(new BaseObserver<Stills>(mMain) {
                    @Override
                    public void onSuccess(Stills stills) {
                        mMain.update(stills);
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
                subscribe(new BaseObserver<CastPhoto>(mMain) {
                    @Override
                    public void onSuccess(CastPhoto response) {
                        mMain.updateCast(response);
                        mMain.updateTitle();
                    }
                });
    }
}
