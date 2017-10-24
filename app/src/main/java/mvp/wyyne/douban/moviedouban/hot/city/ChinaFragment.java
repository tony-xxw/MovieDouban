package mvp.wyyne.douban.moviedouban.hot.city;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.adapter.CityAdapter;
import mvp.wyyne.douban.moviedouban.home.BaseFragment;

/**
 * 国内列表
 * Created by XXW on 2017/8/16.
 */

public class ChinaFragment extends BaseFragment {
    @BindView(R.id.rv_cn)
    RecyclerView mRvView;
    private CityAdapter mCityAdapter;
    private String[] hotCity = {
            "北京", "上海", "广州",
            "深圳", "武汉", "成都",
            "杭州", "重庆", "郑州",
            "南京", "西安", "苏州",
            "天津", "长沙", "福州"};
    private List<String> hotArryCity;

    @Override
    protected void refresh() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_china;
    }

    @Override
    protected void initView() {
        hotArryCity = Arrays.asList(hotCity);

        mRvView.setLayoutManager(new GridLayoutManager(getActivity(), 3));



    }
}
