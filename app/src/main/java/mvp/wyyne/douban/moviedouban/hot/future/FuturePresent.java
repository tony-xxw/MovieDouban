package mvp.wyyne.douban.moviedouban.hot.future;

import android.content.Context;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import mvp.wyyne.douban.moviedouban.api.RetrofitService;
import mvp.wyyne.douban.moviedouban.api.bean.HotBean;
import retrofit2.Retrofit;

/**
 * Created by XXW on 2017/6/13.
 */

public class FuturePresent implements IFuturePresent {
    private Context mContext;
    private IFutureMain mMain;

    public FuturePresent(Context context, IFutureMain main) {
        mContext = context;
        mMain = main;
    }


    @Override
    public void getData() {
        RetrofitService.getFutureList()
                .subscribe(new Observer<HotBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mMain.show();
                    }

                    @Override
                    public void onNext(@NonNull HotBean hotBean) {
                        mMain.initData(hotBean.getSubjectsList());
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
