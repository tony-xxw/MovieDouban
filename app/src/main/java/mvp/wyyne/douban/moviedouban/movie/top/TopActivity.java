package mvp.wyyne.douban.moviedouban.movie.top;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.adapter.viewpage.SubjectTitlePageAdapter;
import mvp.wyyne.douban.moviedouban.home.base.BaseActivity;
import mvp.wyyne.douban.moviedouban.utils.ResourcesUtils;
import mvp.wyyne.douban.moviedouban.utils.StatusUtils;

/**
 * @author Wynne
 * @date 2018/6/20
 */

public class TopActivity extends BaseActivity {
    @BindView(R.id.tl_top)
    TabLayout tlTop;
    @BindView(R.id.vp_top)
    ViewPager vpTop;
    @BindView(R.id.tv_stills_title)
    TextView tvStillsTitle;
    private List<Fragment> mList;
    private SubjectTitlePageAdapter adapter;
    private List<String> titleList;
    private String[] title = {"Top1-50", "51-100", "101-150", "151-200", "201-250"};

    @Override
    protected void refresh() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_top_movie;
    }

    @Override
    protected void initView() {
        StatusUtils.setStatusBarColor(this, ResourcesUtils.getColor(R.color.white, this), true);
        tvStillsTitle.setText("豆瓣Top250");
        tlTop.setTabMode(TabLayout.MODE_FIXED);
        initList();
        adapter = new SubjectTitlePageAdapter(getSupportFragmentManager());
        adapter.setFragment(mList);
        adapter.setTitleList(titleList);
        vpTop.setAdapter(adapter);
        tlTop.setupWithViewPager(vpTop);
    }

    private void initList() {
        mList = new ArrayList<>();
        mList.add(new TopFragment());
        mList.add(new TopFragment());
        mList.add(new TopFragment());
        mList.add(new TopFragment());
        mList.add(new TopFragment());

        titleList = Arrays.asList(title);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @OnClick({R.id.iv_back})
    public void onViewClick() {
        finish();
    }

}
