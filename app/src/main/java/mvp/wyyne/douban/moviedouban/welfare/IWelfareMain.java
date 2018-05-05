package mvp.wyyne.douban.moviedouban.welfare;

import java.util.List;

import mvp.wyyne.douban.moviedouban.api.bean.WelfarePhotoInfo;
import mvp.wyyne.douban.moviedouban.home.IMain;

/**
 * @author XXW
 * @date 2017/6/25
 */

public interface IWelfareMain extends IMain {

    /**
     * 显示图片
     *
     * @param list
     */
    void showImg(List<WelfarePhotoInfo> list);


}
