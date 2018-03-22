package mvp.wyyne.douban.moviedouban.home;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.utils.SwipeRefreshUtils;

/**
 * 基类Fragmetn
 *
 * @author XXW
 * @date 2017/6/2
 */

public abstract class BaseFragment<T extends IPresent> extends Fragment {

    //bindview

    @Nullable
    @BindView(R.id.avl_loading)
    protected AVLoadingIndicatorView mLodingView;

    @Nullable
    @BindView(R.id.swipe_refresh)
    protected SwipeRefreshLayout mSwipeRefresh;
    //缓存Fragment
    protected View mRootView;
    protected boolean mIsMulti = false;

    protected T mPresent;

    protected String TAG;

    protected Activity mContentActivity;

    protected Object object = new Object();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContentActivity = (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutId(), null);
            ButterKnife.bind(this, mRootView);
            initView();
            initSwipeRefresh();
        }
        Log.d("XXW", "object==" + object.toString());
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getUserVisibleHint() && mRootView != null) {
            mIsMulti = true;
            update();
        }
    }


    /**
     * 只有在多个Fragment实例才会执行此方法
     * 如果isVisibleToUser 为true表示当前用户可见的Fragment,getUserVisible也为true 就可以去执行更新的数据,实现赖加载
     * <p>
     * 用户后面懒加载的页面 可以延迟加载
     * 因为ViewPager 提前已经创建Fragment 只是没有加载数据
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser && isVisible() && mRootView != null && !mIsMulti) {
            mIsMulti = true;
            update();
        } else {
            super.setUserVisibleHint(isVisibleToUser);
        }
    }

    /**
     * 初始化下拉刷新
     */
    private void initSwipeRefresh() {
        if (mSwipeRefresh != null) {
            SwipeRefreshUtils.init(mSwipeRefresh, new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    refresh();
                }
            });
        }
    }


    protected abstract void refresh();

    protected abstract int getLayoutId();

    protected abstract void initView();

    /**
     * 更新数据
     *
     * @param
     */
    protected void update() {

    }


}
