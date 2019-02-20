package com.wynne.common.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.wynne.common.base.present.BasePresenter;
import com.wynne.common.base.present.BaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.wynne.common.Constant.PARAM;

/**
 * @author Wynne
 */
public abstract class BaseActivity<V extends BaseView, P extends BasePresenter>
        extends AppCompatActivity implements BaseView {

    protected P presenter;
    Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
    }


    public void initConfiguration() {
        unbinder = ButterKnife.bind(this);
        presenter = initPresenter();
        if (presenter != null) {
            presenter.onAttach((V) this, this);
        }
        initView();
        Intent intent = getIntent();
        BaseParam param = (BaseParam) intent.getSerializableExtra(PARAM);
        if (presenter != null && param != null) {
            presenter.onParams(param);
        }

        if (presenter != null) {
            presenter.onStart();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.onDetach();
        }
        if (unbinder != null) {
            unbinder.unbind();
        }
    }


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


    /**
     * 初始化presenter
     *
     * @return
     */
    protected abstract P initPresenter();
}
