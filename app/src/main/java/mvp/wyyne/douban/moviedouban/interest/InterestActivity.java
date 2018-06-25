package mvp.wyyne.douban.moviedouban.interest;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
 * 想看-看过
 *
 * @author Wynne
 * @date 2018/6/12
 */

public class InterestActivity extends BaseActivity implements TabLayout.OnTabSelectedListener {
    public static final String TAG = InterestActivity.class.getSimpleName();
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    @BindView(R.id.bottom_line)
    View bottomLine;
    @BindView(R.id.tl_interest)
    TabLayout tlInterest;
    @BindView(R.id.vp_content)
    ViewPager vpContent;
    private List<Fragment> mList;
    private String[] mString = {"想看", "看过"};
    private ViewHolder oneViewHolder;
    private ViewHolder twoViewHolder;


    @Override
    protected void refresh() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        StatusUtils.setStatusBarColor(this,
                ResourcesUtils.getColor(R.color.white, this), true);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_interest;
    }

    @Override
    protected void initView() {
        mList = new ArrayList<>();
        ivBack.setVisibility(View.GONE);
        bottomLine.setVisibility(View.VISIBLE);
        ivClose.setVisibility(View.VISIBLE);
        btnConfirm.setVisibility(View.VISIBLE);

        tlInterest.setTabMode(TabLayout.MODE_FIXED);
        tlInterest.addTab(tlInterest.newTab().setText(getString(R.string.wanna_see)));
        tlInterest.addTab(tlInterest.newTab().setText(getString(R.string.have_seen)));

        tlInterest.addOnTabSelectedListener(this);

        mList.add(new ReadFragment());
        mList.add(new WantFragment());
        SubjectTitlePageAdapter adapter = new SubjectTitlePageAdapter(getSupportFragmentManager());
        adapter.setTitleList(Arrays.asList(mString));
        adapter.setFragment(mList);
        vpContent.setAdapter(adapter);
        tlInterest.setupWithViewPager(vpContent);
    }


    @OnClick({R.id.iv_close, R.id.btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                finish();
                break;
            case R.id.btn_confirm:
                //标记
                break;
            default:
                break;
        }
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        Log.d("XXW", "onTabSelected  :" + tab.getPosition());
        if (tab.getPosition() == 0) {
            if (tab.getCustomView() == null) {
                tab.setCustomView(LayoutInflater.from(this).inflate(R.layout.item_text_interest, tlInterest, false));
                oneViewHolder = new ViewHolder(tab.getCustomView());
            }

            tabText(oneViewHolder, 14);
        } else {
            if (tab.getCustomView() == null) {
                tab.setCustomView(LayoutInflater.from(this).inflate(R.layout.item_text_interest, tlInterest, false));
                twoViewHolder = new ViewHolder(tab.getCustomView());
            }
            tabText(twoViewHolder, 14);
        }


    }


    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        Log.d("XXW", "onTabUnselected  :" + tab.getPosition());
        if (tab.getPosition() == 0) {
            tabText(oneViewHolder, 14);
        } else {
            tabText(twoViewHolder, 14);
        }
    }


    public void tabText(ViewHolder viewHolder, int size) {
        if (viewHolder.equals(oneViewHolder)) {
            TextView textView = (TextView) viewHolder.mContentView.findViewById(R.id.tv_interest);
            textView.setText(getString(R.string.wanna_see));
            textView.setTextSize(size);
        } else {
            TextView textView = (TextView) viewHolder.mContentView.findViewById(R.id.tv_interest);
            textView.setText(getString(R.string.have_seen));
            textView.setTextSize(size);
        }
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


    class ViewHolder {

        public ViewHolder(View mContentView) {
            this.mContentView = mContentView;
        }

        View mContentView;


    }
}
