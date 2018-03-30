package mvp.wyyne.douban.moviedouban.detail.stills;

import mvp.wyyne.douban.moviedouban.home.IPresent;

/**
 *
 * @author XXW
 * @date 2017/7/2
 */

public interface AllStillsPresent extends IPresent {

    void getStills(String id);

    void getCasts(String id);
}
