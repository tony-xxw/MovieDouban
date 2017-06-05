package mvp.wyyne.douban.moviedouban.hot;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.main.BaseFragment;

/**
 * Created by XXW on 2017/6/2.
 */

public class HotFragment extends BaseFragment implements HotView {

    @BindView(R.id.hot_tl)
    TabLayout mHotTl;
    Unbinder unbinder;
    @BindView(R.id.hot_vp)
    ViewPager mHotVp;
    private View mContentView;
    private HotPresenterImp mPresenterImp;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("XXW", "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, mContentView);
        Log.d("XXW", "onCreateView");
        return mContentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("XXW", "onActivityCreated");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initView() {
        //Fragment里 嵌套Fragment的Manager要用getChildFragmentManager()
        mPresenterImp = new HotPresenterImp(this, getChildFragmentManager());
        mHotTl.setTabMode(TabLayout.MODE_FIXED);
        mHotTl.addTab(mHotTl.newTab().setText("正在热映"));
        mHotTl.addTab(mHotTl.newTab().setText("即将上映"));
        mPresenterImp.initPage(mHotVp);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        Log.d("XXW", "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("XXW", "onDestroy");
    }


    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("XXW", "onDetach");
    }

    @Override
    public void onBindPage() {
        mHotTl.setupWithViewPager(mHotVp);

    }
}
