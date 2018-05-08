package mvp.wyyne.douban.moviedouban.detail.comment.photo;

import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.home.base.BaseActivity;

/**
 * 照片下的评论
 *
 * @author Wynne
 * @date 2018/5/8
 */

public class PhotoCommentActivity extends BaseActivity<IPhotoCommentPresent> implements IPhotoCommentMain {
    @BindView(R.id.rv_comment)
    RecyclerView rvComment;


    @Override
    protected void refresh() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_comment_photo;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void noticeAdapter() {

    }
}
