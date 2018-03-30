package mvp.wyyne.douban.moviedouban.discuss;

import android.support.v4.app.Fragment;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.home.base.BaseFragment;

/**
 * Created by XXW on 2017/7/22.
 */

public class DiscussFragment extends BaseFragment {


    public static Fragment getInstance() {
        DiscussFragment fragment = new DiscussFragment();
        return fragment;
    }

    @Override
    protected void refresh() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_discuss;
    }

    @Override
    protected void initView() {

    }
}
