package mvp.wyyne.douban.moviedouban.movie;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.home.BaseFragment;

/**
 * Created by XXW on 2017/6/2.
 */

public class MovieFragment extends BaseFragment {


    @BindView(R.id.tv_city)
    TextView mTvCity;

    private View mContentView;


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
