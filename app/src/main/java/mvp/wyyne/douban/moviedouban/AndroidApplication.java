package mvp.wyyne.douban.moviedouban;

import android.app.Application;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.blankj.utilcode.util.Utils;
import com.facebook.stetho.Stetho;
import com.tencent.tinker.loader.app.ApplicationLike;
import com.tinkerpatch.sdk.TinkerPatch;
import com.tinkerpatch.sdk.loader.TinkerPatchApplicationLike;

import org.greenrobot.greendao.database.Database;

import mvp.wyyne.douban.moviedouban.api.RetrofitService;
import mvp.wyyne.douban.moviedouban.model.DaoMaster;
import mvp.wyyne.douban.moviedouban.model.DaoSession;

import static mvp.wyyne.douban.moviedouban.utils.Constant.LOGIN;

/**
 * @author XXW
 * @date 2017/6/5
 */

public class AndroidApplication extends Application {
    private static AndroidApplication mApplication;
    private SharedPreferences loginShared;
    private ApplicationLike tinkerApplicationLike;
    private String dbName = "search_name";
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        RetrofitService.init();

        Utils.init(this);


        //数据库查看信息
        Stetho.initializeWithDefaults(this);

        initTinkerPatch();
        initSharedPreferences();
        initDateBase();


    }


    public static DaoSession getDaoSession() {
        return daoSession;
    }


    /*** 初始化数据库*/

    private void initDateBase() {
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(this, dbName);
        Database database = openHelper.getWritableDb();
        DaoMaster daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
    }


    /**
     * 初始化SharePreferences
     */
    private void initSharedPreferences() {
        loginShared = getSharedPreferences("login", MODE_PRIVATE);
    }

    public static AndroidApplication getApplication() {
        return mApplication;
    }


    /**
     * 记录登陆状态
     */
    public void recodeLogin() {
        loginShared.edit().putString(LOGIN, LOGIN).apply();

    }

    /**
     * 退出登陆状态
     */
    public void cancelLogin() {
        loginShared.edit().putString(LOGIN, "").apply();
    }


    /**
     * 判断是否登陆
     *
     * @return
     */
    public boolean isLogin() {
        String result = loginShared.getString(LOGIN, "");
        if (!TextUtils.isEmpty(result) && result.equals(LOGIN)) {
            return true;
        }
        return false;
    }


    /**
     * 我们需要确保至少对主进程跟patch进程初始化 TinkerPatch
     */
    private void initTinkerPatch() {
        // 我们可以从这里获得Tinker加载过程的信息
        if (BuildConfig.TINKER_ENABLE) {
            tinkerApplicationLike = TinkerPatchApplicationLike.getTinkerPatchApplicationLike();
            // 初始化TinkerPatch SDK
            TinkerPatch.init(
                    tinkerApplicationLike
//                new TinkerPatch.Builder(tinkerApplicationLike)
//                    .requestLoader(new OkHttp3Loader())
//                    .build()
            )
                    .reflectPatchLibrary()
                    .setPatchRollbackOnScreenOff(true)
                    .setPatchRestartOnSrceenOff(true)
                    .setFetchPatchIntervalByHours(3)
            ;
            // 获取当前的补丁版本
            Log.d("XXW", "Current patch version is " + TinkerPatch.with().getPatchVersion());

            // fetchPatchUpdateAndPollWithInterval 与 fetchPatchUpdate(false)
            // 不同的是，会通过handler的方式去轮询
            TinkerPatch.with().fetchPatchUpdateAndPollWithInterval();
        }
    }
}
