package mvp.wyyne.douban.moviedouban.oneself.tab;

import android.support.v4.app.Fragment;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.home.base.BaseFragment;

/**
 * 我的 -想看
 *
 * @author XXW
 * @date 2017/7/22
 */

public class LanHuFragment extends BaseFragment {


    public static Fragment getInstance() {
        LanHuFragment fragment = new LanHuFragment();
        return fragment;
    }

    @Override
    protected void refresh() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_lanhu;
    }

    @Override
    protected void initView() {

    }
}
