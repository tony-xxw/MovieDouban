package mvp.wyyne.douban.moviedouban.movie.weekly;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.adapter.WeeklyMovieAdapter;
import mvp.wyyne.douban.moviedouban.api.RvItemOnClick;
import mvp.wyyne.douban.moviedouban.api.bean.WeeklySubject;
import mvp.wyyne.douban.moviedouban.detail.DetailMovieActivity;
import mvp.wyyne.douban.moviedouban.home.IMain;
import mvp.wyyne.douban.moviedouban.home.base.BaseActivity;
import mvp.wyyne.douban.moviedouban.utils.ResourcesUtils;
import mvp.wyyne.douban.moviedouban.utils.StatusUtils;

import static mvp.wyyne.douban.moviedouban.utils.Constant.DETAIL_TAG;

/**
 * @author Wynne
 * @date 2018/6/19
 */

public class WeeklyMovieActivity extends BaseActivity implements IMain, RvItemOnClick {
    public static final String TAG = WeeklyMovieActivity.class.getSimpleName();
    @BindView(R.id.tv_stills_title)
    TextView tvStillsTitle;
    @BindView(R.id.rv_weekly)
    RecyclerView rvWeekly;
    private List<WeeklySubject> mList;


    @Override
    protected void refresh() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_weekly_movie;
    }


    @Override
    protected void initView() {
        StatusUtils.setStatusBarColor(this, ResourcesUtils.getColor(R.color.white, this), true);

        tvStillsTitle.setText("本周口碑榜");
        if (getIntent().getParcelableArrayListExtra(TAG) != null) {
            mList = getIntent().getParcelableArrayListExtra(TAG);
        }
        WeeklyMovieAdapter adapter = new WeeklyMovieAdapter(this, mList);
        adapter.setRvOnClick(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvWeekly.setLayoutManager(layoutManager);
        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rvWeekly.addItemDecoration(decoration);
        rvWeekly.setAdapter(adapter);

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onItemClick(int position, String tag) {
        if (mList != null && mList.size() != 0) {
            Intent intent = new Intent(this, DetailMovieActivity.class);
            intent.putExtra(DETAIL_TAG, mList.get(position).getSubject().getId());
            startActivity(intent);
        }
    }
}
