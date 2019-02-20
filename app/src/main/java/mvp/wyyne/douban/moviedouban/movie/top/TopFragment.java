package mvp.wyyne.douban.moviedouban.movie.top;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.functions.Consumer;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.adapter.HotMovieAdapter;
import mvp.wyyne.douban.moviedouban.api.RetrofitService;
import mvp.wyyne.douban.moviedouban.api.RvItemOnClick;
import mvp.wyyne.douban.moviedouban.api.bean.Subjects;
import mvp.wyyne.douban.moviedouban.detail.DetailMovieActivity;
import mvp.wyyne.douban.moviedouban.home.base.BaseFragment;
import mvp.wyyne.douban.moviedouban.widget.VerticalItemDecoration;

import static com.wynne.common.Constant.DETAIL_TAG;

/**
 * @author Wynne
 * @date 2018/6/20
 */

public class TopFragment extends BaseFragment implements RvItemOnClick {
    public static final String TAG = TopFragment.class.getSimpleName();
    @BindView(R.id.rv_top)
    RecyclerView rvTop;
    private List<Subjects> mList;
    private HotMovieAdapter topAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_top_movie;
    }


    @Override
    protected void initView() {
        mList = new ArrayList<>();
        topAdapter = new HotMovieAdapter(getActivity(), mList);
        topAdapter.setRvOnClick(this);
        VerticalItemDecoration itemDecoration = new VerticalItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.line_gray_horizantal));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvTop.setLayoutManager(layoutManager);
        rvTop.addItemDecoration(itemDecoration);
        rvTop.setAdapter(topAdapter);


        RetrofitService.getTopMovie().subscribe(new Consumer<List<Subjects>>() {
            @Override
            public void accept(List<Subjects> list) throws Exception {
                mList = list;
                topAdapter.setList(list);
                topAdapter.notifyDataSetChanged();
            }
        });
    }


    @Override
    public void onItemClick(int position, String tag) {
        Intent detailIntent = new Intent(getActivity(), DetailMovieActivity.class);
        detailIntent.putExtra(DETAIL_TAG, mList.get(position).getId());
        startActivity(detailIntent);
    }
}
