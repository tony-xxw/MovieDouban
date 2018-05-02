package mvp.wyyne.douban.moviedouban.welfare;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.adapter.WelfareAdapter;
import mvp.wyyne.douban.moviedouban.api.bean.WelfarePhotoInfo;
import mvp.wyyne.douban.moviedouban.home.base.BaseFragment;
import mvp.wyyne.douban.moviedouban.utils.StatusUtils;

/**
 * @author XXW
 * @date 2017/6/2
 */

public class WelfareFragment extends BaseFragment<IWelfarePresent> implements IWelfareMain {

    public static final String TAG = WelfareFragment.class.getSimpleName();
    @BindView(R.id.rv_welfare)
    RecyclerView mRvWelfare;
    private WelfareAdapter mAdapter;
    private List<WelfarePhotoInfo> mList;
    private StaggeredGridLayoutManager mManager;


    public static WelfareFragment getInstance() {
        return new WelfareFragment();
    }

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
        mManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRvWelfare.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                mManager.invalidateSpanAssignments();
            }
        });
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
