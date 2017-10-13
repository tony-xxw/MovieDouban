package mvp.wyyne.douban.moviedouban.hot.city;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.home.BaseFragment;

/**
 * 国内列表
 * Created by XXW on 2017/8/16.
 */

public class ChinaFragment extends BaseFragment {
    @BindView(R.id.rv_cn)
    RecyclerView mList;

    @Override
    protected void refresh() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_china;
    }

    @Override
    protected void initView() {
        mList.setLayoutManager(new GridLayoutManager(getActivity(), 3));


    }
}
