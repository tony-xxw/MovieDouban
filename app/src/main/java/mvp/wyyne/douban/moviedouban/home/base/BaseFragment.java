package mvp.wyyne.douban.moviedouban.home.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.wang.avi.AVLoadingIndicatorView;

import javax.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import mvp.wyyne.douban.moviedouban.AndroidApplication;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.home.IPresent;
import mvp.wyyne.douban.moviedouban.utils.SwipeRefreshUtils;
import mvp.wyyne.douban.moviedouban.utils.ToastUtils;

/**
 * 基类Fragment
 *
 * @author XXW
 * @date 2017/6/2
 */

public abstract class BaseFragment<T extends IPresent> extends Fragment {
    @Nullable
    @BindView(R.id.avl_loading)
    protected AVLoadingIndicatorView mLoadingView;
    @Nullable
    @BindView(R.id.swipe_refresh)
    protected SwipeRefreshLayout mSwipeRefresh;
    @Nullable
    @BindView(R.id.iv_null_view)
    protected ImageView ivNullView;
    /**
     * 缓存Fragment
     **/
    protected View mRootView;
    /**
     * View初始化完毕
     */
    protected boolean mIsMulti = false;

    protected boolean mIsVisible = false;

    protected T mPresent;

    private Unbinder unbinder;


    protected Activity mContentActivity;


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
            unbinder = ButterKnife.bind(this, mRootView);
            initSwipeRefresh();
        }
        return mRootView;

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mIsMulti = true;
        if (getUserVisibleHint()) {
            mIsVisible = true;
            lazyLoad();
        } else {
            mIsVisible = false;
        }
    }


    public void lazyLoad() {
        if (mIsMulti && mIsVisible) {
            initView();
            update();

            mIsMulti = false;
            mIsVisible = false;
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        isNullView();
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


    /**
     * 设置布局ID
     *
     * @return 资源id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化Views
     */
    protected abstract void initView();

    /**
     * 更新数据
     */
    protected void update() {
    }

    /**
     * 刷新数据
     */
    protected void refresh() {
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    public void isNullView() {
        if (ivNullView != null) {
            //未登录
            if (!AndroidApplication.getApplication().isLogin()) {
                ivNullView.setVisibility(View.VISIBLE);
            } else {
                ivNullView.setVisibility(View.GONE);
            }
        }
    }

    public void showToast(String msg) {
        View inflater = View.inflate(getActivity(), R.layout.toast_login, null);
        TextView textView = (TextView) inflater.findViewById(R.id.tv_text);
        textView.setText(msg);
        LinearLayout linearLayout = (LinearLayout) inflater.findViewById(R.id.ll_shape);
        linearLayout.setBackground(getResources().getDrawable(R.drawable.bg_green));
        ToastUtils.getInstance(getActivity()).makeToastSelfViewAnim(inflater, R.style.ToastStyle);

    }

}
