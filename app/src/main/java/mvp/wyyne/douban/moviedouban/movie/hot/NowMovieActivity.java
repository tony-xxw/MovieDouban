package mvp.wyyne.douban.moviedouban.movie.hot;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.adapter.HotMovieAdapter;
import mvp.wyyne.douban.moviedouban.api.RvItemOnClick;
import mvp.wyyne.douban.moviedouban.api.bean.Subjects;
import mvp.wyyne.douban.moviedouban.detail.DetailMovieActivity;
import mvp.wyyne.douban.moviedouban.home.base.BaseActivity;
import mvp.wyyne.douban.moviedouban.utils.ResourcesUtils;
import mvp.wyyne.douban.moviedouban.utils.StatusUtils;

import static com.wynne.common.Constant.DETAIL_TAG;

/**
 * 找片-热门
 *
 * @author Wynne
 * @date 2018/6/19
 */

public class NowMovieActivity extends BaseActivity implements RvItemOnClick {
    public static final String TAG = NowMovieActivity.class.getSimpleName();
    @BindView(R.id.rv_hot)
    RecyclerView rvHot;
    @BindView(R.id.tv_stills_title)
    TextView tvStillsTitle;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    private List<Subjects> mList;
    private HotMovieAdapter movieAdapter;

    @Override
    protected void refresh() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_hot_movie;
    }

    @Override
    protected void initView() {
        if (getIntent().getParcelableArrayListExtra(TAG) != null) {
            mList = getIntent().getParcelableArrayListExtra(TAG);
        }
        tvStillsTitle.setText("豆瓣热门");
        ivShare.setVisibility(View.VISIBLE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvHot.setLayoutManager(layoutManager);
        rvHot.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        movieAdapter = new HotMovieAdapter(this, mList);
        movieAdapter.setRvOnClick(this);
        rvHot.setAdapter(movieAdapter);
    }


    @Override
    protected void onResume() {
        super.onResume();
        StatusUtils.setStatusBarColor(this, ResourcesUtils.getColor(R.color.white, this), true);
    }


    @OnClick(R.id.iv_back)
    public void onViewClicked(View view) {
        finish();
    }

    @Override
    public void onItemClick(int position, String tag) {
        Intent intentDetail = new Intent(this, DetailMovieActivity.class);
        intentDetail.putExtra(DETAIL_TAG, mList.get(position).getId());
        startActivity(intentDetail);
    }
}
