package mvp.wyyne.douban.moviedouban.detail.photo;

import android.nfc.Tag;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.adapter.PhotoPageAdapter;
import mvp.wyyne.douban.moviedouban.api.bean.StillsPhotos;
import mvp.wyyne.douban.moviedouban.home.BaseActivity;
import mvp.wyyne.douban.moviedouban.widget.PhotoViewPage;

/**
 * 照片放大
 * Created by XXW on 2017/7/3.
 */

public class PhotoActivity extends BaseActivity<IPhotoPresent> implements IPhotoMain, ViewPager.OnPageChangeListener {
    public static final String ID = "subjectId";
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.iv_down)
    ImageView mIvDown;
    @BindView(R.id.vp_page)
    PhotoViewPage mVpPage;
    @BindView(R.id.iv_love)
    ImageView mIvLove;
    @BindView(R.id.iv_comment)
    ImageView mIvComment;
    private PhotoPageAdapter mPageAdapter;
    private List<StillsPhotos> mList;
    private String mSubject;

    @Override
    public void show() {
        mLodingView.show();
    }

    @Override
    public void hide() {
        mLodingView.hide();
    }

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
        }
        mList = new ArrayList<>();
        mPageAdapter = new PhotoPageAdapter(this, mList);
        mVpPage.setAdapter(mPageAdapter);
        mVpPage.setOnPageChangeListener(this);
        mPresent = new IPhotoImp(this);
        mPresent.getPhoto(mSubject);
    }

    @Override
    public void showPage(List<StillsPhotos> list) {
        mList = list;
        mPageAdapter.setList(mList);
        mPageAdapter.notifyDataSetChanged();
    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.d("xxw", "onPageScrolled---" + position + "---positionOffset---" + positionOffset + "---positionOffsetPixels---" + positionOffsetPixels);
    }

    @Override
    public void onPageSelected(int position) {
        Log.d("xxw", "onPageSelected---" + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        Log.d("xxw", "onPageScrollStateChanged---" + state);
    }
}
