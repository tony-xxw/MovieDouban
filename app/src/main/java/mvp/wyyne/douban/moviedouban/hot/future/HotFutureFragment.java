package mvp.wyyne.douban.moviedouban.hot.future;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.adapter.CurrentAdapter;
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

    @Override
    protected void refresh() {
        mPresent.getData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hot_future;
    }

    @Override
    protected void initView() {
        mList = new ArrayList<>();
        mMovieTypes = new ArrayList<>();

        mPresent = new FuturePresent(getActivity(), this);
        mAdapter = new FutureAdapter(getActivity(), mList);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mItemDecoration = new DividerItemDecoration(mFutureRv.getContext(), manager.getOrientation());
        mItemDecoration.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.line_gray_horizantal));
        mDecoration = new TitleRecycleItemDecoration(getActivity(), mMovieTypes);
        mFutureRv.setLayoutManager(manager);
        mFutureRv.addItemDecoration(mItemDecoration);

        mFutureRv.setAdapter(mAdapter);
        mPresent.getMovieDate();

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
//        for (int i = 0; i < subjectses.size(); i++) {
//            mMovieTypes.add(new MovieType("日期", subjectses.get(i)));
//        }
        mList = subjectses;
    }

    @Override
    public void bindData(Map<String, Subjects> s) {
        Set<Map.Entry<String, Subjects>> map = s.entrySet();
//        Iterator<Map.Entry<String, Subjects>> it = map.iterator();
//
//        for (it, it.hasNext();) {
//
//        }

        for (Iterator<Map.Entry<String, Subjects>> it = map.iterator(); it.hasNext(); ) {
            Map.Entry<String, Subjects> entry = (Map.Entry<String, Subjects>) it.next();
            System.out.println(entry.getKey() + "--->" + entry.getValue());
            mMovieTypes.add(new MovieType(entry.getKey(), entry.getValue()));
        }

        mDecoration.setData(mMovieTypes);
        mFutureRv.addItemDecoration(mDecoration);
        mAdapter.setList(mList);
        mAdapter.notifyDataSetChanged();
    }

}
