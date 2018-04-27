package mvp.wyyne.douban.moviedouban.oneself.tab;

import android.support.v4.app.Fragment;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.home.base.BaseFragment;

/**
 * 我的 -再看
 *
 * @author XXW
 * @date 2017/7/22
 */

public class SightFragment extends BaseFragment {

    public static Fragment getInstance() {
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
