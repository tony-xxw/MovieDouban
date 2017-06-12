package mvp.wyyne.douban.moviedouban.hot.current;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.adapter.CurrentAdapter;
import mvp.wyyne.douban.moviedouban.api.RetrofitService;
import mvp.wyyne.douban.moviedouban.api.bean.HotBean;
import mvp.wyyne.douban.moviedouban.api.bean.Subjects;
import mvp.wyyne.douban.moviedouban.home.BaseFragment;

/**
 * Created by XXW on 2017/6/4.
 */

public class HotCurrentFragment extends BaseFragment implements ICurrentMain {
    protected static final String TAG = "HotCurrentFragment";
    @BindView(R.id.current_rv)
    RecyclerView mCurrentRv;
    private List<Subjects> mList;
    private CurrentPresent mPresent;
    private CurrentAdapter mAdapter;

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

        mCurrentRv.setLayoutManager(manager);
        mCurrentRv.addItemDecoration(new DividerItemDecoration(mCurrentRv.getContext(), manager.getOrientation()));
        mCurrentRv.setAdapter(mAdapter);
        mPresent.getData();

    }


    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void refresh() {

    }

    @Override
    public void initData(List<Subjects> subjects) {
        mAdapter.setList(subjects);
        mAdapter.notifyDataSetChanged();
    }
}
