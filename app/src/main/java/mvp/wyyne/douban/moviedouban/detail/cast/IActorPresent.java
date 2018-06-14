package mvp.wyyne.douban.moviedouban.detail.cast;

import mvp.wyyne.douban.moviedouban.home.IPresent;

/**
 * @author XXW
 * @date 2017/7/7
 */

public interface IActorPresent extends IPresent {
    /**
     * 获取演员信息
     *
     * @param castId
     */
    void getCastInfo(String castId);
}
