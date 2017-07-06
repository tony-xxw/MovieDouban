package mvp.wyyne.douban.moviedouban.detail.photo;

import java.util.List;

import mvp.wyyne.douban.moviedouban.api.bean.Stills;
import mvp.wyyne.douban.moviedouban.api.bean.StillsPhotos;
import mvp.wyyne.douban.moviedouban.home.IMain;

/**
 * Created by XXW on 2017/7/3.
 */

public interface IPhotoMain extends IMain {
    void showPage(Stills stills);
}
