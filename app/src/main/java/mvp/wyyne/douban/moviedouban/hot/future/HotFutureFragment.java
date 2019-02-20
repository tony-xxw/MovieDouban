package mvp.wyyne.douban.moviedouban.hot.future;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.adapter.HotAdapter;
import mvp.wyyne.douban.moviedouban.api.RvItemOnClick;
import mvp.wyyne.douban.moviedouban.api.bean.Subjects;
import mvp.wyyne.douban.moviedouban.detail.DetailMovieActivity;
import mvp.wyyne.douban.moviedouban.home.base.BaseFragment;
import mvp.wyyne.douban.moviedouban.hot.current.HotPresent;
import mvp.wyyne.douban.moviedouban.hot.current.IHotMain;
import mvp.wyyne.douban.moviedouban.utils.MovieType;
import mvp.wyyne.douban.moviedouban.widget.TitleRecycleItemDecoration;

import static com.wynne.common.Constant.DETAIL_TAG;

/**
 * 即将上映
 *
 * @author XXW
 * @date 2017/6/4
 */

public class HotFutureFragment extends BaseFragment<HotPresent> implements IHotMain, RvItemOnClick {
    public static final String TAG = HotFutureFragment.class.getSimpleName();
    @BindView(R.id.future_rv)
    RecyclerView mFutureRv;
    private HotAdapter mAdapter;
    private List<Subjects> mList;
    private List<MovieType> mMovieTypes;
    private TitleRecycleItemDecoration mDecoration;
    private String[] mTags = {"6月15日,星期四", "6月15日,星期四", "6月15日,星期四", "6月15日,星期四",
            "6月17日,星期六", "6月17日,星期六", "6月17日,星期六", "6月17日,星期六",
            "6月19日,星期一", "6月19日,星期一", "6月19日,星期一", "6月19日,星期一",
            "6月22日,星期四", "6月22日,星期四", "6月22日,星期四", "6月22日,星期四",
            "6月24日,星期六", "6月24日,星期六", "6月24日,星期六", "6月24日,星期六"
    };


    private List<String> mTag;


    @Override
    protected void refresh() {
        mPresent.getFutureData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hot_future;
    }

    @Override
    protected void initView() {
        mList = new ArrayList<>();
        mMovieTypes = new ArrayList<>();
        mTag = Arrays.asList(mTags);
        mPresent = new HotPresent(this);
        mAdapter = new HotAdapter(getActivity(), mList);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        DividerItemDecoration mItemDecoration = new DividerItemDecoration(mFutureRv.getContext(), manager.getOrientation());
        mItemDecoration.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.line_gray_horizantal));

        mFutureRv.setLayoutManager(manager);
        mFutureRv.addItemDecoration(mItemDecoration);
        mFutureRv.setAdapter(mAdapter);
        mAdapter.setRvOnClick(this);

    }

    @Override
    public void update() {
        mPresent.getFutureData();
    }


    @Override
    public void show() {
        mLoadingView.show();
    }

    @Override
    public void hide() {
        mLoadingView.hide();
        mSwipeRefresh.setRefreshing(false);
    }

    @Override
    public void initData(List<Subjects> subjects) {
        for (int i = 0; i < subjects.size(); i++) {
            mMovieTypes.add(new MovieType(mTag.get(i), subjects.get(i)));
        }
        mList = subjects;
        mDecoration = new TitleRecycleItemDecoration(getActivity(), mMovieTypes);
        mFutureRv.addItemDecoration(mDecoration);
        mAdapter.setList(subjects);
        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void onItemClick(int position, String tag) {
        Intent intent = new Intent(getActivity(), DetailMovieActivity.class);
        intent.putExtra(DETAIL_TAG, mList.get(position).getId());
        getActivity().startActivity(intent);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            mIsVisible = true;
            lazyLoad();
        } else {
            mIsVisible = false;
        }
    }

}
