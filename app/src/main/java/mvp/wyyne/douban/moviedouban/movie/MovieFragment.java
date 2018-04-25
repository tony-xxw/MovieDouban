package mvp.wyyne.douban.moviedouban.movie;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.BarUtils;

import butterknife.BindView;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.home.base.BaseFragment;
import mvp.wyyne.douban.moviedouban.utils.StatusUtils;

/**
 * @author XXW
 * @date 2017/6/2
 */

public class MovieFragment extends BaseFragment {
    public static final String TAG = MovieFragment.class.getSimpleName();
    @BindView(R.id.tv_city)
    TextView mTvCity;
    @BindView(R.id.ll_movie)
    LinearLayout mLlMovie;


    public static MovieFragment getInstance() {
        return new MovieFragment();
    }

    @Override
    protected void refresh() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_movie;
    }

    @Override
    protected void initView() {
        StatusUtils.setStatusColor(getActivity(),Color.WHITE);
        mTvCity.setVisibility(View.GONE);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        BarUtils.addMarginTopEqualStatusBarHeight(mLlMovie);
    }
}
