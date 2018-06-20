package mvp.wyyne.douban.moviedouban.oneself.wanna;

import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import mvp.wyyne.douban.moviedouban.AndroidApplication;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.adapter.WannaAdapter;
import mvp.wyyne.douban.moviedouban.api.RvItemOnClick;
import mvp.wyyne.douban.moviedouban.api.model.WannaModel;
import mvp.wyyne.douban.moviedouban.api.model.WannaTable;
import mvp.wyyne.douban.moviedouban.home.base.BaseFragment;
import mvp.wyyne.douban.moviedouban.widget.VerticalItemDecoration;

/**
 * 我的-想看
 *
 * @author XXW
 * @date 2017/7/22
 */

public class OneselfWannaFragment extends BaseFragment implements RvItemOnClick {
    @BindView(R.id.tv_filtrate)
    TextView tvFiltrate;
    @BindView(R.id.ll_wanna_content)
    LinearLayout llWannaContent;
    @BindView(R.id.tv_number_subject)
    TextView tvNumberSubject;
    @BindView(R.id.ll_tab_content)
    LinearLayout llTabContent;
    @BindView(R.id.tv_empty)
    TextView tvEmpty;
    @BindView(R.id.ll_empty)
    LinearLayout llEmpty;
    @BindView(R.id.rv_actor)
    RecyclerView rvActor;
    private List<WannaTable> mList;
    private WannaAdapter adapter;

    public static Fragment getInstance() {
        OneselfWannaFragment fragment = new OneselfWannaFragment();
        return fragment;
    }

    @Override
    protected void refresh() {
        mList = WannaModel.getInstance().queryModelList();
        adapter.setList(mList);
        adapter.notifyDataSetChanged();

        mSwipeRefresh.setRefreshing(false);
    }

    @Override
    public void onResume() {
        super.onResume();
        handleLogin();
        initSubjectList();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wanna;
    }

    @Override
    protected void initView() {
        adapter = new WannaAdapter(getActivity(), mList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvActor.setLayoutManager(layoutManager);
        VerticalItemDecoration itemDecoration = new VerticalItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.line_gray_horizantal));
        rvActor.addItemDecoration(itemDecoration);
        View footerView = LayoutInflater.from(getActivity()).inflate(R.layout.item_oneself_actor_footer, rvActor, false);
        adapter.setRvOnClick(this);
        adapter.setFooterView(footerView);
        rvActor.setAdapter(adapter);
    }

    private void handleLogin() {
        if (AndroidApplication.getApplication().Login()) {
            llWannaContent.setVisibility(View.VISIBLE);
        } else {
            llWannaContent.setVisibility(View.GONE);
        }
    }


    private void initSubjectList() {
        if (WannaModel.getInstance().queryModelList().size() != 0) {
            mList = WannaModel.getInstance().queryModelList();
            llEmpty.setVisibility(View.GONE);
            String number = mList.size() + "部";
            tvNumberSubject.setText(number);
            adapter.setList(mList);
            adapter.notifyDataSetChanged();
            rvActor.setVisibility(View.VISIBLE);
        } else {
            rvActor.setVisibility(View.GONE);
            llEmpty.setVisibility(View.VISIBLE);
            tvNumberSubject.setText("0部");
        }
    }

    @Override
    public void onItemClick(int position, String tag) {

    }
}
