package mvp.wyyne.douban.moviedouban.comment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.adapter.CommentAdapter;
import mvp.wyyne.douban.moviedouban.adapter.ReviewAdapter;
import mvp.wyyne.douban.moviedouban.api.bean.Article;
import mvp.wyyne.douban.moviedouban.api.bean.PopularCm;
import mvp.wyyne.douban.moviedouban.home.base.BaseFragment;
import mvp.wyyne.douban.moviedouban.widget.RecycleViewUtils;

/**
 * Created by XXW on 2017/6/22.
 */

public class CommentFragment extends BaseFragment {
    public static String TAG = "CommentFragment";
    @BindView(R.id.rv_comment)
    RecyclerView mRvComment;
    @BindView(R.id.rv_review)
    RecyclerView mRvReview;
    private CommentAdapter mAdapter;
    private List<PopularCm> mPopularCm;
    private ReviewAdapter mReviewAdapter;
    private Article mArticle;
    private LinearLayoutManager mManager;
    private LinearLayoutManager mReviewsManager;

    public static CommentFragment getInstance(Article article) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(TAG, article);
        CommentFragment fragment = new CommentFragment();
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
        return R.layout.fragment_comment;
    }

    @Override
    protected void initView() {

        mReviewAdapter = new ReviewAdapter(getActivity(), mArticle.getPopular_reviews());
        mReviewsManager = new LinearLayoutManager(getActivity());
        mReviewsManager.setOrientation(LinearLayoutManager.VERTICAL);
        mReviewAdapter.setArticle(mArticle);
        mRvReview.setLayoutManager(mReviewsManager);
        mReviewAdapter.setHeadView(RecycleViewUtils.addHeadView(R.layout.item_comment_head, getActivity()));
        mReviewAdapter.setFooterView(RecycleViewUtils.addFooterView(R.layout.item_comment_footer, getActivity()));
        mRvReview.setAdapter(mReviewAdapter);
        mAdapter = new CommentAdapter(getActivity(), mArticle.getPopular_comments());
        mAdapter.setArticle(mArticle);
        mManager = new LinearLayoutManager(getActivity());
        mManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvComment.setLayoutManager(mManager);
        mAdapter.setHeadView(RecycleViewUtils.addHeadView(R.layout.item_comment_head, getActivity()));
        mAdapter.setFooterView(RecycleViewUtils.addFooterView(R.layout.item_comment_footer, getActivity()));
        mRvComment.setAdapter(mAdapter);

    }


}

