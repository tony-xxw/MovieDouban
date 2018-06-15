package mvp.wyyne.douban.moviedouban.oneself.cast;

import android.content.Intent;
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
import mvp.wyyne.douban.moviedouban.adapter.MineActorAdapter;
import mvp.wyyne.douban.moviedouban.api.RvItemOnClick;
import mvp.wyyne.douban.moviedouban.api.model.ActorCollectTable;
import mvp.wyyne.douban.moviedouban.detail.cast.ActorActivity;
import mvp.wyyne.douban.moviedouban.detail.cast.ActorModel;
import mvp.wyyne.douban.moviedouban.home.base.BaseFragment;

import static mvp.wyyne.douban.moviedouban.detail.cast.ActorActivity.ACTORID;

/**
 * 我的-影人
 *
 * @author XXW
 * @date 2017/7/22
 */

public class OneselfActorFragment extends BaseFragment implements RvItemOnClick {

    @BindView(R.id.tv_number_subject)
    TextView tvNumberSubject;
    @BindView(R.id.tv_filtrate)
    TextView tvFiltrate;
    @BindView(R.id.ll_tab_content)
    LinearLayout llTabContent;
    @BindView(R.id.ll_cast_content)
    LinearLayout llCastContent;
    @BindView(R.id.ll_empty)
    LinearLayout llEmpty;
    @BindView(R.id.rv_actor)
    RecyclerView rvActor;
    private MineActorAdapter mineActorAdapter;
    private List<ActorCollectTable> mList;


    public static Fragment getInstance() {
        OneselfActorFragment fragment = new OneselfActorFragment();
        return fragment;
    }

    @Override
    protected void refresh() {

    }

    @Override
    public void onResume() {
        super.onResume();
        handleLogin();
        initActorList();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cast;
    }

    @Override
    protected void initView() {
        mList = ActorModel.getInstance().queryModelList();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvActor.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.line_gray_horizantal));
        rvActor.addItemDecoration(itemDecoration);
        mineActorAdapter = new MineActorAdapter(getActivity(), mList);
        View footerView = LayoutInflater.from(getActivity()).inflate(R.layout.item_oneself_actor_footer, rvActor, false);
        mineActorAdapter.setRvOnClick(this);
        mineActorAdapter.setFooterView(footerView);
        rvActor.setAdapter(mineActorAdapter);
    }

    private void handleLogin() {
        if (AndroidApplication.getApplication().Login()) {
            llCastContent.setVisibility(View.VISIBLE);
            tvNumberSubject.setText("0位");
            llTabContent.setVisibility(View.GONE);
        } else {
            llCastContent.setVisibility(View.GONE);
        }
    }

    /**
     * 获取数据库数据,初始化页面
     */
    private void initActorList() {
        if (ActorModel.getInstance().queryModelList().size() != 0) {
            mList = ActorModel.getInstance().queryModelList();
            llEmpty.setVisibility(View.GONE);
            String number = mList.size() + "部";
            tvNumberSubject.setText(number);
            mineActorAdapter.setList(mList);
            mineActorAdapter.notifyDataSetChanged();
            rvActor.setVisibility(View.VISIBLE);
        } else {
            rvActor.setVisibility(View.GONE);
            llEmpty.setVisibility(View.VISIBLE);
            tvNumberSubject.setText("0部");
        }
    }


    @Override
    public void onItemClick(int position, String tag) {
        if (mList.get(position).getId() != 0) {
            Intent actorInfo = new Intent(getActivity(), ActorActivity.class);
            actorInfo.putExtra(ACTORID, String.valueOf(mList.get(position).getId()));
            startActivity(actorInfo);
        }
    }
}
