package mvp.wyyne.douban.moviedouban.detail.cast;

import mvp.wyyne.douban.moviedouban.api.RetrofitService;
import mvp.wyyne.douban.moviedouban.api.bean.CastArticle;
import mvp.wyyne.douban.moviedouban.home.BaseObserver;

/**
 * @author XXW
 * @date 2017/7/7
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
                .subscribe(new BaseObserver<CastArticle>(mMain) {
                    @Override
                    public void onSuccess(CastArticle castArticle) {
                        if (castArticle != null) {

                            mMain.showPage(castArticle);
                        }
                    }
                });
    }
}
