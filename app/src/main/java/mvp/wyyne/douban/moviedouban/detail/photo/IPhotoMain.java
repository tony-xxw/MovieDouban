package mvp.wyyne.douban.moviedouban.detail.photo;

import mvp.wyyne.douban.moviedouban.api.bean.Stills;
import mvp.wyyne.douban.moviedouban.home.IMain;

/**
 * @author XXW
 * @date 2017/7/3
 */

public interface IPhotoMain extends IMain {

    /**
     * 刷新页面
     *
     * @param stills 剧照Bean
     */
    void showPage(Stills stills);


    /**
     * 显示Toast
     *
     * @param toastString 内容文本
     */
    void showToast(String toastString);


    /**
     * 跳转分享页面
     */
    void shareIntent();
}
