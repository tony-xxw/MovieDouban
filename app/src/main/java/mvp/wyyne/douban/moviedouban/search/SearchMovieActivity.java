package mvp.wyyne.douban.moviedouban.search;


import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.adapter.SearchAdapter;
import mvp.wyyne.douban.moviedouban.adapter.SearchHotAdapter;
import mvp.wyyne.douban.moviedouban.api.RvItemOnClick;
import mvp.wyyne.douban.moviedouban.api.bean.Subjects;
import mvp.wyyne.douban.moviedouban.detail.DetailMovieActivity;
import mvp.wyyne.douban.moviedouban.home.base.BaseActivity;
import mvp.wyyne.douban.moviedouban.utils.StatusUtils;

import static mvp.wyyne.douban.moviedouban.utils.Constant.DETAIL_TAG;

/**
 * 搜索电影页面
 *
 * @author XXW
 * @date 2017/6/3
 */

public class SearchMovieActivity extends BaseActivity<SearchMovieImp> implements
        ISearchMovieMain, RvItemOnClick, TextWatcher {
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
    @BindView(R.id.ll_parent)
    LinearLayout llParent;
    @BindView(R.id.rv_result)
    RecyclerView rvResult;
    @BindView(R.id.ll_result)
    LinearLayout llResult;
    private List<Subjects> mResultList;
    private SearchAdapter mResultAdapter;

    @Override
    protected void refresh() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_movie;
    }

    @Override
    protected void onResume() {
        super.onResume();
        StatusUtils.setStatusBarActivity(this, false, getResources().getColor(R.color.color_green));
        llSearch.setVisibility(View.GONE);
        llSearchMain.setVisibility(View.VISIBLE);
        flSearch.setBackgroundColor(getResources().getColor(R.color.color_green));
    }

    @Override
    protected void initView() {
        mPresent = new SearchMovieImp(this, this);
        mResultList = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        DividerItemDecoration mItemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());
        mItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.line_gray_horizantal));
        rvHotSearch.setLayoutManager(layoutManager);
        rvHotSearch.addItemDecoration(mItemDecoration);
        SearchHotAdapter mSearchAdapter = new SearchHotAdapter(this, mPresent.getSubjectsList());
        mSearchAdapter.setRvOnClick(this);
        rvHotSearch.setAdapter(mSearchAdapter);
        dclSearchMain.addTextChangedListener(this);


        //搜索结果初始化
        mResultAdapter = new SearchAdapter(this, mResultList);
        mResultAdapter.setRvOnClick(this);
        LinearLayoutManager resultManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvResult.setLayoutManager(resultManager);
        rvResult.addItemDecoration(mItemDecoration);
        rvResult.setAdapter(mResultAdapter);
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
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void onItemClick(int position, String tag) {
        Intent intent = new Intent(this, DetailMovieActivity.class);
        intent.putExtra(DETAIL_TAG, mPresent.getSubjectsList().get(position).getSubjects().getId());
        startActivity(intent);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.length() == 0) {
            llParent.setVisibility(View.VISIBLE);
            llResult.setVisibility(View.GONE);

        } else {
            llParent.setVisibility(View.GONE);
            llResult.setVisibility(View.VISIBLE);
        }

        mPresent.searchMovieSubject(s.toString(), "1", "10");
    }

    @Override
    public void afterTextChanged(Editable s) {
    }

    @Override
    public void notifyResultRefresh(List<Subjects> list) {
        Log.d("XXW", "notifyResultRefresh size : " + list.size());
        mResultAdapter.setList(list);
        mResultAdapter.notifyDataSetChanged();
    }

    @Override
    public void setSubject(List<Subjects> list) {
        mResultList = list;
    }


    @Override
    public List<Subjects> getSubject() {
        return mResultList;
    }
}
