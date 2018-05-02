package mvp.wyyne.douban.moviedouban.login;

import mvp.wyyne.douban.moviedouban.home.IMain;

/**
 * 登录 UI相关
 *
 * @author Wynne
 * @date 2018/5/2
 */

public interface ILoginMain extends IMain {

    /**
     * 获取密码
     *
     * @return 密码
     */
    String getPassWord();

    /**
     * 获取账号
     *
     * @return 账号
     */
    String getAccount();

    /**
     * 跳转主页面
     */
    void toMainActivity();


    /**
     * 登录结果
     *
     * @param msg success or failed
     */
    void showToast(String msg);


}
