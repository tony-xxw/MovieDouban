package mvp.wyyne.douban.moviedouban.detail.cast;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;


import butterknife.BindView;
import butterknife.OnClick;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.adapter.ProducationAdapter;
import mvp.wyyne.douban.moviedouban.api.RvItemOnClick;
import mvp.wyyne.douban.moviedouban.api.bean.CastArticle;
import mvp.wyyne.douban.moviedouban.api.bean.Subjects;
import mvp.wyyne.douban.moviedouban.detail.DetailMovieActivity;
import mvp.wyyne.douban.moviedouban.home.BaseFragment;

/**
 * Created by XXW on 2017/7/14.
 */

public class CastArticleFragment extends BaseFragment implements RvItemOnClick {
    public static final String TAG = "castArticleFragment.class";
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_title_en)
    TextView mTvTitleEn;
    @BindView(R.id.tv_summary)
    TextView mTvSummary;
    @BindView(R.id.rv_production)
    RecyclerView mRvProduction;
    private CastArticle mArticle;
    private ProducationAdapter mAdapter;
    private LinearLayoutManager mManager;


    public static CastArticleFragment getInstance(CastArticle castArticle) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(TAG, castArticle);
        CastArticleFragment fragment = new CastArticleFragment();
        fragment.setArguments(bundle);

        return fragment;
    }


    @Override
    protected void refresh() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cast_content;
    }

    @Override
    protected void initView() {
        if (getArguments() != null) {
            mArticle = getArguments().getParcelable(TAG);
        }

        mAdapter = new ProducationAdapter(getActivity(), mArticle.getWorks());
        mAdapter.setRvOnClick(this);
        mManager = new LinearLayoutManager(getActivity());
        mManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvProduction.setLayoutManager(mManager);
        mRvProduction.setAdapter(mAdapter);

        mTvTitle.setText(mArticle.getName());
        mTvTitleEn.setText(mArticle.getName_en());
        mTvSummary.setText(mArticle.getSummary().trim());
    }

    @OnClick(R.id.tv_summary)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(), CastDetailActivity.class);
        intent.putExtra("article", mArticle);
        startActivity(intent);
    }

    @Override
    public void onItemClick(int position, String tag) {
        Subjects subjects = mArticle.getWorks().get(position).getSubject();
        Log.d("XXW", "ID===" + subjects.getId());
        Intent intent = new Intent(getActivity(), DetailMovieActivity.class);
        intent.putExtra(DetailMovieActivity.DETAIL_TAG, subjects.getId());
        getActivity().startActivity(intent);
    }
}
