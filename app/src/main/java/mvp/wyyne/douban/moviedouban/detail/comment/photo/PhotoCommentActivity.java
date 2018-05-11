package mvp.wyyne.douban.moviedouban.detail.comment.photo;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
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

public class PhotoCommentActivity extends BaseActivity<IPhotoCommentPresent> implements IPhotoCommentMain, TextWatcher {
    public static final String TAG = "PhotoCommentActivity";
    @BindView(R.id.rv_comment)
    RecyclerView rvComment;
    @BindView(R.id.tv_stills_title)
    TextView tvTitle;
    @BindView(R.id.et_comment)
    EditText etComment;
    @BindView(R.id.iv_reply)
    ImageView ivReply;
    /**
     * 评论照片ID
     */
    private String mId;

    private PhotoCommentAdapter mAdapter;

    private List<Reviews> mReviewsList;

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
        tvTitle.setText(getString(R.string.all_reply));
        etComment.addTextChangedListener(this);

        mReviewsList = new ArrayList<>();
        mPresent = new PhotoCommentImp(this, mId);
        mAdapter = new PhotoCommentAdapter(this, mReviewsList);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvComment.setLayoutManager(manager);
        rvComment.setAdapter(mAdapter);
        mPresent.getData();

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }


    @Override
    public void noticeAdapter(List<Reviews> list) {
        mReviewsList = list;
        mAdapter.setList(mReviewsList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.length() == 0) {
            ivReply.setImageDrawable(getDrawable(R.drawable.ic_send_disabled));
        } else {
            ivReply.setImageDrawable(getDrawable(R.drawable.ic_send_focused));
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }


    @OnClick({R.id.iv_back})
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            default:
                break;
        }
    }
}
