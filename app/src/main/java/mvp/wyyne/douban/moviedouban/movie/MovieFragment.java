package mvp.wyyne.douban.moviedouban.movie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.main.BaseFragment;

/**
 * Created by XXW on 2017/6/2.
 */

public class MovieFragment extends BaseFragment {


    @BindView(R.id.tv_city)
    TextView mTvCity;
    Unbinder unbinder;
    private View mContentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, mContentView);
        return mContentView;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_movie;
    }

    @Override
    protected void initView() {
        mTvCity.setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
