package mvp.wyyne.douban.moviedouban.hot.city;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.adapter.CityAdapter;
import mvp.wyyne.douban.moviedouban.api.bean.City;
import mvp.wyyne.douban.moviedouban.home.BaseFragment;

/**
 * 国内列表
 *
 * @author XXW
 * @date 2017/8/16.
 */

public class ChinaFragment extends BaseFragment {
    @BindView(R.id.rv_hot_city)
    RecyclerView mRvHotCity;
    private CityAdapter mCityAdapter;
    private String[] hotCity = {
            "北京", "上海", "广州",
            "深圳", "武汉", "成都",
            "杭州", "重庆", "郑州",
            "南京", "西安", "苏州",
            "天津", "长沙", "福州"};
    private List<City> mCityList = new ArrayList<>();


    @Override
    protected void refresh() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_china;
    }

    @Override
    protected void initView() {
        for (String city : hotCity) {
            mCityList.add(new City(city));
        }
        mCityAdapter = new CityAdapter(getActivity(), mCityList);
        mRvHotCity.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mRvHotCity.setAdapter(mCityAdapter);


    }
}
