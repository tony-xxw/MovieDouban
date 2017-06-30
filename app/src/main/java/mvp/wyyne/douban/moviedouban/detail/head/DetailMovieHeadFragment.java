package mvp.wyyne.douban.moviedouban.detail.head;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.adapter.CastAdapter;
import mvp.wyyne.douban.moviedouban.adapter.PhotoAdapter;
import mvp.wyyne.douban.moviedouban.api.RvItemOnClick;
import mvp.wyyne.douban.moviedouban.api.bean.Article;
import mvp.wyyne.douban.moviedouban.api.bean.Casts;
import mvp.wyyne.douban.moviedouban.api.bean.Directors;
import mvp.wyyne.douban.moviedouban.api.bean.Photos;
import mvp.wyyne.douban.moviedouban.api.bean.Trailers;
import mvp.wyyne.douban.moviedouban.home.BaseFragment;
import mvp.wyyne.douban.moviedouban.widget.ExpandableTextView;
import mvp.wyyne.douban.moviedouban.widget.RecycleViewUtils;

/**
 * Created by XXW on 2017/6/30.
 */

public class DetailMovieHeadFragment extends BaseFragment<DHeadImp> implements IDHeadMain, RvItemOnClick {

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
    @BindView(R.id.iv_detail_shop)
    ImageView mIvDetailShop;
    @BindView(R.id.tv_detail_shop)
    TextView mTvDetailShop;
    @BindView(R.id.et_summary)
    ExpandableTextView mEtSummary;
    @BindView(R.id.rv_casts)
    RecyclerView mRvCasts;
    @BindView(R.id.rv_photos)
    RecyclerView mRvPhoto;
    private String mSubjectsId;
    private Bitmap mDrawableBitmap;
    private Palette.Builder mPalette;
    private Article mArticle;
    private int boundHeight;
    private Palette.Swatch swatch;
    private List<Casts> mCastses;
    private CastAdapter mCastAdapter;
    private PhotoAdapter mPhotosAdapter;
    private LinearLayoutManager mCastManager;
    private LinearLayoutManager mStillsManager;
    private List<Photos> mPhoto;
    private List<Directors> mDirectorses;
    private List<Trailers> mTrailerses;

    public static final String TAG = "DetailMovieHeadFragment";

    public static DetailMovieHeadFragment getInstance(Article article) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(TAG, article);
        DetailMovieHeadFragment fragment = new DetailMovieHeadFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().getParcelable(TAG) != null) {
            mArticle = getArguments().getParcelable(TAG);
        }
    }

    @Override
    protected void refresh() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail_head;
    }

    @Override
    protected void initView() {
        //初始化演员列表
        mCastses = new ArrayList<>();
        mDirectorses = new ArrayList<>();
        mCastAdapter = new CastAdapter(this, mCastses);
        mCastManager = new LinearLayoutManager(this);
        mCastManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvCasts.setLayoutManager(mCastManager);
        mRvCasts.setAdapter(mCastAdapter);
        mCastAdapter.setHeadView(RecycleViewUtils.addHeadView(mRvCasts, R.layout.item_cast_head, getActivity()));
        mCastAdapter.setDirectorses(mDirectorses);

        //初始化剧照
        mPhoto = new ArrayList<>();
        mTrailerses = new ArrayList<>();
        mPhotosAdapter = new PhotoAdapter(getActivity(), mPhoto);
        mPhotosAdapter.setHeadData(mTrailerses);
        mPhotosAdapter.setFooterData(0);
        mStillsManager = new LinearLayoutManager(getActivity());
        mStillsManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvPhoto.setLayoutManager(mStillsManager);
        mPhotosAdapter.setHeadView(RecycleViewUtils.addHeadView(mRvPhoto, R.layout.moview_detail_stills_head, getActivity()));
        mPhotosAdapter.setFooterView(RecycleViewUtils.addHeadView(mRvPhoto, R.layout.moview_detail_stills_footer, getActivity()));
        mPhotosAdapter.setRvOnClick(this);
        mRvPhoto.setAdapter(mPhotosAdapter);

        String url = mArticle.getImages().getLarge();
        Glide.with(this).load(url).into(mIvAvatars);
        setBackGroudBg(url);
    }



    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }


    @Override
    public void onItemClick(int position, String tag) {

    }
}
