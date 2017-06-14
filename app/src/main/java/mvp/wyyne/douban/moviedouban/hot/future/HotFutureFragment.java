package mvp.wyyne.douban.moviedouban.hot.future;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.adapter.CurrentAdapter;
import mvp.wyyne.douban.moviedouban.adapter.FutureAdapter;
import mvp.wyyne.douban.moviedouban.api.bean.Subjects;
import mvp.wyyne.douban.moviedouban.home.BaseFragment;

/**
 * Created by XXW on 2017/6/4.
 */

public class HotFutureFragment extends BaseFragment<FuturePresent> implements IFutureMain {
    protected static final String TAG = "HotFutureFragment";
    @BindView(R.id.future_rv)
    RecyclerView mFutureRv;
    private FutureAdapter mAdapter;
    private List<Subjects> mList;

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
        mPresent = new FuturePresent(getActivity(), this);
        mAdapter = new FutureAdapter(getActivity(), mList);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        mFutureRv.setLayoutManager(manager);
        mFutureRv.addItemDecoration(new DividerItemDecoration(mFutureRv.getContext(), manager.getOrientation()));
        mFutureRv.setAdapter(mAdapter);
        mPresent.getData();
        
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
        mAdapter.setList(subjectses);
        mAdapter.notifyDataSetChanged();
    }

}
