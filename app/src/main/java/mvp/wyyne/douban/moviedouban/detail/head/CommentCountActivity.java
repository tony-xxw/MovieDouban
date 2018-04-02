package mvp.wyyne.douban.moviedouban.detail.head;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import butterknife.BindView;
import butterknife.OnClick;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.bean.Article;
import mvp.wyyne.douban.moviedouban.home.base.BaseActivity;
import mvp.wyyne.douban.moviedouban.utils.Constans;
import mvp.wyyne.douban.moviedouban.widget.ChartRect;

/**
 * 评价界面
 *
 * @author XXW
 * @date 2017/8/21
 */

public class CommentCountActivity extends BaseActivity {
    public static final String TAG = "CommentCountActivity";
    @BindView(R.id.iv_movie_bg)
    ImageView ivMovieBg;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_grade)
    TextView tvGrade;
    @BindView(R.id.tv_comment_count)
    TextView tvCommentCount;
    @BindView(R.id.cr_chart)
    ChartRect crChart;
    private Article mArticle;

    private GestureDetector mGesture;

    @Override
    protected void refresh() {

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (getIntent().getBundleExtra(Constans.DATA_ARTICLE) != null) {
            mArticle = getIntent().getBundleExtra(Constans.DATA_ARTICLE).getParcelable(Constans.DATA_ARTICLE);
        }
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_comment;
    }

    @Override
    protected void initView() {
        if (mArticle != null) {
            tvTitle.setText(mArticle.getTitle());
            String url = mArticle.getPhotos().get(0).getImage();
            Log.d(TAG, "url :" + url);
            Glide.with(CommentCountActivity.this).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).into(ivMovieBg);
            tvGrade.setText(String.valueOf(mArticle.getRating().getAverage()));
            String commonCount = mArticle.getRatings_count() + "人评分";
            tvCommentCount.setText(commonCount);
        }

        mGesture = new GestureDetector(this, onGestureListener);
        crChart.setStarDetail(mArticle.getRating().getDetails(), mArticle.getRatings_count());

    }


    @OnClick({R.id.iv_back})
    public void finishActivity() {
        finish();
    }


    private GestureDetector.OnGestureListener onGestureListener = new GestureDetector.SimpleOnGestureListener() {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float x = e2.getX() - e1.getX();
            float y = e2.getY() - e2.getY();


            Log.d(TAG, "scroll X:" + x);
            Log.d(TAG, "scroll Y:" + y);

            return super.onFling(e1, e2, velocityX, velocityY);
        }
    };


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return mGesture.onTouchEvent(event);
    }
}
