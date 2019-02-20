package com.wynne.common.base.present;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * 基类 Presenter 网络层
 *
 * @author Wynne
 */
public abstract class BaseNetPresenter<T extends BaseView> extends BasePresenter<T> {
    private List<Disposable> disposables = new ArrayList<>();

    protected int pageSize;
    protected int page;

    @Override
    public void onDetach() {
        if (disposables != null && !disposables.isEmpty()) {
            for (Disposable disposable : disposables) {
                if (disposable.isDisposed()) {
                    continue;
                }
                disposable.dispose();
            }

            disposables.clear();
            disposables = null;
        }
        super.onDetach();
    }


}
