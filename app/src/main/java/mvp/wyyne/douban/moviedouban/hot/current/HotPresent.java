package mvp.wyyne.douban.moviedouban.hot.current;


import java.util.List;

import mvp.wyyne.douban.moviedouban.api.RetrofitService;
import mvp.wyyne.douban.moviedouban.api.bean.Subjects;
import mvp.wyyne.douban.moviedouban.home.BaseObserver;

/**
 * @author XXW
 * @date 2017/6/12
 */

public class HotPresent implements IHotPresent {
    private IHotMain mMain;

    public HotPresent(IHotMain main) {
        mMain = main;
    }


    @Override
    public void getData() {

    }

    @Override
    public void getCurrentData() {
        RetrofitService.getHotList().
                subscribe(new BaseObserver<List<Subjects>>(mMain) {
                    @Override
                    public void onSuccess(List<Subjects> response) {
                        mMain.initData(response);
                    }
                });
    }

    @Override
    public void getFutureData() {
        RetrofitService.getFutureList()
                .subscribe(new BaseObserver<List<Subjects>>(mMain) {
                    @Override
                    public void onSuccess(List<Subjects> subjects) {
                        mMain.initData(subjects);
                    }
                });
    }
}
