package mvp.wyyne.douban.moviedouban.search;


import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.bean.Subjects;
import mvp.wyyne.douban.moviedouban.home.base.BaseActivity;
import mvp.wyyne.douban.moviedouban.utils.StatusUtils;

/**
 * 搜索电影页面
 *
 * @author XXW
 * @date 2017/6/3
 */

public class SearchMovieActivity extends BaseActivity {
    public static final String TAG = SearchMovieActivity.class.getSimpleName();
    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    @BindView(R.id.ll_search_main)
    LinearLayout llSearchMain;
    @BindView(R.id.fl_search)
    FrameLayout flSearch;
    @BindView(R.id.dcl_search_main)
    EditText dclSearchMain;
    @BindView(R.id.tv_clear)
    TextView tvClear;
    @BindView(R.id.rv_history)
    RecyclerView rvHistory;
    @BindView(R.id.ll_history)
    LinearLayout llHistory;
    @BindView(R.id.rv_hot_search)
    RecyclerView rvHotSearch;
    private List<Subjects> mList;


    @Override
    protected void refresh() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_movie;
    }

    @Override
    protected void initView() {
        if (getIntent().getParcelableArrayListExtra(TAG) != null) {
            mList = getIntent().getParcelableArrayListExtra(TAG);
            mList = mList.subList(0, 10);
        }
        StatusUtils.setStatusBarActivity(this, false, getResources().getColor(R.color.color_green));
        llSearch.setVisibility(View.GONE);
        llSearchMain.setVisibility(View.VISIBLE);
        flSearch.setBackgroundColor(getResources().getColor(R.color.color_green));
    }


    @OnClick(R.id.tv_cancel)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
