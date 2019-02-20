package com.wynne.common.base.present;

import android.app.Activity;

import com.wynne.common.base.BaseParam;

/**
 * 基类Presenter
 *
 * @author Wynne
 */
public abstract class BasePresenter<V extends BaseView> {
    protected Activity mContext;
    protected V mView;

    public BasePresenter() {

    }

    public void onAttach(V view, Activity context) {
        mView = view;
        mContext = context;
    }

    public void onDetach() {
        mView = null;
        mContext = null;
    }


    public abstract void onStart();

    public abstract void onParams(BaseParam baseParam);


}
