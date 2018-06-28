package mvp.wyyne.douban.moviedouban.movie;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.TimeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.adapter.NowTopAdapter;
import mvp.wyyne.douban.moviedouban.adapter.UsAdapter;
import mvp.wyyne.douban.moviedouban.adapter.WeeklyAdapter;
import mvp.wyyne.douban.moviedouban.api.RvItemOnClick;
import mvp.wyyne.douban.moviedouban.api.bean.Subjects;
import mvp.wyyne.douban.moviedouban.api.bean.UsSubjects;
import mvp.wyyne.douban.moviedouban.api.bean.WeeklySubject;
import mvp.wyyne.douban.moviedouban.api.model.WannaModel;
import mvp.wyyne.douban.moviedouban.api.model.WannaTable;
import mvp.wyyne.douban.moviedouban.detail.DetailMovieActivity;
import mvp.wyyne.douban.moviedouban.home.MainActivity;
import mvp.wyyne.douban.moviedouban.home.base.BaseFragment;
import mvp.wyyne.douban.moviedouban.movie.hot.NowMovieActivity;
import mvp.wyyne.douban.moviedouban.movie.top.TopActivity;
import mvp.wyyne.douban.moviedouban.movie.us.UsMovieActivity;
import mvp.wyyne.douban.moviedouban.movie.weekly.WeeklyMovieActivity;
import mvp.wyyne.douban.moviedouban.search.SearchMovieActivity;

import static mvp.wyyne.douban.moviedouban.utils.Constant.DETAIL_TAG;
import static mvp.wyyne.douban.moviedouban.utils.Constant.NOW_TAG;
import static mvp.wyyne.douban.moviedouban.utils.Constant.TOP_TAG;
import static mvp.wyyne.douban.moviedouban.utils.Constant.WANNA;
import static mvp.wyyne.douban.moviedouban.utils.Constant.WANNA_EXIST;

/**
 * @author XXW
 * @date 2017/6/2
 */

public class MovieFragment extends BaseFragment<IMoviePresent> implements IMovieMain, RvItemOnClick {
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
    private ArrayList<Subjects> mNowList;
    private ArrayList<WeeklySubject> mWeeklyList;
    private ArrayList<UsSubjects> mUsList;

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
        mTvCity.setVisibility(View.GONE);
        mPresent = new MovieFragmentImp(this);
        initList();

        LinearLayoutManager weeklyManager = new LinearLayoutManager(getActivity());
        LinearLayoutManager usManager = new LinearLayoutManager(getActivity());
        GridLayoutManager nowManager = new GridLayoutManager(getActivity(), 3);
        GridLayoutManager topManager = new GridLayoutManager(getActivity(), 3);
        nowAdapter = new NowTopAdapter(getActivity(), nowList);
        nowAdapter.setTag(NOW_TAG);
        nowAdapter.setRvOnClick(this);
        rvNow.setLayoutManager(nowManager);
        rvNow.setAdapter(nowAdapter);
        topAdapter = new NowTopAdapter(getActivity(), topList);
        topAdapter.setTag(TOP_TAG);
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


    @OnClick({R.id.dcl_search, R.id.tv_now_all, R.id.tv_us, R.id.tv_weekly, R.id.tv_top_all})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.dcl_search:
                Intent searchMovie = new Intent(getActivity(), SearchMovieActivity.class);
                searchMovie.putParcelableArrayListExtra(SearchMovieActivity.TAG, mList);
                startActivity(searchMovie);
                break;
            case R.id.tv_now_all:
                mNowList.addAll(nowList);
                Intent hotMovie = new Intent(getActivity(), NowMovieActivity.class);
                hotMovie.putParcelableArrayListExtra(NowMovieActivity.TAG, mNowList);
                startActivity(hotMovie);
                break;
            case R.id.tv_weekly:
                mWeeklyList.addAll(weeklyList);
                Intent weeklyMovie = new Intent(getActivity(), WeeklyMovieActivity.class);
                weeklyMovie.putParcelableArrayListExtra(WeeklyMovieActivity.TAG, mWeeklyList);
                startActivity(weeklyMovie);
                break;
            case R.id.tv_us:
                mUsList.addAll(usList);
                Intent usMovie = new Intent(getActivity(), UsMovieActivity.class);
                usMovie.putParcelableArrayListExtra(UsMovieActivity.TAG, mUsList);
                startActivity(usMovie);
                break;
            case R.id.tv_top_all:
                startActivity(new Intent(getActivity(), TopActivity.class));
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

