package mvp.wyyne.douban.moviedouban.detail.photo;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.adapter.PhotoPageAdapter;
import mvp.wyyne.douban.moviedouban.api.RetrofitService;
import mvp.wyyne.douban.moviedouban.api.bean.CastPhoto;
import mvp.wyyne.douban.moviedouban.api.bean.Stills;
import mvp.wyyne.douban.moviedouban.api.bean.StillsPhotos;
import mvp.wyyne.douban.moviedouban.home.BaseActivity;
import mvp.wyyne.douban.moviedouban.widget.PhotoViewPage;

/**
 * Created by XXW on 2017/7/15.
 */

public class CastPhotoActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    public static final String ID = "subjectId";
    public static final String POSITION = "position";
    @BindView(R.id.vp_page)
    PhotoViewPage mVpPage;
    @BindView(R.id.tv_photo_time)
    TextView mTvPhotoTime;
    @BindView(R.id.iv_love)
    ImageView mIvLove;
    @BindView(R.id.iv_comment)
    ImageView mIvComment;
    @BindView(R.id.tv_comment_count)
    TextView mTvCommentCount;
    @BindView(R.id.btn_article)
    Button mBtnArticle;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_count)
    TextView mTvCount;
    @BindView(R.id.iv_down)
    ImageView mIvDown;
    private PhotoPageAdapter mPageAdapter;
    private List<StillsPhotos> mList;
    private String mSubject;
    private int position;
    private String subjectId;
    private Stills mStills;


    @Override
    protected void refresh() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_photo;
    }

    @Override
    protected void initView() {
        if (getIntent() != null) {
            mSubject = getIntent().getStringExtra(ID);
            position = getIntent().getIntExtra(POSITION, 0);
        }
        mList = new ArrayList<>();
        mPageAdapter = new PhotoPageAdapter(this, mList);
        mVpPage.setAdapter(mPageAdapter);
        mVpPage.setOnPageChangeListener(this);
        RetrofitService.getCastList(mSubject).subscribe(mObserver);
    }


    Observer<CastPhoto> mObserver = new Observer<CastPhoto>() {
        @Override
        public void onSubscribe(@NonNull Disposable d) {

        }

        @Override
        public void onNext(@NonNull CastPhoto castPhoto) {

        }

        @Override
        public void onError(@NonNull Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    };

    @OnClick(R.id.iv_back)

    public void onViewClicked() {
        finish();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mVpPage.setCurrentItem(position);
        subjectId = mList.get(position).getSubject_id();
        mTvCount.setText(getString(R.string.view_image) + "(" + position++ + "/" + mStills.getCount() + ")");
        mTvCommentCount.setText(mList.get(position).getComments_count() + "");
        mTvPhotoTime.setText(mList.get(position).getCreated_at());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
