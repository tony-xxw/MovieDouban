package mvp.wyyne.douban.moviedouban.detail;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.bean.Article;
import mvp.wyyne.douban.moviedouban.home.BaseActivity;
import mvp.wyyne.douban.moviedouban.utils.BitMapUtils;

/**
 * Created by XXW on 2017/6/18.
 */

public class DetailMovieActivity extends BaseActivity<DetailMoviePresent> implements IDetailMain {
    public static final String DETAIL_TAG = "detail";
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.iv_share)
    ImageView mIvShare;
    @BindView(R.id.ll_title)
    RelativeLayout mLlTitle;
    private String mSubjectsId;
    private Bitmap mDrawableBitmap;


    @Override
    protected void refresh() {

    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_movie_detail;
    }

    @Override
    protected void initView() {
        if (getIntent().getStringExtra(DETAIL_TAG) != null) {
            mSubjectsId = getIntent().getStringExtra(DETAIL_TAG);
            Log.d("XXW", "mList------->" + mSubjectsId);
        }
        mPresent = new DetailMoviePresent(this);
        mPresent.getArticle(mSubjectsId);
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void initMovieImg(Article article) {
        mDrawableBitmap = BitMapUtils.getInstace(this).getNetWorkBitmap(article.getImages().getMedium());


    }
}
