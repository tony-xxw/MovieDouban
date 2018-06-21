package mvp.wyyne.douban.moviedouban.welfare;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.adapter.WelfareAdapter;
import mvp.wyyne.douban.moviedouban.api.RvItemLongOnClick;
import mvp.wyyne.douban.moviedouban.api.RvItemOnClick;
import mvp.wyyne.douban.moviedouban.api.bean.WelfarePhotoInfo;
import mvp.wyyne.douban.moviedouban.home.base.BaseFragment;

import static mvp.wyyne.douban.moviedouban.adapter.WelfareAdapter.STAGGERED;


/**
 * @author XXW
 * @date 2017/6/2
 */

public class WelfareFragment extends BaseFragment<WelfarePresent> implements IWelfareMain, RvItemOnClick, RvItemLongOnClick, View.OnClickListener {
    public static final String TAG = WelfareFragment.class.getSimpleName();
    @BindView(R.id.rv_welfare)
    RecyclerView mRvWelfare;
    private WelfareAdapter mAdapter;
    private List<WelfarePhotoInfo> mList;
    private StaggeredGridLayoutManager mStaggerManager;
    private LinearLayoutManager mLlManager;
    private GridLayoutManager mGlManager;
    private AlertDialog alertDialog;

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
        mPresent = new WelfarePresent(this, getContext());
        mList = new ArrayList<>();
        mAdapter = new WelfareAdapter(getActivity(), mList);
        mAdapter.setRvOnClick(this);
        mAdapter.setRvLongOnClick(this);
        setGridsManager();
        initDialog();
        mPresent.getData();


    }


    private void initDialog() {
        View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_welfare_manager, null);
        dialogView.findViewById(R.id.tv_grids).setOnClickListener(this);
        dialogView.findViewById(R.id.tv_linear).setOnClickListener(this);
        dialogView.findViewById(R.id.tv_staggered).setOnClickListener(this);

        alertDialog = new AlertDialog.Builder(getActivity())
                .setView(dialogView)
                .create();
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


    @Override
    public void onItemClick(int position, String tag) {
        Intent intent = new Intent(getActivity(), WelfarePhotoActivity.class);
        String url = mList.get(position).getUrl();
        intent.putExtra(WelfarePhotoActivity.TAG, url);
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(int position, String tag) {
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_grids:
                setGridsManager();
                alertDialog.dismiss();
                break;
            case R.id.tv_linear:
                setLinearManager();
                alertDialog.dismiss();
                break;
            case R.id.tv_staggered:
                setStaggeredManager();
                alertDialog.dismiss();
                break;

            default:
                break;
        }
    }

    private void setGridsManager() {
        mGlManager = new GridLayoutManager(getActivity(), 2);
        mRvWelfare.setLayoutManager(mGlManager);
        mAdapter.setLayoutManager(WelfareAdapter.GRIDS);
        mRvWelfare.setAdapter(mAdapter);
    }

    private void setLinearManager() {
        mLlManager = new LinearLayoutManager(getActivity());
        mRvWelfare.setLayoutManager(mLlManager);
        mAdapter.setLayoutManager(WelfareAdapter.LINEAR);
        mRvWelfare.setAdapter(mAdapter);
    }

    public void setStaggeredManager() {
        mStaggerManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mAdapter.setLayoutManager(STAGGERED);
        mStaggerManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRvWelfare.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                mStaggerManager.invalidateSpanAssignments();
            }
        });
        mRvWelfare.setLayoutManager(mStaggerManager);
        mRvWelfare.setAdapter(mAdapter);
    }
}
