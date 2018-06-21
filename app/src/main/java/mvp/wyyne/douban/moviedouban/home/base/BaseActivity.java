package mvp.wyyne.douban.moviedouban.home.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.home.IPresent;

/**
 *
 * 基类Activity
 *
 * @author XXW
 * @date 2017/6/19
 */

public abstract class BaseActivity<T extends IPresent> extends AppCompatActivity {

    @Nullable
    @BindView(R.id.avl_loading)

    protected AVLoadingIndicatorView mLoadingView;

    @Nullable
    @BindView(R.id.swipe_refresh)
    protected SwipeRefreshLayout mSwipeRefresh;


    protected T mPresent;


    //只有在多个Fragment实例才会执行此方法
    //如果isVisibleToUser 为true表示当前用户可见的Fragment,getUserVisible也为true 就可以去执行更新的数据,实现赖加载


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initView();
    }

    /**
     * 刷新数据
     */
    protected abstract void refresh();

    /**
     * 布局ID
     *
     * @return
     */
    protected abstract int getLayoutId();


    /**
     * 初始化View
     */
    protected abstract void initView();


    protected void isDisplayLoading(boolean isDisplay) {
        if (mLoadingView != null) {
            if (isDisplay) {
                mLoadingView.setVisibility(View.VISIBLE);
            } else {
                mLoadingView.setVisibility(View.GONE);
            }
        }
    }


    /**
     * @param context 上下文
     * @param active  目标类
     */
    public void intentActivity(Context context, Class active) {
        Intent intent = new Intent(context, active);
        startActivity(intent);
    }

}
