package mvp.wyyne.douban.moviedouban.detail.cast;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.adapter.PhotoFmAdapter;
import mvp.wyyne.douban.moviedouban.api.bean.CastArticle;
import mvp.wyyne.douban.moviedouban.api.bean.Photos;
import mvp.wyyne.douban.moviedouban.home.BaseFragment;

/**
 * Created by XXW on 2017/7/14.
 */

public class CastArticleFragment extends BaseFragment {
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

}
