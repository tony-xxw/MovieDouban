package mvp.wyyne.douban.moviedouban.detail.stills;


import java.util.List;

import mvp.wyyne.douban.moviedouban.api.bean.CastPhoto;
import mvp.wyyne.douban.moviedouban.api.bean.Stills;
import mvp.wyyne.douban.moviedouban.api.bean.StillsPhotos;
import mvp.wyyne.douban.moviedouban.home.IMain;

/**
 * Created by XXW on 2017/7/2.
 */

public interface AllStillMain extends IMain {

    void update(Stills stills);

    void updateCast(CastPhoto stills);

    void updateTitle();
}
