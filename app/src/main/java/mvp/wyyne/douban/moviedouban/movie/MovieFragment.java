package mvp.wyyne.douban.moviedouban.movie;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.adapter.NowTopAdapter;
import mvp.wyyne.douban.moviedouban.adapter.UsAdapter;
import mvp.wyyne.douban.moviedouban.adapter.WeeklyAdapter;
import mvp.wyyne.douban.moviedouban.api.bean.Subjects;
import mvp.wyyne.douban.moviedouban.api.bean.UsSubjects;
import mvp.wyyne.douban.moviedouban.api.bean.WeeklySubject;
import mvp.wyyne.douban.moviedouban.home.MainActivity;
import mvp.wyyne.douban.moviedouban.home.base.BaseFragment;
import mvp.wyyne.douban.moviedouban.movie.hot.HotMovieActivity;
import mvp.wyyne.douban.moviedouban.search.SearchMovieActivity;

/**
 * @author XXW
 * @date 2017/6/2
 */

public class MovieFragment extends BaseFragment<IMoviePresent> implements IMovieMain {
    public static final String TAG = MovieFragment.class.getSimpleName();
    @BindView(R.id.tv_city)
    TextView mTvCity;
    @BindView(R.id.ll_movie)
    LinearLayout mLlMovie;
    @BindView(R.id.tv_now_all)
    TextView tvNowAll;
    @BindView(R.id.rv_now)
    RecyclerView rvNow;
    @BindView(R.id.rv_weekly)
    RecyclerView rvWeekly;
    @BindView(R.id.tv_top_all)
    TextView tvTopAll;
    @BindView(R.id.rv_top)
    RecyclerView rvTop;
    @BindView(R.id.rv_us)
    RecyclerView rvUs;
    private NowTopAdapter nowAdapter;
    private NowTopAdapter topAdapter;
    private WeeklyAdapter weeklyAdapter;
    private UsAdapter usAdapter;
    private List<Subjects> nowList;
    private List<Subjects> topList;
    private List<WeeklySubject> weeklyList;
    private List<UsSubjects> usList;
    private ArrayList<Subjects> mList;

    public static MovieFragment getInstance() {
        return new MovieFragment();
    }

    @Override
    protected void refresh() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_movie;
    }

    @Override
    protected void initView() {

        mList = ((MainActivity) getActivity()).getSubjects();
        mTvCity.setVisibility(View.GONE);
        mPresent = new MovieFragmentImp(this);


        initList();

        LinearLayoutManager weeklyManager = new LinearLayoutManager(getActivity());
        LinearLayoutManager usManager = new LinearLayoutManager(getActivity());
        GridLayoutManager nowManager = new GridLayoutManager(getActivity(), 3);
        GridLayoutManager topManager = new GridLayoutManager(getActivity(), 3);
        nowAdapter = new NowTopAdapter(getActivity(), nowList);
        rvNow.setLayoutManager(nowManager);
        rvNow.setAdapter(nowAdapter);
        topAdapter = new NowTopAdapter(getActivity(), topList);
        rvTop.setLayoutManager(topManager);
        rvTop.setAdapter(topAdapter);
        weeklyAdapter = new WeeklyAdapter(getActivity(), weeklyList);
        rvWeekly.setLayoutManager(weeklyManager);
        rvWeekly.setAdapter(weeklyAdapter);
        usAdapter = new UsAdapter(getActivity(), usList);
        rvUs.setLayoutManager(usManager);
        rvUs.setAdapter(usAdapter);


        handleData();
    }


    @OnClick({R.id.dcl_search, R.id.tv_now_all})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.dcl_search:
                Intent searchMovie = new Intent(getActivity(), SearchMovieActivity.class);
                searchMovie.putParcelableArrayListExtra(SearchMovieActivity.TAG, mList);
                startActivity(searchMovie);
                break;
            case R.id.tv_now_all:
                Intent hotMovie = new Intent(getActivity(), HotMovieActivity.class);
                startActivity(hotMovie);
                break;
            default:
                break;
        }
    }

    @Override
    public void show() {
        mLoadingView.show();

    }

    @Override
    public void hide() {
        mLlMovie.setVisibility(View.VISIBLE);
        mLoadingView.hide();
    }


    private void handleData() {
        mPresent.getNowMovies();
        mPresent.getTopMovie();
        mPresent.getUsBox();
        mPresent.getWeekly();
    }

    private void initList() {
        nowList = new ArrayList<>();
        weeklyList = new ArrayList<>();
        topList = new ArrayList<>();
        usList = new ArrayList<>();
    }


    @Override
    public void notifyNowRefresh(List<Subjects> list) {
        nowList = list;
        String all = "全部" + nowList.size() + "+";
        tvNowAll.setText(all);
        if (list.size() > 6) {
            list = list.subList(0, 6);
        }
        nowAdapter.setList(list);
        nowAdapter.notifyDataSetChanged();
    }

    @Override
    public void notifyUsRefresh(List<UsSubjects> list) {
        usList = list;
        if (usList.size() > 6) {
            usList = usList.subList(0, 6);
        }
        usAdapter.setList(usList);
        usAdapter.notifyDataSetChanged();
    }

    @Override
    public void notifyWeeklyRefresh(List<WeeklySubject> list) {
        weeklyList = list;
        if (weeklyList.size() > 6) {
            weeklyList = weeklyList.subList(0, 6);
        }
        weeklyAdapter.setList(weeklyList);
        weeklyAdapter.notifyDataSetChanged();
    }

    @Override
    public void notifyTopRefresh(List<Subjects> list) {
        topList = list;
        if (topList.size() > 6) {
            topList = topList.subList(0, 6);
        }
        topAdapter.setList(topList);
        topAdapter.notifyDataSetChanged();
    }


}
