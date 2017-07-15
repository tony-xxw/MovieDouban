package mvp.wyyne.douban.moviedouban.hot.future;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.adapter.FutureAdapter;
import mvp.wyyne.douban.moviedouban.api.bean.Subjects;
import mvp.wyyne.douban.moviedouban.home.BaseFragment;
import mvp.wyyne.douban.moviedouban.utils.MovieType;
import mvp.wyyne.douban.moviedouban.utils.TitleRecycleItemDecoration;

/**
 * Created by XXW on 2017/6/4.
 */

public class HotFutureFragment extends BaseFragment<FuturePresent> implements IFutureMain {
    protected static final String TAG = "HotFutureFragment";
    @BindView(R.id.future_rv)
    RecyclerView mFutureRv;
    private FutureAdapter mAdapter;
    private List<Subjects> mList;
    private DividerItemDecoration mItemDecoration;
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
        mSwipeRefresh.setRefreshing(false);
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
        mPresent = new FuturePresent(getActivity(), this);
        mAdapter = new FutureAdapter(getActivity(), mList);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mItemDecoration = new DividerItemDecoration(mFutureRv.getContext(), manager.getOrientation());
        mItemDecoration.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.line_gray_horizantal));

        mFutureRv.setLayoutManager(manager);
        initHeadDecoration();
        mFutureRv.addItemDecoration(mItemDecoration);
        mFutureRv.setAdapter(mAdapter);
        mPresent.getData();

    }

    private void initHeadDecoration() {

    }


    @Override
    public void show() {

    }

    @Override
    public void hide() {
        mSwipeRefresh.setRefreshing(false);
    }

    @Override
    public void initData(List<Subjects> subjectses) {
        for (int i = 0; i < subjectses.size(); i++) {
            mMovieTypes.add(new MovieType(mTag.get(i), subjectses.get(i)));

        }
        mDecoration = new TitleRecycleItemDecoration(getActivity(), mMovieTypes);
        mFutureRv.addItemDecoration(mDecoration);
        mAdapter.setList(subjectses);
        mAdapter.notifyDataSetChanged();
    }


}
