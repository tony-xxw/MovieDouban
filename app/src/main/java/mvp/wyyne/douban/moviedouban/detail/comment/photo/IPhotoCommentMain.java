package mvp.wyyne.douban.moviedouban.detail.comment.photo;

import java.util.List;

import mvp.wyyne.douban.moviedouban.api.bean.Reviews;
import mvp.wyyne.douban.moviedouban.home.IMain;

/**
 * @author Wynne
 * @date 2018/5/8
 */

public interface IPhotoCommentMain extends IMain {

    /**
     * 刷新列表
     *
     * @param mList
     */
    void noticeAdapter(List<Reviews> mList);
}
