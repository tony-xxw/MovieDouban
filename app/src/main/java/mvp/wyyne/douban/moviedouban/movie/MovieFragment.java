package mvp.wyyne.douban.moviedouban.movie;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.home.base.BaseFragment;

/**
 * @author XXW
 * @date 2017/6/2
 */

public class MovieFragment extends BaseFragment {
    public static final String TAG = MovieFragment.class.getSimpleName();
    @BindView(R.id.tv_city)
    TextView mTvCity;

    private View mContentView;


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


}
