package mvp.wyyne.douban.moviedouban.comment;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import javax.annotation.Nullable;
//import androidx.appcompat.widget.LinearLayoutManager;
//import androidx.appcompat.widget.RecyclerView;

import butterknife.BindView;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.adapter.CommentAdapter;
import mvp.wyyne.douban.moviedouban.adapter.ReviewAdapter;
import mvp.wyyne.douban.moviedouban.api.bean.Article;
import mvp.wyyne.douban.moviedouban.home.base.BaseFragment;
import mvp.wyyne.douban.moviedouban.widget.RecycleViewUtils;

/**
 * @author XXW
 * @date 2017/6/22
 */

public class CommentFragment extends BaseFragment {
    public static final String TAG = "CommentFragment";
    @BindView(R.id.rv_comment)
    RecyclerView mRvComment;
    @BindView(R.id.rv_review)
    RecyclerView mRvReview;
    private Article mArticle;

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


        CommentAdapter mAdapter = new CommentAdapter(getActivity(), mArticle.getPopular_comments());
        mAdapter.setArticle(mArticle);

        mRvComment.setNestedScrollingEnabled(false);
        mRvComment.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter.setHeadView(RecycleViewUtils.addHeadView(R.layout.item_comment_head, getActivity(), mRvComment));
        mAdapter.setFooterView(RecycleViewUtils.addFooterView(R.layout.item_comment_footer, getActivity()));
        mRvComment.setAdapter(mAdapter);


        ReviewAdapter mReviewAdapter = new ReviewAdapter(getActivity(), mArticle.getPopular_reviews());
        mReviewAdapter.setArticle(mArticle);


        mRvReview.setNestedScrollingEnabled(false);
        mRvReview.setLayoutManager(new LinearLayoutManager(getActivity()));

        mReviewAdapter.setHeadView(RecycleViewUtils.addHeadView(R.layout.item_comment_head, getActivity(), mRvReview));
        mReviewAdapter.setFooterView(RecycleViewUtils.addFooterView(R.layout.item_comment_footer, getActivity()));
        mRvReview.setAdapter(mReviewAdapter);

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            mIsVisible = true;
            lazyLoad();
        } else {
            mIsVisible = false;
        }
    }
}

