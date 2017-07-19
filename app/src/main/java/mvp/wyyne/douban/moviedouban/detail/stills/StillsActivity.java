package mvp.wyyne.douban.moviedouban.detail.stills;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.bumptech.glide.Glide;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.adapter.TvAdapter;
import mvp.wyyne.douban.moviedouban.api.bean.Article;
import mvp.wyyne.douban.moviedouban.api.bean.Source;
import mvp.wyyne.douban.moviedouban.api.bean.TvBean;
import mvp.wyyne.douban.moviedouban.home.BaseActivity;

/**
 * Created by XXW on 2017/7/2.
 */

public class StillsActivity extends BaseActivity {
    public static final String MESSAGE = "message";
    @BindView(R.id.jp_video)
    JCVideoPlayerStandard mJpVideo;
    @BindView(R.id.rv_tv)
    RecyclerView mRvTv;
    private Article mArticle;
    private TvAdapter mTvAdapter;
    private LinearLayoutManager mManager;
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
        mRvTv.setAdapter(mTvAdapter);

    }

    private void initVideo() {
        if (mArticle != null) {
            mJpVideo.setUp(mArticle.getTrailers().get(0).getResource_url(), JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL);
            Glide.with(this).load(mArticle.getTrailers().get(0).getMedium()).into(mJpVideo.thumbImageView);
            mJpVideo.setActivity(this);
        }
    }


    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
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
        JCVideoPlayer.releaseAllVideos();
    }


}
