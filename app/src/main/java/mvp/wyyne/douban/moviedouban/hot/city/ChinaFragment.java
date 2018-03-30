package mvp.wyyne.douban.moviedouban.hot.city;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.adapter.CityAdapter;
import mvp.wyyne.douban.moviedouban.api.bean.City;
import mvp.wyyne.douban.moviedouban.home.base.BaseFragment;

/**
 * 国内列表
 *
 * @author XXW
 * @date 2017/8/16.
 */

public class ChinaFragment extends BaseFragment<ChinaPresenter> implements ChinaView {
    @BindView(R.id.rv_hot_city)
    RecyclerView mRvHotCity;
    @BindView(R.id.tv_gps_city)
    TextView mTvGpsCity;
    private CityAdapter mCityAdapter;
    private String[] hotCity = {
            "北京", "上海", "广州",
            "深圳", "武汉", "成都",
            "杭州", "重庆", "郑州",
            "南京", "西安", "苏州",
            "天津", "长沙", "福州"};
    private List<City> mCityList = new ArrayList<>();
    private ViewGroup.LayoutParams viewGroup;


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


        viewGroup = mTvGpsCity.getLayoutParams();
        viewGroup.width = getResources().getDisplayMetrics().widthPixels / 3 - 60;
        mTvGpsCity.setLayoutParams(viewGroup);
        Log.d("XXW", "mTvGpsCity===" + viewGroup.width);
//        mPresent = new ChinaPresenterImp(this);
        //开始定位
//        mPresent.initLocation();

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void initCity(String cityName) {

    }
}
