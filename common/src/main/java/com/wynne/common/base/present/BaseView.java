package com.wynne.common.base.present;

/**
 * 基类 View层
 *
 * @author Wynne
 */
public interface BaseView {

    void showLoadingDialog();

    void dismissLoadingDialog();

    void showToast(String msg);

}