        mList = ((MainActivity) getActivity()).getSubjects();
        mNowList = new ArrayList<>();
        mWeeklyList = new ArrayList<>();
        mUsList = new ArrayList<>();
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
            list = list.subList(0, 6);
        }
        usAdapter.setList(list);
        usAdapter.notifyDataSetChanged();
    }

    @Override
    public void notifyWeeklyRefresh(List<WeeklySubject> list) {
        weeklyList = list;
        if (weeklyList.size() > 6) {
            list = list.subList(0, 6);
        }
        weeklyAdapter.setList(list);
        weeklyAdapter.notifyDataSetChanged();
    }

    @Override
    public void notifyTopRefresh(List<Subjects> list) {
        topList = list;
        if (topList.size() > 6) {
            list = list.subList(0, 6);
        }
        topAdapter.setList(list);
        topAdapter.notifyDataSetChanged();
    }


    @Override
    public void onItemClick(int position, String tag) {
        switch (tag) {
            case NOW_TAG:
                Intent nowIntent = new Intent(getActivity(), DetailMovieActivity.class);
                nowIntent.putExtra(DETAIL_TAG, nowList.get(position).getId());
                startActivity(nowIntent);
                break;
            case WANNA:
                if (nowList.size() != 0) {
                    WannaTable table = new WannaTable();
                    table.setAvatarUrl(nowList.get(position).getImages().getMedium());
                    table.setAverage(nowList.get(position).getRating().getAverage() + "");
                    StringBuffer directorsBuffer = new StringBuffer();
                    StringBuffer castsBuffer = new StringBuffer();
                    for (int i = 0; i < nowList.get(position).getDirectors().size(); i++) {
                        if (i == 3) {
                            continue;
                        }
                        if (i != nowList.get(position).getDirectors().size() - 1) {
                            directorsBuffer.append(nowList.get(position).getDirectors().get(i).getName() + "/");
                        } else {
                            directorsBuffer.append(nowList.get(position).getDirectors().get(i).getName());
                        }
                    }
                    for (int i = 0; i < nowList.get(position).getCasts().size(); i++) {
                        if (i == 3) {
                            continue;
                        }
                        if (i != nowList.get(position).getCasts().size() - 1) {
                            castsBuffer.append(nowList.get(position).getCasts().get(i).getName() + "/");
                        } else {
                            castsBuffer.append(nowList.get(position).getCasts().get(i).getName());
                        }
                    }
                    table.setDirectors(directorsBuffer.toString());
                    table.setCasts(castsBuffer.toString());
                    table.setTitle(nowList.get(position).getTitle());
                    table.setCreatetime(TimeUtils.getNowString());
                    table.setId(Long.valueOf(nowList.get(position).getId()));
                    WannaModel.getInstance().insertModel(table);
                    showToast("已添加到想看列表");
                }
                break;
            case WANNA_EXIST:
                if (nowList.size() != 0) {
                    WannaModel.getInstance().deleteModel(Long.valueOf(nowList.get(position).getId()));
                    showToast("已从想看列表移除");
                }
                break;
            case TOP_TAG:
                Intent topIntent = new Intent(getActivity(), DetailMovieActivity.class);
                topIntent.putExtra(DETAIL_TAG, topList.get(position).getId());
                startActivity(topIntent);
                break;
            default:
                break;
        }
    }


}
