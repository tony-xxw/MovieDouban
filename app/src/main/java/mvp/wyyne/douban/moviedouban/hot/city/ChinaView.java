package mvp.wyyne.douban.moviedouban.hot.city;

import mvp.wyyne.douban.moviedouban.home.IMain;

/**
 * @author XXW
 * @date 2018/1/19
 */

public interface ChinaView extends IMain {
    /**
     * 初始化城市控件
     *
     * @param cityName
     */
    void initCity(String cityName);
}
