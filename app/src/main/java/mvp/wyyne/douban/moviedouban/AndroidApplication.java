package mvp.wyyne.douban.moviedouban;

import android.app.Application;
import android.content.SharedPreferences;
import android.text.TextUtils;


import com.blankj.utilcode.util.Utils;

import mvp.wyyne.douban.moviedouban.api.RetrofitService;

import static mvp.wyyne.douban.moviedouban.utils.Constant.LOGIN;

/**
 * @author XXW
 * @date 2017/6/5
 */

public class AndroidApplication extends Application {
    private static AndroidApplication mApplication;
    private SharedPreferences loginShared;


    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        RetrofitService.init();

        Utils.init(this);


        loginShared = getSharedPreferences("login", MODE_PRIVATE);

    }

    public static AndroidApplication getApplication() {
        return mApplication;
    }

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
}
