package mvp.wyyne.douban.moviedouban.detail.head;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

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
import mvp.wyyne.douban.moviedouban.detail.cast.CastArticleActivity;
import mvp.wyyne.douban.moviedouban.detail.photo.PhotoActivity;
import mvp.wyyne.douban.moviedouban.detail.stills.AllStillsActivity;
import mvp.wyyne.douban.moviedouban.detail.stills.StillsActivity;
import mvp.wyyne.douban.moviedouban.home.BaseFragment;
import mvp.wyyne.douban.moviedouban.utils.StringUtils;
import mvp.wyyne.douban.moviedouban.widget.ExpandableTextView;
import mvp.wyyne.douban.moviedouban.widget.RecycleViewUtils;

/**
 * Created by XXW on 2017/6/30.
 */

public class DetailMovieHeadFragment extends BaseFragment<DHeadImp> implements IDHeadMain, RvItemOnClick {
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
    private Article mArticle;
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
            Log.d("XXW", "Arrticle--" + mArticle.toString());
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
        mTvDetailTitle.setText(mArticle.getTitle());
        mTvDetailType.setText(mArticle.getYear() + "/" + StringUtils.getString(mArticle.getGenres()));
        mTvDetailFormerly.setText(mArticle.getOriginal_title());
        mTvDetailGrade.setText(String.valueOf(mArticle.getRating().getAverage()));
        mTvDetailNum.setText(String.valueOf(mArticle.getRatings_count()));
        mTbDetailNum.setRating((float) mArticle.getRating().getAverage());
        for (String s : mArticle.getPubdates()) {
            if (s.contains("中国大陆")) {
                mTvDetailShow.setText(getString(R.string.china) + s);
            }
        }
        if (mArticle.getDurations() != null) {
            if (mArticle.getDurations().size() != 0) {
                mTvDetailTime.setText(getString(R.string.movie_time) + mArticle.getDurations().get(0));
            }
        }
        mEtSummary.setText(mArticle.getSummary());

        //初始化演员列表
        mCastses = mArticle.getCasts();
        mDirectorses = mArticle.getDirectors();
        mCastAdapter = new CastAdapter(getActivity(), mCastses);
        mCastManager = new LinearLayoutManager(getActivity());
        mCastManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvCasts.setLayoutManager(mCastManager);
        mRvCasts.setAdapter(mCastAdapter);
        mCastAdapter.setHeadView(RecycleViewUtils.addHeadView(R.layout.item_cast_head, getActivity()));
        mCastAdapter.setDirectorses(mDirectorses);
        mCastAdapter.setRvOnClick(this);

        //初始化剧照
        mPhoto = mArticle.getPhotos();
        mTrailerses = mArticle.getTrailers();
        mPhotosAdapter = new PhotoAdapter(getActivity(), mPhoto);
        mStillsManager = new LinearLayoutManager(getActivity());
        mStillsManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvPhoto.setLayoutManager(mStillsManager);
        if (mTrailerses != null && mTrailerses.size() != 0) {
            mPhotosAdapter.setHeadData(mTrailerses);
            mPhotosAdapter.setHeadView(RecycleViewUtils.addHeadView(R.layout.moview_detail_stills_head, getActivity()));
        }
        mPhotosAdapter.setFooterData(mArticle.getPhotos_count());
        mPhotosAdapter.setFooterView(RecycleViewUtils.addHeadView(R.layout.moview_detail_stills_footer, getActivity()));
        mPhotosAdapter.setRvOnClick(this);
        mRvPhoto.setAdapter(mPhotosAdapter);
    }


    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }


    @Override
    public void onItemClick(int position, String tag) {
        if (tag.equals(PhotoAdapter.TAG)) {
            Intent singlePt = new Intent(getActivity(), PhotoActivity.class);
            singlePt.putExtra(PhotoActivity.ID, mArticle.getId());
            singlePt.putExtra(PhotoActivity.POSITION, position);
            getActivity().startActivity(singlePt);
        } else if (tag.equals(PhotoAdapter.HEAD)) {
            Intent mCastHead = new Intent(getActivity(), StillsActivity.class);
            mCastHead.putExtra(StillsActivity.MESSAGE, mArticle);
            getActivity().startActivity(mCastHead);
        } else if (tag.equals(PhotoAdapter.FOOTER)) {
            Intent mCastFooter = new Intent(getActivity(), AllStillsActivity.class);
            mCastFooter.putExtra(AllStillsActivity.STILLS, mArticle.getId());
            getActivity().startActivity(mCastFooter);
        } else if (tag.equals(CastAdapter.CAST)) {
            //跳转影人条目
            Intent intent = new Intent(getActivity(), CastArticleActivity.class);
            intent.putExtra(CastArticleActivity.CAST_ID, mCastses.get(position).getId());
            getActivity().startActivity(intent);

        }
    }
}
