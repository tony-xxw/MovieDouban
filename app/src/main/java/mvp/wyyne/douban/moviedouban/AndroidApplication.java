package mvp.wyyne.douban.moviedouban;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;

import mvp.wyyne.douban.moviedouban.api.RetrofitService;

/**
 * Created by XXW on 2017/6/5.
 */

public class AndroidApplication extends Application {
    private static AndroidApplication mApplication;


    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        RetrofitService.init();

        // 集成Bugly
        CrashReport.initCrashReport(this);

    }

    public static AndroidApplication getApplication() {
        return mApplication;
    }
}
