package mvp.wyyne.douban.moviedouban.welfare;

import java.util.List;

import mvp.wyyne.douban.moviedouban.api.bean.WelfarePhotoInfo;
import mvp.wyyne.douban.moviedouban.home.IMain;

/**
 * Created by XXW on 2017/6/25.
 */

public interface IWelfareMain extends IMain {
    void showImg(List<WelfarePhotoInfo> list);
}
