package mvp.wyyne.douban.moviedouban.detail.cast;

import mvp.wyyne.douban.moviedouban.api.RetrofitService;
import mvp.wyyne.douban.moviedouban.api.bean.ActorInfo;
import mvp.wyyne.douban.moviedouban.home.BaseObserver;

/**
 * @author XXW
 * @date 2017/7/7
 */

public class ActorArticleImp implements IActorPresent {
    private IActorMain mMain;

    public ActorArticleImp(IActorMain main) {
        mMain = main;
    }


    @Override
    public void getData() {

    }

    @Override
    public void getCastInfo(String castId) {
        RetrofitService.getCastArticle(castId)
                .subscribe(new BaseObserver<ActorInfo>(mMain) {
                    @Override
                    public void onSuccess(ActorInfo castArticle) {
                        if (castArticle != null) {

                            mMain.showPage(castArticle);
                        }
                    }
                });
    }
}
