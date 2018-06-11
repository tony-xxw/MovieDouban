package mvp.wyyne.douban.moviedouban.welfare;

import android.content.Context;

import mvp.wyyne.douban.moviedouban.api.RetrofitService;
import mvp.wyyne.douban.moviedouban.api.bean.WelfarePhotoList;
import mvp.wyyne.douban.moviedouban.home.BaseObserver;

/**
 * @author XXW
 * @date 2017/6/25
 */

public class WelfarePresent implements IWelfarePresent {
    private IWelfareMain mMain;
    private Context mContext;


    public WelfarePresent(IWelfareMain main, Context context) {
        mMain = main;
        mContext = context;


    }

    @Override
    public void getData() {
        RetrofitService.getPhotoList(0)
                .subscribe(new BaseObserver<WelfarePhotoList>(mMain) {
                    @Override
                    public void onSuccess(WelfarePhotoList welfarePhotoList) {
                        mMain.showImg(welfarePhotoList.getResults());
                        for (int i = 0; i < welfarePhotoList.getResults().size(); i++) {
                        }
                    }
                });
    }


}
