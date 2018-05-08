package mvp.wyyne.douban.moviedouban.detail.comment.photo;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import java.util.List;

import butterknife.BindView;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.bean.Reviews;
import mvp.wyyne.douban.moviedouban.detail.comment.PhotoCommentImp;
import mvp.wyyne.douban.moviedouban.home.base.BaseActivity;

/**
 * 占用评论的接口(后期抓取数据更新)
 * 照片下的评论
 *
 * @author Wynne
 * @date 2018/5/8
 */

public class PhotoCommentActivity extends BaseActivity<IPhotoCommentPresent> implements IPhotoCommentMain {
    public static final String TAG = "PhotoCommentActivity";
    @BindView(R.id.rv_comment)
    RecyclerView rvComment;
    /**
     * 评论照片ID
     */
    private String mId;

    private PhotoCommentAdapter mAdapter;

    @Override
    protected void refresh() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_comment_photo;
    }

    @Override
    protected void initView() {
        if (!TextUtils.isEmpty(getIntent().getStringExtra(TAG))) {
            mId = getIntent().getStringExtra(TAG);
        }
        mPresent = new PhotoCommentImp(this, mId);
        mPresent.getData();
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }


    @Override
    public void noticeAdapter(List<Reviews> mList) {

    }
}
