package mvp.wyyne.douban.moviedouban.detail.stills;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.adapter.StillsAdapter;
import mvp.wyyne.douban.moviedouban.api.RvItemOnClick;
import mvp.wyyne.douban.moviedouban.api.bean.CastPhoto;
import mvp.wyyne.douban.moviedouban.api.bean.Stills;
import mvp.wyyne.douban.moviedouban.api.bean.StillsPhotos;
import mvp.wyyne.douban.moviedouban.detail.photo.CastPhotoActivity;
import mvp.wyyne.douban.moviedouban.detail.photo.PhotoActivity;
import mvp.wyyne.douban.moviedouban.home.base.BaseActivity;

/**
 * Created by XXW on 2017/7/2.
 */

public class AllStillsActivity extends BaseActivity<AllStillsPresent> implements AllStillMain, View.OnClickListener, RvItemOnClick {
    public static final String STILLS = "stills";
    public static final String CAST = "cast";
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_stills_title)
    TextView mTvStillsTitle;
    @BindView(R.id.rv_all_stills)
    RecyclerView mRvAllStills;
    private Stills mStills;
    private StillsAdapter mAdapter;
    private List<StillsPhotos> mList;
    private String mStillId;
    private String mCastId;
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
        if (getIntent() != null) {
            mStillId = getIntent().getStringExtra(STILLS);
            mCastId = getIntent().getStringExtra(CAST);
            Log.d("XXW", "mStillId===" + mStillId + "===" + mCastId);
        }
        mIvBack.setOnClickListener(this);
        mPresent = new AllStillsImp(this);
        mList = new ArrayList<>();
        mAdapter = new StillsAdapter(this, mList);
        mManager = new GridLayoutManager(this, 3);
        mAdapter.setOnItemClick(this);
        mRvAllStills.setLayoutManager(mManager);
        mRvAllStills.setAdapter(mAdapter);
        if (mStillId != null) {
            mPresent.getStills(mStillId);
        } else {
            mPresent.getCasts(mCastId);
        }
    }


    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }


    @Override
    public void update(Stills stills) {
        mList = stills.getPhotos();
        mStills = stills;
        mAdapter.setList(stills.getPhotos());
        mAdapter.notifyDataSetChanged();
    }

    private CastPhoto mCastPhoto;

    @Override
    public void updateCast(CastPhoto stills) {
        mCastPhoto = stills;
        mAdapter.setList(stills.getPhotos());
        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void updateTitle() {
        if (mStills != null) {
            String title = mStills.getSubject().getTitle();
            mTvStillsTitle.setText(title + "剧照 " + "(" + mStills.getCount() + ")");
        } else {
            mTvStillsTitle.setText("" + "剧照 " + "(" + mCastPhoto.getCount() + ")");
        }
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_back) {
            finish();
        }
    }

    @Override
    public void onItemClick(int position, String tag) {
        if (mStillId != null) {
            Log.d("XXW", "STILLS");
            Intent intent = new Intent(this, PhotoActivity.class);
            intent.putExtra(PhotoActivity.ID, mStillId);
            intent.putExtra(PhotoActivity.POSITION, position);
            startActivity(intent);
        } else {
            Log.d("XXW", "mCastId");
            Intent intent = new Intent(this, CastPhotoActivity.class);
            intent.putExtra(CastPhotoActivity.ID, mCastId);
            intent.putExtra(CastPhotoActivity.POSITION, position);
            startActivity(intent);
        }
    }
}
