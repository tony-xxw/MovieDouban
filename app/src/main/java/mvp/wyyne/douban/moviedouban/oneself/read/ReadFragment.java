package mvp.wyyne.douban.moviedouban.oneself.read;

import android.support.v4.app.Fragment;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.home.base.BaseFragment;

/**
 * Created by XXW on 2017/7/22.
 */

public class ReadFragment extends BaseFragment {

    public static Fragment getInstance() {
        ReadFragment fragment = new ReadFragment();
        return fragment;
    }

    @Override
    protected void refresh() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_read;
    }

    @Override
    protected void initView() {

    }
}
