package mvp.wyyne.douban.moviedouban.login;

import android.text.TextUtils;
import mvp.wyyne.douban.moviedouban.utils.Constant;

/**
 * 登录业务相关
 *
 * @author Wynne
 * @date 2018/5/2
 */

public class ILoginImp implements ILoginPresent {

    private ILoginMain mLogin;

    public ILoginImp(ILoginMain main) {
        mLogin = main;
    }


    @Override
    public void getData() {

    }

    @Override
    public void isLogin() {

        if (TextUtils.isEmpty(mLogin.getAccount())) {
            mLogin.showToast("请填写邮箱或者电话");
            return;
        }

        if (TextUtils.isEmpty(mLogin.getPassWord())) {
            mLogin.showToast("请填写密码");
            return;
        }

        if (mLogin.getAccount().equals(Constant.LOGIN_ACCOUNT) && mLogin.getPassWord().equals(Constant.LOGIN_PASSWORD)) {
            mLogin.toMainActivity();

        } else {
            if (!mLogin.getAccount().equals(Constant.LOGIN_ACCOUNT)) {
                mLogin.showToast("用户名与密码不匹配");
            } else {
                mLogin.showToast("密码错误");
            }
        }
    }
}
