package mvp.wyyne.douban.moviedouban.hot.future;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import mvp.wyyne.douban.moviedouban.api.RetrofitService;
import mvp.wyyne.douban.moviedouban.api.bean.Article;
import mvp.wyyne.douban.moviedouban.api.bean.HotBean;
import mvp.wyyne.douban.moviedouban.api.bean.Subjects;
import retrofit2.Retrofit;

/**
 * Created by XXW on 2017/6/13.
 */

public class FuturePresent implements IFuturePresent {
    private Context mContext;
    private IFutureMain mMain;
    private Map<String, Subjects> mMap;
    private List<Subjects> mSubjectses;
//    private List<P>

    public FuturePresent(Context context, IFutureMain main) {
        mContext = context;
        mMain = main;
        mMap = new HashMap<>();
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
