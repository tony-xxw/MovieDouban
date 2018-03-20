package mvp.wyyne.douban.moviedouban.hot.current;

import mvp.wyyne.douban.moviedouban.home.IPresent;

/**
 * @author XXW
 * @date 2017/6/12
 */

public interface IHotPresent extends IPresent {


    /**
     * 获取正在热映数据
     */
    void getCurrentData();


    /**
     * 获取即将上映页面数据
     */
    void getFutureData();
}
