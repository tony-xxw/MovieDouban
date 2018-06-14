package mvp.wyyne.douban.moviedouban.detail.cast;

import mvp.wyyne.douban.moviedouban.api.bean.ActorInfo;
import mvp.wyyne.douban.moviedouban.home.IMain;

/**
 * Created by XXW on 2017/7/7.
 */

public interface IActorMain extends IMain {
    void showPage(ActorInfo article);
}
