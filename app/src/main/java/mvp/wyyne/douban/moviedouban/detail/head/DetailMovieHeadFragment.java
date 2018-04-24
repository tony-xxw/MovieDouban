package mvp.wyyne.douban.moviedouban.detail.head;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
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
import mvp.wyyne.douban.moviedouban.home.IPresent;
import mvp.wyyne.douban.moviedouban.home.base.BaseFragment;
import mvp.wyyne.douban.moviedouban.login.LoginActivity;
import mvp.wyyne.douban.moviedouban.utils.StringUtils;
import mvp.wyyne.douban.moviedouban.widget.ExpandableTextView;
import mvp.wyyne.douban.moviedouban.widget.RecycleViewUtils;

import static mvp.wyyne.douban.moviedouban.utils.Constans.DATA_ARTICLE;

/**
 * @author XXW
 * @date 2017/6/30
 */

public class DetailMovieHeadFragment extends BaseFragment<IPresent> implements RvItemOnClick {
    public static final String TAG = "DetailMovieHeadFragment";
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
    private List<Casts> mCasts;
    private Article mArticle;

    public static DetailMovieHeadFragment getInstance(Article article) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(TAG, article);
        DetailMovieHeadFragment fragment = new DetailMovieHeadFragment();
        fragment.setArguments(bundle);
        return fragment;
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
        if (getArguments().getParcelable(TAG) != null) {
            mArticle = getArguments().getParcelable(TAG);
        }

        initMovieInfo(mArticle);
        initCastInfo(mArticle.getCasts(), mArticle.getDirectors());
        initCastPhoto(mArticle.getPhotos());
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
            intent.putExtra(CastArticleActivity.CAST_ID, mCasts.get(position).getId());
            getActivity().startActivity(intent);

        }
    }


    @OnClick(R.id.cv_comment)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(), CommentCountActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(DATA_ARTICLE, mArticle);
        intent.putExtra(DATA_ARTICLE, bundle);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.comment_tanslate, 0);
    }


    /**
     * @param article 电影列表数据
     */

    public void initMovieInfo(Article article) {
        mTvDetailTitle.setText(article.getTitle());
        String typeDate = article.getYear() + "/" + StringUtils.getString(article.getGenres());
        mTvDetailType.setText(typeDate);
        mTvDetailFormerly.setText(article.getOriginal_title());
        mTvDetailGrade.setText(String.valueOf(article.getRating().getAverage()));
        mTvDetailNum.setText(String.valueOf(article.getRatings_count()));
        mTbDetailNum.setRating((float) article.getRating().getAverage());
        for (String s : article.getPubdates()) {
            if (s.contains("中国大陆")) {
                mTvDetailShow.setText(getString(R.string.china));
            }
        }
        if (article.getDurations() != null) {
            if (article.getDurations().size() != 0) {
                String detailTime = getString(R.string.movie_time) + article.getDurations().get(0);
                mTvDetailTime.setText(detailTime);
            }
        }
        mEtSummary.setText(article.getSummary());
    }

    /**
     * @param castsList 演员列表
     * @param directors 导演列表
     */
    public void initCastInfo(List<Casts> castsList, List<Directors> directors) {
        mCasts = castsList;
        CastAdapter mCastAdapter = new CastAdapter(getActivity(), mCasts);
        LinearLayoutManager mCastManager = new LinearLayoutManager(getActivity());
        mCastManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvCasts.setLayoutManager(mCastManager);
        mRvCasts.setAdapter(mCastAdapter);
        mCastAdapter.setHeadView(RecycleViewUtils.addHeadView(R.layout.item_cast_head, getActivity()));
        mCastAdapter.setDirectorses(directors);
        mCastAdapter.setRvOnClick(this);
    }


    public void initCastPhoto(List<Photos> photosList) {
        List<Trailers> mTrailers = mArticle.getTrailers();
        PhotoAdapter mPhotosAdapter = new PhotoAdapter(getActivity(), photosList);
        LinearLayoutManager mStillsManager = new LinearLayoutManager(getActivity());
        mStillsManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvPhoto.setLayoutManager(mStillsManager);
        if (mTrailers != null && mTrailers.size() != 0) {
            mPhotosAdapter.setHeadData(mTrailers);
            mPhotosAdapter.setHeadView(RecycleViewUtils.addHeadView(R.layout.moview_detail_stills_head, getActivity()));
        }
        mPhotosAdapter.setFooterData(mArticle.getPhotos_count());
        mPhotosAdapter.setFooterView(RecycleViewUtils.addHeadView(R.layout.moview_detail_stills_footer, getActivity()));
        mPhotosAdapter.setRvOnClick(this);
        mRvPhoto.setAdapter(mPhotosAdapter);
    }

    @OnClick({R.id.btn_think_see, R.id.ll_have_seen})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_think_see:
                Intent thinkSeeIntent = new Intent(getActivity(), LoginActivity.class);
                startActivity(thinkSeeIntent);
                break;
            case R.id.ll_have_seen:
                Intent haveSeenIntent = new Intent(getActivity(), LoginActivity.class);
                startActivity(haveSeenIntent);
                break;
            default:
                break;
        }
    }
}
