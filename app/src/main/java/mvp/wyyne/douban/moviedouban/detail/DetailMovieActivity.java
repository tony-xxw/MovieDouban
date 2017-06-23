package mvp.wyyne.douban.moviedouban.detail;


import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.bean.Article;
import mvp.wyyne.douban.moviedouban.home.BaseActivity;
import mvp.wyyne.douban.moviedouban.utils.StringUtils;
import mvp.wyyne.douban.moviedouban.widget.ObservableScrollView;

/**
 * Created by XXW on 2017/6/18.
 */

public class DetailMovieActivity extends BaseActivity<DetailMoviePresent> implements
        IDetailMain, AppBarLayout.OnOffsetChangedListener {
    public static final String DETAIL_TAG = "detail";
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_img_title)
    TextView mTvImgTitle;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.iv_share)
    ImageView mIvShare;
    @BindView(R.id.ll_title)
    RelativeLayout mLlTitle;
    @BindView(R.id.ll_layout)
    LinearLayout mLayout;
    @BindView(R.id.iv_avatars)
    ImageView mIvAvatars;
    @BindView(R.id.fl_avatars_bg)
    FrameLayout mFlAvatarsBg;
    @BindView(R.id.tv_detail_title)
    TextView mTvDetailTitle;
    @BindView(R.id.tv_detail_type)
    TextView mTvDetailType;
    @BindView(R.id.tv_detail_formerly)
    TextView mTvDetailFormerly;
    @BindView(R.id.tv_detail_show)
    TextView mTvDetailShow;
    @BindView(R.id.tv_detail_time)
    TextView mTvDetailTime;
    @BindView(R.id.tv_detail_grade)
    TextView mTvDetailGrade;
    @BindView(R.id.tb_detail_num)
    RatingBar mTbDetailNum;
    @BindView(R.id.tv_detail_num)
    TextView mTvDetailNum;
    @BindView(R.id.tl_bar)
    Toolbar mTlBar;
    @BindView(R.id.iv_detail_shop)
    ImageView mIvDetailShop;
    @BindView(R.id.tv_detail_shop)
    TextView mTvDetailShop;
    @BindView(R.id.vp_detail)
    ViewPager mPager;
    @BindView(R.id.tl_detail)
    TabLayout mTabLayout;
    @BindView(R.id.os_scroll)
    ObservableScrollView mScrollView;
    @BindView(R.id.abl_layout)
    AppBarLayout mBarLayout;
    private String mSubjectsId;
    private Bitmap mDrawableBitmap;
    private Palette.Builder mPalette;
    private Article mArticle;
    private ViewTreeObserver mObserver;
    private int boundHeight;
    private Palette.Swatch swatch;


    @Override
    protected void refresh() {

    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_movie_detail;
    }

    @Override
    protected void initView() {
        if (getIntent().getStringExtra(DETAIL_TAG) != null) {
            mSubjectsId = getIntent().getStringExtra(DETAIL_TAG);
            Log.d("XXW", "mList------->" + mSubjectsId);
        }
        setSupportActionBar(mTlBar);
        mBarLayout.addOnOffsetChangedListener(this);
        mPresent = new DetailMoviePresent(this, getSupportFragmentManager());
        mPresent.getArticle(mSubjectsId);

        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.addTab(mTabLayout.newTab().setText("评论"));
        mTabLayout.addTab(mTabLayout.newTab().setText("讨论区"));
        mTabLayout.setTabTextColors(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.colorBlack)));
        mPresent.initPage(mPager);
    }


    @Override
    public void initMovieImg(Article article) {
        String url = article.getImages().getLarge();
        mArticle = article;
        Glide.with(this).load(url).into(mIvAvatars);
        setBackGroudBg(url);
    }

    @Override
    public void initMovieGrade() {
        mTvDetailTitle.setText(mArticle.getTitle());

        mTvDetailType.setText(mArticle.getYear() + "/" + StringUtils.getString(mArticle.getGenres()));
//        mTvDetailType.setText();
        mTvDetailFormerly.setText(mArticle.getOriginal_title());
        mTvDetailGrade.setText(String.valueOf(mArticle.getRating().getAverage()));
        mTvDetailNum.setText(String.valueOf(mArticle.getRatings_count()));
//        mTbDetailNum.setNumStars(Integer.valueOf(mArticle.getRating().getStars()));
        mTvTitle.setText(mArticle.getTitle());
        mTbDetailNum.setRating((float) mArticle.getRating().getAverage());
    }

    @Override
    public void onBindPage() {
        mTabLayout.setupWithViewPager(mPager);
    }


    //设置背景图片和设置电影海报图片
    public void setBackGroudBg(String url) {
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
                                Log.d("XXW", "noinit");
                                mFlAvatarsBg.setBackgroundColor(swatch.getRgb());
                                mLlTitle.setBackgroundColor(Color.TRANSPARENT);
                                mLayout.setBackgroundColor(Color.TRANSPARENT);
                            }
                        }
                    });
                }
            }
        });
    }



    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        int y = Math.abs(verticalOffset);
        Log.d("XXW", "onOffsetChanged" + verticalOffset);
        if (mIvAvatars != null) {
            boundHeight = mIvAvatars.getHeight();
            if (y <= 0) {
                titleHide();
//                Log.d("XXW", "onOffsetChanged-----<0" + verticalOffset);
                mLlTitle.setBackgroundColor(Color.argb((int) 0, 227, 29, 26));//AGB由相关工具获得，或者美工提供
            } else if (y > 0 && y <= boundHeight) {
                titleShow();
//                Log.d("XXW", "onOffsetChanged----->0  <height"+"------"+verticalOffset);
                float scale = (float) y / boundHeight;
                float alpha = (255 * scale);
                // 只是layout背景透明(仿知乎滑动效果)
                mLlTitle.setBackgroundColor(swatch.getBodyTextColor());
            } else {
                titleShow();
//                Log.d("XXW", "onOffsetChanged----->height---" + verticalOffset);
                mLlTitle.setBackgroundColor(swatch.getRgb());
            }
        }

    }


    public void titleHide() {
        mTvImgTitle.setVisibility(View.VISIBLE);
        mTvTitle.setVisibility(View.GONE);
    }

    public void titleShow() {
        mTvImgTitle.setVisibility(View.GONE);
        mTvTitle.setVisibility(View.VISIBLE);
    }
}
