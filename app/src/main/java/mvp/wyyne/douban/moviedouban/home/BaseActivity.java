package mvp.wyyne.douban.moviedouban.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;
import mvp.wyyne.douban.moviedouban.R;

/**
 * 基类Activity
 * Created by XXW on 2017/6/19.
 */

public abstract class BaseActivity<T extends IPresent> extends AppCompatActivity {


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


    //只有在多个Fragment实例才会执行此方法
    //如果isVisibleToUser 为true表示当前用户可见的Fragment,getUserVisible也为true 就可以去执行更新的数据,实现赖加载
    //
  


  

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initView();
    }

    protected abstract void refresh();

    protected abstract int getLayoutId();

    protected abstract void initView();

}
