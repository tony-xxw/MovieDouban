package mvp.wyyne.douban.moviedouban.oneself.sight;

import android.support.v4.app.Fragment;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.home.BaseFragment;

/**
 * Created by XXW on 2017/7/22.
 */

public class SightFragment extends BaseFragment {

    public static Fragment getInstace() {
        SightFragment fragment = new SightFragment();
        return fragment;
    }


    @Override
    protected void refresh() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sight;
    }

    @Override
    protected void initView() {

    }
}
