package mvp.wyyne.douban.moviedouban.hot.city;

import android.content.Intent;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.adapter.viewpage.SubjectTitlePageAdapter;
import mvp.wyyne.douban.moviedouban.home.MainActivity;
import mvp.wyyne.douban.moviedouban.home.base.BaseActivity;

import static com.wynne.common.Constant.CITY_RESULT;

/**
 * @author XXW
 * @date 2017/8/15.
 */

public class CityActivity extends BaseActivity {
    public static final String TAG = CityActivity.class.getSimpleName();
    @BindView(R.id.tv_stills_title)
    TextView mTvCtTitle;
    @BindView(R.id.iv_share)
    ImageView mIvShare;
    @BindView(R.id.rl_content)
    RelativeLayout mLlTitle;
    @BindView(R.id.tl_ct)
    TabLayout mTlCt;
    @BindView(R.id.vp_ct)
    ViewPager mVpCt;


    private String[] mStrings = {"国内", "海外"};
    private List<Fragment> mList;


    @Override
    protected void refresh() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_city;
    }

    @Override
    protected void initView() {
        setStatusBarColor(R.color.white, true);
        mLlTitle.setBackgroundColor(getResources().getColor(R.color.white));
        mTvCtTitle.setText(getString(R.string.city_select));

        mTlCt.addTab(mTlCt.newTab().setText(mStrings[0]));
        mTlCt.addTab(mTlCt.newTab().setText(mStrings[1]));


        mList = new ArrayList<>();

        mList.add(new ChinaFragment());
        mList.add(new ForeignFragment());

        SubjectTitlePageAdapter mAdapter = new SubjectTitlePageAdapter(getSupportFragmentManager());
        mAdapter.setTitleList(Arrays.asList(mStrings));
        mAdapter.setFragment(mList);
        mVpCt.setAdapter(mAdapter);


        mTlCt.setupWithViewPager(mVpCt);
    }

    @OnClick({R.id.iv_back})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            default:
                break;
        }
    }

    public void setCityResult(String city) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(TAG, city);
        setResult(CITY_RESULT, intent);
        finish();
    }
}
