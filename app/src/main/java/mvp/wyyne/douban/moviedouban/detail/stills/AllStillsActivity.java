package mvp.wyyne.douban.moviedouban.detail.stills;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.adapter.StillsAdapter;
import mvp.wyyne.douban.moviedouban.api.bean.Stills;
import mvp.wyyne.douban.moviedouban.api.bean.StillsPhotos;
import mvp.wyyne.douban.moviedouban.home.BaseActivity;

/**
 * Created by XXW on 2017/7/2.
 */

public class AllStillsActivity extends BaseActivity<AllStillsPresent> implements AllStillMain {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_stills_title)
    TextView mTvStillsTitle;
    @BindView(R.id.rv_all_stills)
    RecyclerView mRvAllStills;
    private Stills mStills;
    private StillsAdapter mAdapter;
    private List<StillsPhotos> mList;
    private String mId;
    private GridLayoutManager mManager;

    @Override
    protected void refresh() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_stills_all;
    }

    @Override
    protected void initView() {
        if (getIntent().getStringExtra("id") != null) {
            mId = getIntent().getStringExtra("id");
            Log.d("XXW", "Id---" + mId);
        }
        mPresent = new AllStillsImp(this);
        mList = new ArrayList<>();
        mAdapter = new StillsAdapter(this, mList);
        mManager = new GridLayoutManager(this, 3);
        mRvAllStills.setLayoutManager(mManager);
        mRvAllStills.setAdapter(mAdapter);
        mPresent.getList(mId);
        Log.d("XXW", "initView-----------" + mRvAllStills.toString());
    }


    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }


    @Override
    public void update(Stills stills) {
        Log.d("XXW", stills.getCount() + "---count" + "---size---" + stills.getPhotos().size());
        mList = stills.getPhotos();
        mStills = stills;
        mAdapter.setList(stills.getPhotos());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateTitle() {
        String title = mStills.getSubject().getTitle();
        mTvStillsTitle.setText(title + "剧照 " + "(" + mStills.getCount() + ")");
    }


}
