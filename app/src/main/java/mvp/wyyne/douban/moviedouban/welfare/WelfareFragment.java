package mvp.wyyne.douban.moviedouban.welfare;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.adapter.WelfareAdapter;
import mvp.wyyne.douban.moviedouban.api.bean.WelfarePhotoInfo;
import mvp.wyyne.douban.moviedouban.home.BaseFragment;

/**
 * Created by XXW on 2017/6/2.
 */

public class WelfareFragment extends BaseFragment<IWelfarePresent> implements IWelfareMain {


    @BindView(R.id.rv_welfare)
    RecyclerView mRvWelfare;
    private WelfareAdapter mAdapter;
    private List<WelfarePhotoInfo> mList;
    private StaggeredGridLayoutManager mManager;

    @Override
    protected void refresh() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_welfare;
    }

    @Override
    protected void initView() {
        mPresent = new WelfarePresent(this);
        mList = new ArrayList<>();
        mAdapter = new WelfareAdapter(getActivity(), mList);
        mManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        mRvWelfare.setLayoutManager(mManager);
        mRvWelfare.setAdapter(mAdapter);
        mPresent.getData();

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void showImg(List<WelfarePhotoInfo> list) {
        mList = list;
        mAdapter.setList(mList);
        mAdapter.notifyDataSetChanged();
    }
}
