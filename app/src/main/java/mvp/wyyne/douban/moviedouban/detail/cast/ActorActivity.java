package mvp.wyyne.douban.moviedouban.detail.cast;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.adapter.PhotoFmAdapter;
import mvp.wyyne.douban.moviedouban.api.RvItemOnClick;
import mvp.wyyne.douban.moviedouban.api.bean.ActorInfo;
import mvp.wyyne.douban.moviedouban.api.bean.Photos;
import mvp.wyyne.douban.moviedouban.detail.photo.CastPhotoActivity;
import mvp.wyyne.douban.moviedouban.detail.photo.PhotoActivity;
import mvp.wyyne.douban.moviedouban.detail.stills.AllStillsActivity;
import mvp.wyyne.douban.moviedouban.home.base.BaseActivity;
import mvp.wyyne.douban.moviedouban.utils.StatusUtils;

/**
 * @author XXW
 * @date 2017/6/30
 */

public class ActorActivity extends BaseActivity<IActorPresent> implements IActorMain,
        NestedScrollView.OnScrollChangeListener, RvItemOnClick {
    /**
     * 演员ID
     */
    public static final String ACTORID = "actorId";
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_stills_title)
    TextView mTvStillsTitle;
    @BindView(R.id.iv_share)
    ImageView mIvShare;
    @BindView(R.id.tl_title)
    Toolbar mTlTitle;
    @BindView(R.id.abl_ca)
    AppBarLayout mBarLayout;
    @BindView(R.id.rl_content)
    RelativeLayout mLlTitle;
    @BindView(R.id.iv_avatar)
    ImageView mIvAvatar;
    @BindView(R.id.fl_avatars_bg)
    FrameLayout mFlAvatarsBg;
    @BindView(R.id.rv_photo)
    RecyclerView mRvPhoto;
    @BindView(R.id.nsv_content)
    NestedScrollView mNsvContent;
    private String id;
    private Palette.Swatch swatch;
    private Palette.Builder mPalette;
    private Bitmap mDrawableBitmap;
    private ActorInfo mCastArticle;
    private ActorDetailFragment mFragment;
    private List<Photos> mPhotosList;
    private PhotoFmAdapter mFmAdapter;


    @Override
    protected void refresh() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_cast_detail;
    }

    @Override
    protected void initView() {
        if (getIntent() != null) {
            id = getIntent().getStringExtra(ACTORID);
        }
        mPhotosList = new ArrayList<>();
        mPresent = new ActorArticleImp(this);
        setSupportActionBar(mTlTitle);
        mNsvContent.setOnScrollChangeListener(this);
        //设置影人相册Rv
        mRvPhoto.setLayoutManager(new GridLayoutManager(this, 4));
        mRvPhoto.setNestedScrollingEnabled(false);
        mFmAdapter = new PhotoFmAdapter(this, mPhotosList);
        mFmAdapter.setRvOnClick(this);
        mRvPhoto.setAdapter(mFmAdapter);
        mIvShare.setVisibility(View.VISIBLE);
        mPresent.getCastInfo(id);


    }


    @Override
    public void show() {
        mLoadingView.show();
    }

    @Override
    public void hide() {
        mLoadingView.hide();
    }

    @Override
    public void showPage(ActorInfo article) {
        mCastArticle = article;
        mPhotosList = mCastArticle.getPhotos();
        mFmAdapter.setList(mCastArticle.getPhotos());
        mFmAdapter.notifyDataSetChanged();
        if (mFragment == null) {
            mFragment = ActorDetailFragment.getInstance(article);
        }
        FragmentManager mManager = getSupportFragmentManager();
        FragmentTransaction mTransaction = mManager.beginTransaction();
        mTransaction.add(R.id.fl_content, mFragment);
        mTransaction.commit();
        setBackGroudBg(article.getAvatars().getLarge());

    }

    public void setBackGroudBg(String url) {
        Glide.with(this).load(url).into(mIvAvatar);
        Glide.with(this).load(url).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                if (resource != null) {
                    mDrawableBitmap = resource;
                    //设置颜色调试器
                    mPalette = Palette.from(mDrawableBitmap);
                    //颜色调试器回调监听
                    mPalette.generate(new Palette.PaletteAsyncListener() {
                        @Override
                        public void onGenerated(Palette palette) {
                            swatch = palette.getMutedSwatch();
                            if (swatch != null) {
                                mFlAvatarsBg.setBackgroundColor(swatch.getRgb());
                                mLlTitle.setBackgroundColor(Color.TRANSPARENT);
                                mTlTitle.setBackgroundColor(swatch.getRgb());
                                StatusUtils.setStatusColor(ActorActivity.this, swatch.getRgb(), true);
                            }
                        }
                    });
                }
            }
        });
    }


    public void titleHide() {
        mTvStillsTitle.setTextColor(Color.WHITE);
        mTvStillsTitle.setText(R.string.cast);
    }

    public void titleShow() {
        mTvStillsTitle.setTextColor(Color.WHITE);
        mTvStillsTitle.setText(mCastArticle.getName());
    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onItemClick(int position, String tag) {
        switch (tag) {
            case PhotoFmAdapter.ALL:
                Intent all = new Intent(this, AllStillsActivity.class);
                all.putExtra(AllStillsActivity.CAST, id);
                startActivity(all);
                break;
            case PhotoFmAdapter.SINGLE:
                Intent intent = new Intent(this, CastPhotoActivity.class);
                intent.putExtra(PhotoActivity.ID, id);
                intent.putExtra(PhotoActivity.POSITION, position);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        int y = Math.abs(scrollY);
        if (mIvAvatar != null) {
            int boundHeight = mIvAvatar.getHeight();
            if (y <= 0) {
                titleHide();
                //AGB由相关工具获得，或者美工提供
                mLlTitle.setBackgroundColor(Color.argb((int) 0, 227, 29, 26));
            } else if (y > 0 && y <= boundHeight) {
                mLlTitle.setBackgroundColor(ContextCompat.getColor(this, R.color.colorTranslucence));
                titleShow();
            } else {
                titleShow();
                mLlTitle.setBackgroundColor(swatch.getRgb());
            }
        }
    }
}
