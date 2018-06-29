package mvp.wyyne.douban.moviedouban.interest;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.TimeUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.adapter.viewpage.SubjectTitlePageAdapter;
import mvp.wyyne.douban.moviedouban.api.bean.Article;
import mvp.wyyne.douban.moviedouban.api.model.WannaModel;
import mvp.wyyne.douban.moviedouban.api.model.WannaTable;
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
    @BindView(R.id.iv_close_gray)
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
    private ReadFragment readFragment;
    private WantFragment wantFragment;
    private Article mArticle;
    /**
     * 当前position
     */
    private int currentPosition;

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

        if (getIntent().getParcelableExtra(TAG) != null) {
            mArticle = getIntent().getParcelableExtra(TAG);
        }

        mList = new ArrayList<>();
        ivBack.setVisibility(View.GONE);
        bottomLine.setVisibility(View.VISIBLE);
        ivClose.setVisibility(View.VISIBLE);
        btnConfirm.setVisibility(View.VISIBLE);

        tlInterest.setTabMode(TabLayout.MODE_FIXED);


        tlInterest.addOnTabSelectedListener(this);

        readFragment = new ReadFragment();
        wantFragment = new WantFragment();
        mList.add(readFragment);
        mList.add(wantFragment);
        SubjectTitlePageAdapter adapter = new SubjectTitlePageAdapter(getSupportFragmentManager());
        adapter.setTitleList(Arrays.asList(mString));
        adapter.setFragment(mList);
        vpContent.setAdapter(adapter);
        tlInterest.setupWithViewPager(vpContent);
    }


    @OnClick({R.id.iv_close_gray, R.id.btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close_gray:
                finish();
                break;
            case R.id.btn_confirm:
                if (getCurrentTab()) {


                } else {
                    if (mArticle != null) {
                        WannaTable table = new WannaTable();
                        table.setAvatarUrl(mArticle.getImages().getMedium());
                        table.setAverage(mArticle.getRating().getAverage() + "");
                        StringBuffer directorsBuffer = new StringBuffer();
                        StringBuffer castsBuffer = new StringBuffer();
                        for (int i = 0; i < mArticle.getDirectors().size(); i++) {
                            if (i == 3) {
                                continue;
                            }
                            if (i != mArticle.getDirectors().size() - 1) {
                                directorsBuffer.append(mArticle.getDirectors().get(i).getName() + "/");
                            } else {
                                directorsBuffer.append(mArticle.getDirectors().get(i).getName());
                            }
                        }
                        for (int i = 0; i < mArticle.getCasts().size(); i++) {
                            if (i == 3) {
                                continue;
                            }
                            if (i != mArticle.getCasts().size() - 1) {
                                castsBuffer.append(mArticle.getCasts().get(i).getName() + "/");
                            } else {
                                castsBuffer.append(mArticle.getCasts().get(i).getName());
                            }
                        }
                        table.setDirectors(directorsBuffer.toString());
                        table.setCasts(castsBuffer.toString());
                        table.setTitle(mArticle.getTitle());
                        table.setCreatetime(TimeUtils.getNowString());
                        table.setId(Long.valueOf(mArticle.getId()));
                        table.setReason(wantFragment.getReasonString());
                        table.setLabel(wantFragment.getLabelString());
                        table.setIsLabel(true);
                        WannaModel.getInstance().insertModel(table);
                        showToast("正在标记");
                        intentActivity(this, ShareLabelActivity.class);
                    }
                }
                break;
            default:
                break;
        }
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        currentPosition = tab.getPosition();
        if (tab.getPosition() == 0) {
            if (tab.getCustomView() == null) {
                tab.setCustomView(LayoutInflater.from(this).inflate(R.layout.item_text_interest, tlInterest, false));
                oneViewHolder = new ViewHolder(tab.getCustomView());
            }

            tabText(oneViewHolder, 22);
        } else {
            if (tab.getCustomView() == null) {
                tab.setCustomView(LayoutInflater.from(this).inflate(R.layout.item_text_interest, tlInterest, false));
                twoViewHolder = new ViewHolder(tab.getCustomView());
            }
            tabText(twoViewHolder, 22);
        }


    }


    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        if (tab.getPosition() == 0) {
            tabText(oneViewHolder, 16);
        } else {
            tabText(twoViewHolder, 16);
        }
    }

    public boolean getCurrentTab() {
        if (currentPosition == 0) {
            return false;
        } else {
            return true;
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
