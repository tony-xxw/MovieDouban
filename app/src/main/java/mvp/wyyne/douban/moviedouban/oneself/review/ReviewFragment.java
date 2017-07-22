package mvp.wyyne.douban.moviedouban.oneself.review;

import android.support.v4.app.Fragment;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.home.BaseFragment;

/**
 * Created by XXW on 2017/7/22.
 */

public class ReviewFragment extends BaseFragment {

    public static Fragment getInstace() {
        ReviewFragment fragment = new ReviewFragment();
        return fragment;
    }

    @Override
    protected void refresh() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_review;
    }

    @Override
    protected void initView() {

    }
}
