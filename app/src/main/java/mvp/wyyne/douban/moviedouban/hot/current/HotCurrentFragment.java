package mvp.wyyne.douban.moviedouban.hot.current;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.adapter.HotAdapter;
import mvp.wyyne.douban.moviedouban.api.RvItemOnClick;
import mvp.wyyne.douban.moviedouban.api.bean.Subjects;
import mvp.wyyne.douban.moviedouban.detail.DetailMovieActivity;
import mvp.wyyne.douban.moviedouban.home.base.BaseFragment;

import static mvp.wyyne.douban.moviedouban.utils.Constans.DETAIL_TAG;

/**
 * @author XXW
 * @date 2017/6/4
 */

public class HotCurrentFragment extends BaseFragment<HotPresent> implements IHotMain, RvItemOnClick {
    public static String TAG = HotCurrentFragment.class.getSimpleName();
    @BindView(R.id.current_rv)
    RecyclerView mCurrentRv;
    private List<Subjects> mList = new ArrayList<>();
    private HotAdapter mAdapter;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("XXW", TAG + "onActivityCreated==" + getUserVisibleHint());

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hot_current;
    }

    @Override
    protected void initView() {
        mPresent = new HotPresent(this);
        mAdapter = new HotAdapter(getActivity(), mList);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        DividerItemDecoration mItemDecoration = new DividerItemDecoration(mCurrentRv.getContext(), manager.getOrientation());
        mItemDecoration.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.line_gray_horizantal));
        mAdapter.setRvOnClick(this);
        mCurrentRv.setLayoutManager(manager);
        mCurrentRv.addItemDecoration(mItemDecoration);
        mCurrentRv.setAdapter(mAdapter);

    }


    /**
     * 懒加载 只有在进入页面才获取数据
     */
    @Override
    public void update() {
        mPresent.getCurrentData();
    }


    @Override
    public void show() {
        mLodingView.show();

    }

    @Override
    public void hide() {
        mLodingView.hide();
        mSwipeRefresh.setRefreshing(false);
    }

    @Override
    public void refresh() {
        mPresent.getCurrentData();
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
        intent.putExtra(DETAIL_TAG, mList.get(position).getId());

        getActivity().startActivity(intent);
    }
}
