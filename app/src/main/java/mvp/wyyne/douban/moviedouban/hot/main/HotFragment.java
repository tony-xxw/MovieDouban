package mvp.wyyne.douban.moviedouban.hot.main;


import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.bean.Subjects;
import mvp.wyyne.douban.moviedouban.home.MainActivity;
import mvp.wyyne.douban.moviedouban.home.base.BaseFragment;
import mvp.wyyne.douban.moviedouban.hot.city.CityActivity;
import mvp.wyyne.douban.moviedouban.search.SearchMovieActivity;

import static com.wynne.common.Constant.CITY_RESULT;
import static com.wynne.common.Constant.HOT_REQUEST;


/**
 * @author XXW
 * @date 2017/6/2
 */

public class HotFragment extends BaseFragment<HotPresenterImp> implements HotView {
    public static final String TAG = HotFragment.class.getSimpleName();
    @BindView(R.id.hot_tl)
    TabLayout mHotTl;
    @BindView(R.id.hot_vp)
    ViewPager mHotVp;
    @BindView(R.id.ll_hot)
    LinearLayout mLlHotLayout;
    @BindView(R.id.ll_search)
    public LinearLayout llSearch;
    @BindView(R.id.ll_search_main)
    public LinearLayout llSearchMain;
    @BindView(R.id.fl_search)
    public FrameLayout flSearch;
    @BindView(R.id.tv_city)
    public TextView tvCity;
    private MainActivity mainActivity;
    private ArrayList<Subjects> mList;


    public static HotFragment getInstance() {
        return new HotFragment();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initView() {
        mainActivity = (MainActivity) getActivity();
        //Fragment里 嵌套Fragment的Manager要用getChildFragmentManager()
        mPresent = new HotPresenterImp(this, getChildFragmentManager());
        mHotTl.setTabMode(TabLayout.MODE_FIXED);
        mPresent.initPage(mHotVp);


    }


    @Override
    public void onBindPage() {
        mHotTl.setupWithViewPager(mHotVp);

    }


    @OnClick({R.id.tv_city, R.id.dcl_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_city:
                startActivityForResult(new Intent(getActivity(), CityActivity.class), HOT_REQUEST);
                break;
            case R.id.dcl_search:
                if (mainActivity.getSubjects() != null) {
                    mList = mainActivity.getSubjects();
                }
                Intent intent = new Intent(getActivity(), SearchMovieActivity.class);
                intent.putParcelableArrayListExtra(SearchMovieActivity.TAG, mList);
                startActivity(intent);
                break;
            default:
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode > -1) {
            if (resultCode == CITY_RESULT) {
                String cityName = data.getStringExtra(CityActivity.TAG);
                tvCity.setText(cityName);
            }
        }
    }
}
