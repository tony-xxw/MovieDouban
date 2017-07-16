package mvp.wyyne.douban.moviedouban.detail.cast;


import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.adapter.PhotoFmAdapter;
import mvp.wyyne.douban.moviedouban.api.RvItemOnClick;
import mvp.wyyne.douban.moviedouban.api.bean.CastArticle;
import mvp.wyyne.douban.moviedouban.api.bean.Photos;
import mvp.wyyne.douban.moviedouban.home.BaseActivity;

/**
 * Created by XXW on 2017/6/30.
 */

public class CastArticleActivity extends BaseActivity<ICastPresent> implements ICastMain, AppBarLayout.OnOffsetChangedListener, RvItemOnClick {
    //影人条目Id
    public static final String CAST_ID = "cast_id";
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
    @BindView(R.id.ll_title)
    RelativeLayout mLlTitle;
    @BindView(R.id.iv_avatar)
    ImageView mIvAvatar;
    @BindView(R.id.fl_avatars_bg)
    FrameLayout mFlAvatarsBg;
    @BindView(R.id.rv_photo)
    RecyclerView mRvPhoto;
    private String id;
    private Palette.Swatch swatch;
    private Palette.Builder mPalette;
    private Bitmap mDrawableBitmap;
    private int boundHeight;
    private CastArticle mCastArticle;
    private CastArticleFragment mFragment;
    private FragmentManager mManager;
    private FragmentTransaction mTransaction;
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
            id = getIntent().getStringExtra(CAST_ID);
            Log.d("XXW", "id-->" + id);
        }
        mPhotosList = new ArrayList<>();
        mPresent = new CastArticleImp(this);
        setSupportActionBar(mTlTitle);
        mBarLayout.addOnOffsetChangedListener(this);

        //设置影人相册Rv
        mRvPhoto.setLayoutManager(new GridLayoutManager(this, 4));
        mFmAdapter = new PhotoFmAdapter(this, mPhotosList);
        mFmAdapter.setRvOnClick(this);
        mRvPhoto.setAdapter(mFmAdapter);
        mIvShare.setVisibility(View.VISIBLE);
        mPresent.getCastArticle(id);


    }


    @Override
    public void show() {
        mLodingView.show();
    }

    @Override
    public void hide() {
        mLodingView.hide();
    }

    @Override
    public void showPage(CastArticle article) {
        mCastArticle = article;
        mFmAdapter.setList(mCastArticle.getPhotos());
        mFmAdapter.notifyDataSetChanged();
        mFragment = CastArticleFragment.getInstance(article);
        mManager = getSupportFragmentManager();
        mTransaction = mManager.beginTransaction();
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
                            }
                        }
                    });
                }
            }
        });
    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        int y = Math.abs(verticalOffset);
        if (mIvAvatar != null) {
            boundHeight = mIvAvatar.getHeight();
            if (y <= 0) {
                titleHide();
//                Log.d("XXW", "onOffsetChanged-----<0" + verticalOffset);
                mLlTitle.setBackgroundColor(Color.argb((int) 0, 227, 29, 26));//AGB由相关工具获得，或者美工提供
            } else if (y > 0 && y <= boundHeight) {
//                Log.d("XXW", "onOffsetChanged----->0  <height"+"------"+verticalOffset);
                mLlTitle.setBackgroundColor(ContextCompat.getColor(this, R.color.colorTranslucence));
            } else {
                titleShow();
//                Log.d("XXW", "onOffsetChanged----->height---" + verticalOffset);
                mLlTitle.setBackgroundColor(swatch.getRgb());
            }
        }

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
                Log.d("XXW", "ALL");
                break;
            case PhotoFmAdapter.SINGLE:
                Log.d("XXW", "SINGLE");
                break;
            default:
                break;
        }
    }
}
