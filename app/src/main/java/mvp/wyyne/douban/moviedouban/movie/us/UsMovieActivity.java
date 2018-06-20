package mvp.wyyne.douban.moviedouban.movie.us;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.adapter.UsMovieAdapter;
import mvp.wyyne.douban.moviedouban.api.RvItemOnClick;
import mvp.wyyne.douban.moviedouban.api.bean.UsSubjects;
import mvp.wyyne.douban.moviedouban.detail.DetailMovieActivity;
import mvp.wyyne.douban.moviedouban.home.base.BaseActivity;
import mvp.wyyne.douban.moviedouban.utils.StatusUtils;

import static mvp.wyyne.douban.moviedouban.utils.Constant.DETAIL_TAG;

/**
 * @author Wynne
 * @date 2018/6/20
 */

public class UsMovieActivity extends BaseActivity implements RvItemOnClick {
    public static final String TAG = UsMovieActivity.class.getSimpleName();
    @BindView(R.id.tv_stills_title)
    TextView tvStillsTitle;
    @BindView(R.id.rv_us)
    RecyclerView rvUs;
    private List<UsSubjects> mList;

    @Override
    protected void refresh() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        StatusUtils.setStatusBarActivity(this, false, ContextCompat.getColor(this, R.color.white));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_us_movie;
    }

    @Override
    protected void initView() {
        tvStillsTitle.setText("北美票房榜");

        if (getIntent().getParcelableArrayListExtra(TAG) != null) {
            mList = getIntent().getParcelableArrayListExtra(TAG);
        }


        UsMovieAdapter adapter = new UsMovieAdapter(this, mList);
        adapter.setRvOnClick(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvUs.setLayoutManager(layoutManager);
        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rvUs.addItemDecoration(decoration);
        rvUs.setAdapter(adapter);
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