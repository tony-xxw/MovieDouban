package mvp.wyyne.douban.moviedouban.detail.cast;

import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.bean.CastArticle;
import mvp.wyyne.douban.moviedouban.home.base.BaseActivity;

/**
 * @author XXW
 * @date 2017/7/13
 */

public class CastDetailActivity extends BaseActivity {
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_eg_name)
    TextView mTvEgName;
    @BindView(R.id.tv_gender)
    TextView mTvGender;
    @BindView(R.id.tv_constellation)
    TextView mTvConstellation;
    @BindView(R.id.tv_birthday)
    TextView mTvBirthday;
    @BindView(R.id.tv_born)
    TextView mTvBorn;
    @BindView(R.id.tv_summary)
    TextView mTvSummary;
    private CastArticle mArticle;

    @Override
    protected void refresh() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_cast_summry;
    }

    @Override
    protected void initView() {
        if (getIntent() != null) {
            mArticle = getIntent().getParcelableExtra("article");
        }

        mTvName.setText(mArticle.getName());
        mTvEgName.setText(mArticle.getName_en());
        mTvGender.setText(mArticle.getGender());
        mTvConstellation.setText(mArticle.getConstellation());
        mTvBirthday.setText(mArticle.getBirthday());
        mTvBorn.setText(mArticle.getBorn_place());
        mTvSummary.setText(mArticle.getSummary());


    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
