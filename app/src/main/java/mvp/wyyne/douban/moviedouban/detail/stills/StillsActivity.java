package mvp.wyyne.douban.moviedouban.detail.stills;


import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.adapter.TvAdapter;
import mvp.wyyne.douban.moviedouban.adapter.TvHeadAdapter;
import mvp.wyyne.douban.moviedouban.api.RvItemOnClick;
import mvp.wyyne.douban.moviedouban.api.bean.Article;
import mvp.wyyne.douban.moviedouban.api.bean.Source;
import mvp.wyyne.douban.moviedouban.api.bean.Trailers;
import mvp.wyyne.douban.moviedouban.api.bean.TvBean;
import mvp.wyyne.douban.moviedouban.home.base.BaseActivity;
import mvp.wyyne.douban.moviedouban.widget.RecycleViewUtils;

/**
 * @author XXW
 * @date 2017/7/2
 */

public class StillsActivity extends BaseActivity implements RvItemOnClick {
    public static final String MESSAGE = "message";
    @BindView(R.id.jp_video)
    JzvdStd mJpVideo;
    @BindView(R.id.rv_tv)
    RecyclerView mRvTv;
    RecyclerView mRvTvHead;
    private Article mArticle;
    private TvAdapter mTvAdapter;
    private LinearLayoutManager mManager;
    private LinearLayoutManager mHeadManager;
    //组装数据
    private TvBean[] mBeen =
            {
                    new TvBean(R.drawable.youku_normal, "优酷视频", "暂时本片"),
                    new TvBean(R.drawable.tencent_normal, "腾讯视频", "暂时本片"),
                    new TvBean(R.drawable.aiqiyi_normal, "爱奇艺", "暂时本片"),
                    new TvBean(R.drawable.bilibili_normal, "bilibili", "暂时本片"),
                    new TvBean(R.drawable.leshi_normal, "乐视视频", "暂时本片"),
                    new TvBean(R.drawable.mangguo_normal, "芒果TV", "暂时本片"),
                    new TvBean(R.drawable.sohu_normal, "搜狐视频", "暂时本片"),
            };
    private String[] mName = {"优酷视频", "腾讯视频", "爱奇艺", "bilibili", "乐视视频", "芒果TV", "搜狐视频"};
    private List<String> mTvName;
    private List<TvBean> mTvBeendefault;
    private List<TvBean> mTvBean;
    private TvHeadAdapter mTvHeadAdapter;
    private List<Trailers> mTrailerses;
    private View mHeadView;


    @Override
    protected void refresh() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_stills;
    }

    @Override
    protected void initView() {
        if (getIntent() != null) {
            mArticle = getIntent().getParcelableExtra(MESSAGE);
        }
        initVideo();
        initDate();

    }

    private void initDate() {
        mTrailerses = new ArrayList<>();
        mTvBeendefault = Arrays.asList(mBeen);
        mTvName = Arrays.asList(mName);
        if (mArticle.getVideos() != null && mArticle.getVideos().size() != 0) {
            for (int i = 0; i < mArticle.getVideos().size(); i++) {
                Source source = mArticle.getVideos().get(i).getSource();
                Log.d("XXW", "videos--" + source.getPic() + "--name--" + source.getName());
                if (mTvBeendefault.get(i).getTvName().equals(source.getName())) {
                    mTvBean.add(new TvBean(source.getPic(), source.getName(), "免费"));
                } else {
                    mTvBean.add(new TvBean(mTvBeendefault.get(i).getUrl(), mTvBeendefault.get(i).getTvName(), "免费"));
                }
            }
            mTvAdapter = new TvAdapter(this, mTvBean);
        } else {
            mTvAdapter = new TvAdapter(this, mTvBeendefault);
        }
        mManager = new LinearLayoutManager(this);
        mManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvTv.setLayoutManager(mManager);

        mHeadView = RecycleViewUtils.addHeadView(R.layout.activity_stills_head, this, mRvTv);
        initHeadView();
        mTvAdapter.setHeadView(mHeadView);
        mRvTv.setAdapter(mTvAdapter);

    }


    public void initHeadView() {
        if (mArticle.getTrailers().size() != 0) {
            for (Trailers trailers : mArticle.getTrailers()) {
                mTrailerses.add(trailers);
            }
        }
        if (mArticle.getBloopers().size() != 0) {
            for (Trailers trailers : mArticle.getBloopers()) {
                mTrailerses.add(trailers);
            }
        }
        mRvTvHead = (RecyclerView) mHeadView.findViewById(R.id.rv_tv_head);
        mHeadManager = new LinearLayoutManager(this);
        mHeadManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvTvHead.setLayoutManager(mHeadManager);
        mTvHeadAdapter = new TvHeadAdapter(this, mTrailerses);
        mTvHeadAdapter.setRvOnClick(this);
        mRvTvHead.setAdapter(mTvHeadAdapter);
    }

    private void initVideo() {
        if (mArticle != null) {
//            mJpVideo.setUp(mArticle.getTrailers().get(0).getResource_url(), JzvdStd.SCREEN_LAYOUT_NORMAL);
//            Glide.with(this).load(mArticle.getTrailers().get(0).getMedium()).into(mJpVideo.thumbImageView);
//            mJpVideo.startVideo();
        }
    }


    @Override
    public void onBackPressed() {
        if (JzvdStd.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mJpVideo.startButton.performClick();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JzvdStd.releaseAllVideos();
    }


    @Override
    public void onItemClick(int position, String tag) {
        if (mArticle != null) {
//            mJpVideo.setUp(mTrailerses.get(position).getResource_url(), Jzvd.SCREEN_LAYOUT_NORMAL);
//            Glide.with(this).load(mTrailerses.get(position).getMedium()).into(mJpVideo.thumbImageView);
        }
    }
}
