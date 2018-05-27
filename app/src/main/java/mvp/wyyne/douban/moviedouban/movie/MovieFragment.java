package mvp.wyyne.douban.moviedouban.movie;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.home.base.BaseFragment;
import mvp.wyyne.douban.moviedouban.search.SearchMovieActivity;

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

        mTvCity.setVisibility(View.GONE);
    }


    @OnClick({R.id.dcl_search})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.dcl_search:
                startActivity(new Intent(getActivity(), SearchMovieActivity.class));
                break;
            default:
                break;
        }
    }
}
