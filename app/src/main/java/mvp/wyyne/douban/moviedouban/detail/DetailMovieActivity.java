package mvp.wyyne.douban.moviedouban.detail;


import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import butterknife.BindView;
import butterknife.OnClick;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.bean.Article;
import mvp.wyyne.douban.moviedouban.detail.head.DetailMovieHeadFragment;
import mvp.wyyne.douban.moviedouban.detail.photo.PhotoActivity;
import mvp.wyyne.douban.moviedouban.home.base.BaseActivity;
import mvp.wyyne.douban.moviedouban.utils.StatusUtils;

import static com.wynne.common.Constant.DETAIL_TAG;

/**
 * Y400 测试
 *
 * @author XXW
 * @date 2017/6/18
 */

public class DetailMovieActivity extends BaseActivity<DetailMovieImp> implements
        IDetailMain, AppBarLayout.OnOffsetChangedListener {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_img_title)
    TextView mTvImgTitle;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rl_content)
    RelativeLayout mLlTitle;
    @BindView(R.id.ll_layout)
    LinearLayout mLayout;
    @BindView(R.id.tl_bar)
    Toolbar mTlBar;
    @BindView(R.id.vp_detail)
    ViewPager mPager;
    @BindView(R.id.tl_detail)
    TabLayout mTabLayout;
    @BindView(R.id.abl_layout)
    AppBarLayout mBarLayout;
    @BindView(R.id.nv_detail)
    NestedScrollView mNestedScrollView;
    @BindView(R.id.iv_avatars)
    ImageView mIvAvatars;
    @BindView(R.id.fl_avatars_bg)
    FrameLayout mFlAvatarsBg;
    @BindView(R.id.fl_content)
    FrameLayout mFlContent;
    private String mSubjectsId;
    private Bitmap mDrawableBitmap;
    private Article mArticle;
    private Palette.Swatch swatch;

    @Override
    protected void refresh() {
        Log.d("XXW", "Y400");
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_movie_detail;
    }

    @Override
    protected void initView() {
        if (getIntent().getStringExtra(DETAIL_TAG) != null) {
            mSubjectsId = getIntent().getStringExtra(DETAIL_TAG);
        }
        setSupportActionBar(mTlBar);
        //NestedScrollView中嵌套ViewPager不显示 设置为true
        mNestedScrollView.setFillViewport(true);
        mBarLayout.addOnOffsetChangedListener(this);
        mPresent = new DetailMovieImp(this, getSupportFragmentManager());
        mPresent.getArticle(mSubjectsId);

        mTvImgTitle.setTextColor(getResources().getColor(R.color.white));

        //初始化TabLayout
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setTabTextColors(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.colorBlack)));

    }


    @Override
    public void initMovieImg(Article article) {
        FragmentManager mManager = getSupportFragmentManager();
        FragmentTransaction mTransaction = mManager.beginTransaction();
        mTransaction.add(R.id.fl_head, DetailMovieHeadFragment.getInstance(article));
        mTransaction.commit();
        mArticle = article;
        setBackGroundBg(article.getImages().getLarge());

    }

    @Override
    public void initMovieGrade() {
        String url = mArticle.getImages().getLarge();
        Glide.with(this).load(url).into(mIvAvatars);
        mPresent.initPage(mPager);
    }

    @Override
    public void onBindPage() {
        mTvTitle.setText(mArticle.getTitle());
        mTabLayout.setupWithViewPager(mPager);
    }


    /**
     * 设置背景图片和设置电影海报图片
     */
    public void setBackGroundBg(String url) {
        Glide.with(this).load(url).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                if (resource != null) {
                    mDrawableBitmap = resource;
                    //设置颜色调试器
                    Palette palette = Palette.from(mDrawableBitmap).generate();
                    //颜色调试器回调监听
                    swatch = palette.getMutedSwatch();
                    if (swatch == null) {
                        swatch = palette.getLightMutedSwatch();
                    }

                    if (swatch != null) {
                        mFlAvatarsBg.setBackgroundColor(swatch.getRgb());
                        mLlTitle.setBackgroundColor(Color.TRANSPARENT);
                        mLayout.setBackgroundColor(Color.TRANSPARENT);
                        StatusUtils.setStatusColor(DetailMovieActivity.this, swatch.getRgb(), true);
                    } else {
                        mFlAvatarsBg.setBackgroundColor(Color.WHITE);
                        mLlTitle.setBackgroundColor(Color.TRANSPARENT);
                        mLayout.setBackgroundColor(Color.TRANSPARENT);
                        StatusUtils.setStatusColor(DetailMovieActivity.this, Color.WHITE, true);
                    }
                }
            }
        });
    }

    @Override
    public void show() {
        isDisplayLoading(true);
        mFlContent.setVisibility(View.VISIBLE);
    }

    @Override
    public void hide() {
        isDisplayLoading(false);
        mFlContent.setVisibility(View.GONE);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        int y = Math.abs(verticalOffset);
        if (mIvAvatars != null) {
            int boundHeight = mIvAvatars.getHeight();
            if (y <= 0) {
                titleHide();
                mLlTitle.setBackgroundColor(Color.argb((int) 0, 227, 29, 26));
            } else if (y > 0 && y <= boundHeight) {
                mLlTitle.setBackgroundColor(ContextCompat.getColor(this, R.color.colorTranslucence));
            } else {
                titleShow();
                if (swatch != null) {
                    mLlTitle.setBackgroundColor(swatch.getRgb());
                } else {
                    mLlTitle.setBackgroundColor(Color.WHITE);
                }
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


    @OnClick({R.id.iv_back, R.id.iv_avatars, R.id.iv_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_avatars:
                Intent intent = new Intent(this, PhotoActivity.class);
                intent.putExtra(PhotoActivity.ID, mArticle.getId());
                intent.putExtra(PhotoActivity.POSITION, 0);
                startActivity(intent);
                break;
            case R.id.iv_share:
//                ShareUtils.shareBinaryContent(this, mArticle.getImages().getLarge());
                break;
            default:
                break;
        }
    }

}
