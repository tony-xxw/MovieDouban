package mvp.wyyne.douban.moviedouban.comment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.adapter.BaseRvAdapter;
import mvp.wyyne.douban.moviedouban.adapter.CommentAdapter;
import mvp.wyyne.douban.moviedouban.adapter.ReviewAdapter;
import mvp.wyyne.douban.moviedouban.api.bean.Article;
import mvp.wyyne.douban.moviedouban.api.bean.MoviesReviews;
import mvp.wyyne.douban.moviedouban.api.bean.PopularCm;
import mvp.wyyne.douban.moviedouban.api.bean.Reviews;
import mvp.wyyne.douban.moviedouban.home.BaseFragment;
import mvp.wyyne.douban.moviedouban.widget.RecycleViewUtils;

/**
 * Created by XXW on 2017/6/22.
 */

public class CommentFragment extends BaseFragment{
    public static String TAG = "CommentFragment";
    public static String REVIEWS = "reviews";
    @BindView(R.id.rv_comment)
    RecyclerView mRvComment;
    TextView mTvFooter;
    @BindView(R.id.rv_review)
    RecyclerView mRvReview;
    private CommentAdapter mAdapter;
    private List<PopularCm> mPopularCm;
    private List<Reviews> mReviewsList;
    private MoviesReviews mReviews;
    private ReviewAdapter mReviewAdapter;
    private Article mArticle;
    private LinearLayoutManager mManager;
    private LinearLayoutManager mReviewsManager;
    private View mFooterView;

    public static CommentFragment getInstance(Article article, MoviesReviews reviews) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(TAG, article);
        bundle.putParcelable(REVIEWS, reviews);
        CommentFragment fragment = new CommentFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mArticle = getArguments().getParcelable(TAG);
            mReviews = getArguments().getParcelable(REVIEWS);
            Log.d("XXW", "onCreate--->" + mArticle.getComments_count());
            mPopularCm = mArticle.getPopular_comments();
        }

    }

    @Override
    protected void refresh() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_comment;
    }

    @Override
    protected void initView() {
        mReviewsList = mReviews.getReviews();
        mReviewAdapter = new ReviewAdapter(getActivity(), mReviewsList);
        mReviewsManager = new LinearLayoutManager(getActivity());
        mReviewsManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvReview.setLayoutManager(mReviewsManager);
        addView(mReviews.getTotal(), mRvReview, mReviewAdapter);
        mRvReview.setAdapter(mReviewAdapter);
        mAdapter = new CommentAdapter(getActivity(), mPopularCm);
        mAdapter.setArticle(mArticle);
        mManager = new LinearLayoutManager(getActivity());
        mManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvComment.setLayoutManager(mManager);
        addView(mArticle.getComments_count(), mRvComment, mAdapter);
        mRvComment.setAdapter(mAdapter);

    }

    private void addView(int count, RecyclerView recyclerView, BaseRvAdapter adapter) {
        Log.d("XXW", "addView");
        adapter.setHeadView(RecycleViewUtils.addHeadView(recyclerView, R.layout.item_comment_head, getActivity()));
        mFooterView = RecycleViewUtils.addFooterView(recyclerView, R.layout.item_comment_footer, getActivity());
        mTvFooter = (TextView) mFooterView.findViewById(R.id.tv_footer);
        mTvFooter.setText("全部短评" + count + "个");
        adapter.setFooterView(mFooterView);

    }

}

