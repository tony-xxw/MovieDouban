package mvp.wyyne.douban.moviedouban.oneself.tab;

import android.support.v4.app.Fragment;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.home.base.BaseFragment;

/**
 * 我的-影人
 *
 * @author XXW
 * @date 2017/7/22
 */

public class CastFragment extends BaseFragment {

    public static Fragment getInstance() {
        CastFragment fragment = new CastFragment();
        return fragment;
    }

    @Override
    protected void refresh() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cast;
    }

    @Override
    protected void initView() {

    }
}
