package mvp.wyyne.douban.moviedouban.movie.us;

import android.content.Intent;

import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.adapter.UsMovieAdapter;
import mvp.wyyne.douban.moviedouban.api.RvItemOnClick;
import mvp.wyyne.douban.moviedouban.api.bean.UsSubjects;
import mvp.wyyne.douban.moviedouban.detail.DetailMovieActivity;
import mvp.wyyne.douban.moviedouban.home.base.BaseActivity;
import mvp.wyyne.douban.moviedouban.utils.ResourcesUtils;
import mvp.wyyne.douban.moviedouban.utils.StatusUtils;

import static com.wynne.common.Constant.DETAIL_TAG;

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
    protected int getLayoutId() {
        return R.layout.activity_us_movie;
    }

    @Override
    protected void initView() {
        StatusUtils.setStatusBarColor(this, ResourcesUtils.getColor(R.color.white, this), true);
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
