package mvp.wyyne.douban.moviedouban.comment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.adapter.CommentAdapter;
import mvp.wyyne.douban.moviedouban.api.bean.Article;
import mvp.wyyne.douban.moviedouban.api.bean.PopularCm;
import mvp.wyyne.douban.moviedouban.home.BaseFragment;

/**
 * Created by XXW on 2017/6/22.
 */

public class CommentFragment extends BaseFragment {
    public static String TAG = "CommentFragment";
    @BindView(R.id.rv_comment)
    RecyclerView mRvComment;
    private CommentAdapter mAdapter;
    private List<PopularCm> mPopularCm;
    private Article mArticle;
    private LinearLayoutManager mManager;

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
        if (getArguments() != null) {
            mArticle = getArguments().getParcelable(TAG);
            Log.d("XXW", "mA--" + mArticle.getTitle());
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
        Log.d("---XXW", "initView");
        mAdapter = new CommentAdapter(getActivity(), mPopularCm);
        mManager = new LinearLayoutManager(getActivity());
        mManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvComment.setLayoutManager(mManager);
        mRvComment.setAdapter(mAdapter);

    }


}

