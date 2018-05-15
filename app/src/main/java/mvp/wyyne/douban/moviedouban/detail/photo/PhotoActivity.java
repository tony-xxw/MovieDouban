package mvp.wyyne.douban.moviedouban.detail.photo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.PermissionUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.adapter.PhotoPageAdapter;
import mvp.wyyne.douban.moviedouban.api.bean.Stills;
import mvp.wyyne.douban.moviedouban.api.bean.StillsPhotos;
import mvp.wyyne.douban.moviedouban.detail.comment.photo.PhotoCommentActivity;
import mvp.wyyne.douban.moviedouban.detail.stills.AllStillsActivity;
import mvp.wyyne.douban.moviedouban.home.base.BaseActivity;
import mvp.wyyne.douban.moviedouban.utils.SdCardUtils;
import mvp.wyyne.douban.moviedouban.utils.ToastUtils;
import mvp.wyyne.douban.moviedouban.widget.PhotoViewPage;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static mvp.wyyne.douban.moviedouban.detail.stills.AllStillsActivity.STILLS;

/**
 * 剧照照片放大
 *
 * @author XXW
 * @date 2017/7/3
 */

public class PhotoActivity extends BaseActivity<IPhotoPresent> implements IPhotoMain, ViewPager.OnPageChangeListener {
    public static final String ID = "subjectId";
    public static final String POSITION = "position";
    private final static int REQUEST_CODE = 1;
    private String permission = WRITE_EXTERNAL_STORAGE;
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
    @BindView(R.id.tv_count)
    TextView mTvCount;
    @BindView(R.id.btn_article)
    Button mBtnArticle;
    @BindView(R.id.tv_photo_time)
    TextView mTvPhotoTime;
    @BindView(R.id.tv_comment_count)
    TextView mTvCommentCount;

    private PhotoPageAdapter mPageAdapter;
    private List<StillsPhotos> mList;
    private String mSubject;
    private int position;
    private String subjectId;
    private Stills mStills;
    /**
     * 图片滑动的角标
     */
    private int mItemPosition = 0;

    @Override
    public void show() {
        mLoadingView.show();
    }

    @Override
    public void hide() {
        mLoadingView.hide();
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
            position = getIntent().getIntExtra(POSITION, 0);
            Log.d("XXW", "===mSubject===" + mSubject + "===position===" + position);
        }
        Log.d("xxw", "getPicturesFilePath  " + SdCardUtils.getPicturesFilePath().getPath());
        mList = new ArrayList<>();
        mPageAdapter = new PhotoPageAdapter(this, mList);
        mVpPage.setAdapter(mPageAdapter);
        mVpPage.setOnPageChangeListener(this);
        mPresent = new IPhotoImp(this, this);
        mPresent.getPhoto(mSubject);
    }

    @Override
    public void showPage(Stills stills) {
        mStills = stills;
        mList = stills.getPhotos();
        mPageAdapter.setList(mList);
        mPageAdapter.notifyDataSetChanged();
        mVpPage.setCurrentItem(position, false);
        initPhoto();
    }

    @Override
    public void showToast(String toastString) {
        View inflater = LayoutInflater.from(this).inflate(R.layout.toast_login, null);
        TextView textView = (TextView) inflater.findViewById(R.id.tv_text);
        textView.setText(toastString);
        LinearLayout llShape = (LinearLayout) inflater.findViewById(R.id.ll_shape);
        llShape.setBackground(getResources().getDrawable(R.drawable.bg_green));
        ToastUtils.getInstance(getApplicationContext()).makeToastSelfViewAnim(inflater, R.style.ToastStyle);
    }

    private void initPhoto() {
        if (mList.size() != 0) {
            String commentCount = mList.get(0).getComments_count() + "";
            mTvCommentCount.setText(commentCount);
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        mItemPosition = position;
        mVpPage.setCurrentItem(position);
        subjectId = mList.get(position).getSubject_id();
        if (mStills != null) {
            String currentPosition = getString(R.string.view_image) + "(" + (position + 1) + "/" + mStills.getCount() + ")";
            String commentPosition = mList.get(position).getComments_count() + "";
            String currentDate = mList.get(position).getCreated_at();
            mTvCount.setText(currentPosition);
            mTvCommentCount.setText(commentPosition);
            mTvPhotoTime.setText(currentDate);
        }
    }


    @Override
    public void onPageScrollStateChanged(int state) {
    }


    @OnClick({R.id.iv_love, R.id.iv_comment, R.id.btn_article, R.id.iv_down, R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_love:

                break;
            case R.id.iv_comment:
                Intent commentIntent = new Intent(this, PhotoCommentActivity.class);
                commentIntent.putExtra(PhotoCommentActivity.TAG, mStills.getSubject().getId());
                startActivity(commentIntent);
                break;
            case R.id.btn_article:
                Intent allStillIntent = new Intent(this, AllStillsActivity.class);
                allStillIntent.putExtra(STILLS, mStills.getSubject().getId());
                startActivity(allStillIntent);
                break;
            case R.id.iv_down:
                //下载图片
                initPermissions();
                break;
            case R.id.iv_back:
                finish();
                break;
            default:
                break;
        }
    }

    public void initPermissions() {
        if (!PermissionUtils.isGranted(permission)) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                Log.d("XXW", "权限已经开启");
            } else {
                ActivityCompat.requestPermissions(this, new String[]{permission}, REQUEST_CODE);
            }
        } else {
            mPresent.downloadToLocal(mItemPosition);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE:
                for (int i = 0; i < permissions.length; i++) {
                    if (grantResults[i] == PERMISSION_GRANTED) {
                        Log.d("XXW", "权限" + permissions[i] + "申请成功");
                        mPresent.downloadToLocal(mItemPosition);
                    } else {
                        Log.d("XXW", "权限" + permissions[i] + "失败");
                    }
                }
                break;
            default:
                break;
        }

    }
}
