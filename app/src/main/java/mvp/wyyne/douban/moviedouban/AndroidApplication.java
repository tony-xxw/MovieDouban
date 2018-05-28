package mvp.wyyne.douban.moviedouban;

import android.app.Application;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.blankj.utilcode.util.Utils;
import com.tencent.tinker.loader.app.ApplicationLike;
import com.tinkerpatch.sdk.TinkerPatch;
import com.tinkerpatch.sdk.loader.TinkerPatchApplicationLike;

import mvp.wyyne.douban.moviedouban.api.RetrofitService;

import static mvp.wyyne.douban.moviedouban.utils.Constant.LOGIN;

/**
 * @author XXW
 * @date 2017/6/5
 */

public class AndroidApplication extends Application {
    private static AndroidApplication mApplication;
    private SharedPreferences loginShared;
    private ApplicationLike tinkerApplicationLike;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        RetrofitService.init();

        Utils.init(this);

        initTinkerPatch();
        initSharedPreferences();


    }

    private void initSharedPreferences() {
        loginShared = getSharedPreferences("login", MODE_PRIVATE);
    }

    public static AndroidApplication getApplication() {
        return mApplication;
    }


    /**
     * 记录是否登陆
     */
    public void recodeLogin() {
        loginShared.edit().putString(LOGIN, LOGIN).apply();

    }

    public boolean Login() {
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
