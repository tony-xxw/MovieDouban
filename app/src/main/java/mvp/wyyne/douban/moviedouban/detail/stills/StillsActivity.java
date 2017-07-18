package mvp.wyyne.douban.moviedouban.detail.stills;


import com.bumptech.glide.Glide;

import butterknife.BindView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.bean.Article;
import mvp.wyyne.douban.moviedouban.home.BaseActivity;

/**
 * Created by XXW on 2017/7/2.
 */

public class StillsActivity extends BaseActivity {
    public static final String MESSAGE = "message";
    @BindView(R.id.jp_video)
    JCVideoPlayerStandard mJpVideo;
    private Article mArticle;

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

    }

    private void initVideo() {
        if (mArticle != null) {
            mJpVideo.setUp(mArticle.getTrailers().get(0).getResource_url(), JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL);
            Glide.with(this).load(mArticle.getTrailers().get(0).getMedium()).into(mJpVideo.thumbImageView);
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
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

}
