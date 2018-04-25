package mvp.wyyne.douban.moviedouban;

import android.app.Application;


import com.blankj.utilcode.util.Utils;

import mvp.wyyne.douban.moviedouban.api.RetrofitService;

/**
 * @author XXW
 * @date 2017/6/5
 */

public class AndroidApplication extends Application {
    private static AndroidApplication mApplication;


    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        RetrofitService.init();

        Utils.init(this);

    }

    public static AndroidApplication getApplication() {
        return mApplication;
    }
}
