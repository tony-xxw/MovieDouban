package mvp.wyyne.douban.moviedouban.detail.cast;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import mvp.wyyne.douban.moviedouban.AndroidApplication;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.adapter.ProductionAdapter;
import mvp.wyyne.douban.moviedouban.api.RvItemOnClick;
import mvp.wyyne.douban.moviedouban.api.bean.ActorInfo;
import mvp.wyyne.douban.moviedouban.api.bean.Subjects;
import mvp.wyyne.douban.moviedouban.api.model.ActorCollectTable;
import mvp.wyyne.douban.moviedouban.detail.DetailMovieActivity;
import mvp.wyyne.douban.moviedouban.home.base.BaseFragment;
import mvp.wyyne.douban.moviedouban.login.LoginActivity;

import static mvp.wyyne.douban.moviedouban.utils.Constant.DETAIL_TAG;

/**
 * @author XXW
 * @date 2017/7/14
 */

public class ActorDetailFragment extends BaseFragment implements RvItemOnClick {
    public static final String TAG = "castArticleFragment.class";
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_title_en)
    TextView mTvTitleEn;
    @BindView(R.id.tv_summary)
    TextView mTvSummary;
    @BindView(R.id.rv_production)
    RecyclerView mRvProduction;
    @BindView(R.id.tv_collect)
    TextView tvCollect;
    private ActorInfo mArticle;
    private String actorAvatarUrl;
    private String actorName;

    public static ActorDetailFragment getInstance(ActorInfo castArticle) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(TAG, castArticle);
        ActorDetailFragment fragment = new ActorDetailFragment();
        fragment.setArguments(bundle);

        return fragment;
    }


    @Override
    protected void refresh() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cast_detail;
    }

    @Override
    protected void initView() {
        if (getArguments() != null) {
            mArticle = getArguments().getParcelable(TAG);
        }

        ProductionAdapter mAdapter = new ProductionAdapter(getActivity(), mArticle.getWorks());
        mAdapter.setRvOnClick(this);
        LinearLayoutManager mManager = new LinearLayoutManager(getActivity());
        mManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvProduction.setLayoutManager(mManager);
        mRvProduction.setAdapter(mAdapter);

        mTvTitle.setText(mArticle.getName());
        mTvTitleEn.setText(mArticle.getName_en());
        mTvSummary.setText(mArticle.getSummary().trim());

        if (ActorModel.getInstance().queryModelList().size() != 0) {
            setDrawableLeft();
        }
    }

    @OnClick({R.id.tv_summary, R.id.cv_collect})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_summary:
                //演员资料
                Intent intent = new Intent(getActivity(), ActorDetailActivity.class);
                intent.putExtra("article", mArticle);
                startActivity(intent);
            case R.id.cv_collect:
                //收藏
                if (!AndroidApplication.getApplication().Login()) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                } else {
                    setDrawableLeft();
                    insertActor();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(int position, String tag) {
        Subjects subjects = mArticle.getWorks().get(position).getSubject();
        Intent intent = new Intent(getActivity(), DetailMovieActivity.class);
        intent.putExtra(DETAIL_TAG, subjects.getId());
        getActivity().startActivity(intent);
    }


    public void insertActor() {
        StringBuffer buffer = new StringBuffer();
        if (mArticle.getWorks().size() != 0) {
            for (int i = 0; i < mArticle.getWorks().size(); i++) {
                if (i == 3) {
                    break;
                }
                buffer = buffer.append(mArticle.getWorks().get(i));
            }
        }
        actorAvatarUrl = mArticle.getAvatars().getLarge();
        actorName = mArticle.getName();
        ActorCollectTable table = new ActorCollectTable();
        table.setActorName(actorName);
        table.setAvatarUrl(actorAvatarUrl);
        table.setRepresentative(buffer.toString());
        ActorModel.getInstance().insertModel(table);

    }


    public void setDrawableLeft() {
        Drawable drawableLeft = getResources().getDrawable(R.drawable.ic_check_gray_small);
        drawableLeft.setBounds(0, 0, drawableLeft.getMinimumWidth(), drawableLeft.getMinimumHeight());
        tvCollect.setCompoundDrawables(drawableLeft, null, null, null);
    }

}
