package mvp.wyyne.douban.moviedouban.hot.current;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.adapter.CurrentAdapter;
import mvp.wyyne.douban.moviedouban.api.RvItemOnClick;
import mvp.wyyne.douban.moviedouban.api.bean.Subjects;
import mvp.wyyne.douban.moviedouban.detail.DetailMovieActivity;
import mvp.wyyne.douban.moviedouban.home.BaseFragment;

/**
 * Created by XXW on 2017/6/4.
 */

public class HotCurrentFragment extends BaseFragment<CurrentPresent> implements ICurrentMain, RvItemOnClick {
    protected static final String TAG = "HotCurrentFragment";
    @BindView(R.id.current_rv)
    RecyclerView mCurrentRv;
    @BindView(R.id.avl_loading)
    AVLoadingIndicatorView mAvlLoading;
    private List<Subjects> mList;
    private CurrentAdapter mAdapter;
    private DividerItemDecoration mItemDecoration;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hot_current;
    }

    @Override
    protected void initView() {
        mPresent = new CurrentPresent(this);
        mList = new ArrayList<>();
        mAdapter = new CurrentAdapter(getActivity(), mList);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mItemDecoration = new DividerItemDecoration(mCurrentRv.getContext(), manager.getOrientation());
        mItemDecoration.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.line_gray_horizantal));
        mAdapter.setOnclick(this);
        mCurrentRv.setLayoutManager(manager);
        mCurrentRv.addItemDecoration(mItemDecoration);
        mCurrentRv.setAdapter(mAdapter);
        mPresent.getData();

    }


    @Override
    public void show() {
        mAvlLoading.show();

    }

    @Override
    public void hide() {
        mAvlLoading.hide();
        mSwipeRefresh.setRefreshing(false);
    }

    @Override
    public void refresh() {
        mPresent.getData();
    }

    @Override
    public void initData(List<Subjects> subjects) {
        mList = subjects;
        mAdapter.setList(subjects);
        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void onItemClick(int position, String tag) {
        Intent intent = new Intent(getActivity(), DetailMovieActivity.class);
        intent.putExtra(DetailMovieActivity.DETAIL_TAG, mList.get(position).getId());
        Log.d("XXW", "------->" + mList.get(position).getId());
        getActivity().startActivity(intent);
    }
}
