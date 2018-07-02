package mvp.wyyne.douban.moviedouban.detail.head;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.TimeUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import mvp.wyyne.douban.moviedouban.AndroidApplication;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.adapter.CastAdapter;
import mvp.wyyne.douban.moviedouban.adapter.PhotoAdapter;
import mvp.wyyne.douban.moviedouban.api.RvItemOnClick;
import mvp.wyyne.douban.moviedouban.api.bean.Article;
import mvp.wyyne.douban.moviedouban.api.bean.Casts;
import mvp.wyyne.douban.moviedouban.api.bean.Directors;
import mvp.wyyne.douban.moviedouban.api.bean.Photos;
import mvp.wyyne.douban.moviedouban.api.bean.Trailers;
import mvp.wyyne.douban.moviedouban.api.model.WannaModel;
import mvp.wyyne.douban.moviedouban.detail.cast.ActorActivity;
import mvp.wyyne.douban.moviedouban.detail.photo.PhotoActivity;
import mvp.wyyne.douban.moviedouban.detail.stills.AllStillsActivity;
import mvp.wyyne.douban.moviedouban.detail.stills.StillsActivity;
import mvp.wyyne.douban.moviedouban.home.IPresent;
import mvp.wyyne.douban.moviedouban.home.base.BaseFragment;
import mvp.wyyne.douban.moviedouban.interest.InterestActivity;
import mvp.wyyne.douban.moviedouban.interest.ShareLabelActivity;
import mvp.wyyne.douban.moviedouban.login.LoginActivity;
import mvp.wyyne.douban.moviedouban.utils.ResourcesUtils;
import mvp.wyyne.douban.moviedouban.utils.StringUtils;
import mvp.wyyne.douban.moviedouban.widget.ExpandableTextView;
import mvp.wyyne.douban.moviedouban.widget.RecycleViewUtils;
import mvp.wyyne.douban.moviedouban.widget.StarView;

import static mvp.wyyne.douban.moviedouban.utils.Constant.DATA_ARTICLE;

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
    @BindView(R.id.sv_grade)
    StarView svGrade;
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
    @BindView(R.id.cv_mark)
    CardView mCvMark;
    @BindView(R.id.btn_wanna)
    Button mBtnWanna;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_reason)
    TextView tvReason;
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
    public void onResume() {
        super.onResume();
        if (WannaModel.getInstance().queryMark(mArticle.getTitle()) && AndroidApplication.getApplication().isLogin()) {
            mCvMark.setVisibility(View.VISIBLE);
            tvName.setText("Wynne");
            tvTime.setText(TimeUtils.getNowDate().toString());
            mBtnWanna.setText("已想看");
            mBtnWanna.setTextColor(ResourcesUtils.getColor(R.color.colorGray, getActivity()));
            mBtnWanna.setBackground(ResourcesUtils.getContextDrawable(R.drawable.bg_btn_gray, getActivity()));
            String reason = WannaModel.getInstance().queryReason(mArticle.getTitle());
            if (!TextUtils.isEmpty(reason)) {
                tvReason.setText(reason);
            } else {
                tvReason.setVisibility(View.GONE);
            }
        } else {
            mCvMark.setVisibility(View.GONE);
            mBtnWanna.setText("想看");
            mBtnWanna.setTextColor(ResourcesUtils.getColor(R.color.colorOrange, getActivity()));
            mBtnWanna.setBackground(ResourcesUtils.getContextDrawable(R.drawable.bg_btn_orange_select, getActivity()));
        }


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
            Intent intent = new Intent(getActivity(), ActorActivity.class);
            intent.putExtra(ActorActivity.ACTORID, mCasts.get(position).getId());
            getActivity().startActivity(intent);

        }
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
        svGrade.setStartMark((int) article.getRating().getAverage());
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
        mCastAdapter.setHeadView(RecycleViewUtils.addHeadView(R.layout.item_actor_layout, getActivity(), mRvCasts));
        mCastAdapter.setDirectorses(directors);
        mCastAdapter.setRvOnClick(this);

        mRvCasts.setAdapter(mCastAdapter);
    }


    public void initCastPhoto(List<Photos> photosList) {
        List<Trailers> mTrailers = mArticle.getTrailers();
        PhotoAdapter mPhotosAdapter = new PhotoAdapter(getActivity(), photosList);
        LinearLayoutManager mStillsManager = new LinearLayoutManager(getActivity());
        mStillsManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvPhoto.setLayoutManager(mStillsManager);
        if (mTrailers != null && mTrailers.size() != 0) {
            mPhotosAdapter.setHeadData(mTrailers);
            mPhotosAdapter.setHeadView(RecycleViewUtils.addHeadView(R.layout.moview_detail_stills_head, getActivity(), mRvPhoto));
        }
        mPhotosAdapter.setFooterData(mArticle.getPhotos_count());
        mPhotosAdapter.setFooterView(RecycleViewUtils.addHeadView(R.layout.moview_detail_stills_footer, getActivity(), mRvPhoto));
        mPhotosAdapter.setRvOnClick(this);
        mRvPhoto.setAdapter(mPhotosAdapter);
    }

    @OnClick({R.id.btn_wanna, R.id.ll_read, R.id.cv_comment, R.id.tv_share, R.id.rl_edit})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_wanna:
                if (AndroidApplication.getApplication().isLogin()) {
                    intentInterest();
                } else {
                    Intent thinkSeeIntent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(thinkSeeIntent);
                }
                break;
            case R.id.ll_read:
                if (AndroidApplication.getApplication().isLogin()) {
                    intentInterest();
                } else {
                    Intent haveSeenIntent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(haveSeenIntent);
                }
                break;
            case R.id.cv_comment:
                Intent intent = new Intent(getActivity(), CommentCountActivity.class);
                intent.putExtra(DATA_ARTICLE, bundleArticle(DATA_ARTICLE));
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.comment_tanslate, 0);
                break;
            case R.id.tv_share:
                if (AndroidApplication.getApplication().isLogin()) {
                    Intent shareIntent = new Intent(getActivity(), ShareLabelActivity.class);
                    shareIntent.putExtra(ShareLabelActivity.TAG, bundleArticle(ShareLabelActivity.TAG));
                    startActivity(shareIntent);
                } else {
                    Intent thinkSeeIntent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(thinkSeeIntent);

                }
                break;
            case R.id.rl_edit:
                intentInterest();
                break;
            default:
                break;
        }
    }


    public void intentInterest() {
        Intent interestIntent = new Intent(getActivity(), InterestActivity.class);
        interestIntent.putExtra(InterestActivity.TAG, mArticle);
        startActivity(interestIntent);
    }

    public Bundle bundleArticle(String tag) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(tag, mArticle);
        return bundle;
    }

}
