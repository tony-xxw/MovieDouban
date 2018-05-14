package mvp.wyyne.douban.moviedouban.detail.photo;

import mvp.wyyne.douban.moviedouban.home.IPresent;

/**
 * @author XXW
 * @date 2017/7/3
 */

public interface IPhotoPresent extends IPresent {

    /**
     * 获取照片列表
     *
     * @param subjectId 类别ID
     */
    void getPhoto(String subjectId);

    /**
     * 下载到本地
     *
     * @param position 当前Item
     */
    void downloadToLocal(int position);
}
