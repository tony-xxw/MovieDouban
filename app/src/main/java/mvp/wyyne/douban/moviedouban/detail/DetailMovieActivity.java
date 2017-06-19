package mvp.wyyne.douban.moviedouban.detail;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import butterknife.BindView;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.bean.Subjects;
import mvp.wyyne.douban.moviedouban.home.BaseActivity;

/**
 * Created by XXW on 2017/6/18.
 */

public class DetailMovieActivity extends BaseActivity implements IDetailMain {
    public static final String DETAIL_TAG = "detail";
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tl_title)
    Toolbar mToolbar;
    private Subjects mSubjects;

    @Override
    protected void refresh() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().getParcelableExtra(DETAIL_TAG) != null) {
            mSubjects = getIntent().getParcelableExtra(DETAIL_TAG);
            Log.d("XXW", "mList------->" + mSubjects.getTitle());
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_movie_detail;
    }

    @Override
    protected void initView() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mTvTitle.setText(mSubjects.getTitle());
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }
}
