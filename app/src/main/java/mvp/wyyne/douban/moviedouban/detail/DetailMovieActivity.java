package mvp.wyyne.douban.moviedouban.detail;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.bean.Subjects;
import mvp.wyyne.douban.moviedouban.home.BaseActivity;

/**
 * Created by XXW on 2017/6/18.
 */

public class DetailMovieActivity extends BaseActivity {
    public static final String DETAIL_TAG = "detail";
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

    }
}
