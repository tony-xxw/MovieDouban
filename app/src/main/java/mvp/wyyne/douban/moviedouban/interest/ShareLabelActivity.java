package mvp.wyyne.douban.moviedouban.interest;


import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.bean.Article;
import mvp.wyyne.douban.moviedouban.api.bean.Casts;
import mvp.wyyne.douban.moviedouban.api.model.WannaModel;
import mvp.wyyne.douban.moviedouban.home.base.BaseActivity;
import mvp.wyyne.douban.moviedouban.utils.FastBlur;
import mvp.wyyne.douban.moviedouban.utils.ResourcesUtils;
import mvp.wyyne.douban.moviedouban.utils.StatusUtils;
import mvp.wyyne.douban.moviedouban.widget.StarView;

/**
 * 分享电影标签
 *
 * @author Wynne
 * @date 2018/6/28
 */

public class ShareLabelActivity extends BaseActivity {
    public static final String TAG = ShareLabelActivity.class.getSimpleName();
    @BindView(R.id.iv_close_white)
    ImageView ivCloseWhite;
    @BindView(R.id.tv_title_center)
    TextView tvTitleCenter;
    @BindView(R.id.rl_content)
    RelativeLayout rlContent;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_movie)
    ImageView ivMovie;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_directors_name)
    TextView tvDirectorsName;
    @BindView(R.id.tv_casts_list)
    TextView tvCastsList;
    @BindView(R.id.tv_detail_grade)
    TextView tvDetailGrade;
    @BindView(R.id.sv_grade)
    StarView svGrade;
    @BindView(R.id.tv_detail_num)
    TextView tvDetailNum;
    @BindView(R.id.tv_reason)
    TextView tvReason;
    @BindView(R.id.cv_comment)
    CardView rvComment;
    @BindView(R.id.iv_blur)
    ImageView ivBlur;
    @BindView(R.id.bottom_line)
    View topLine;
    private Article mArticle;
    private String mReason;

    @Override
    protected void refresh() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_share_label;
    }


    @OnClick({R.id.iv_back})
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    protected void initView() {
        StatusUtils.setStatusImage(this, R.color.transparent, true);
        ivCloseWhite.setVisibility(View.VISIBLE);
        ivBack.setVisibility(View.GONE);
        topLine.setVisibility(View.GONE);
        rlContent.setBackgroundColor(ResourcesUtils.getColor(R.color.transparent, this));
        tvTitleCenter.setText("标记分享");


        if (getIntent().getBundleExtra(TAG) != null) {
            mArticle = getIntent().getBundleExtra(TAG).getParcelable(TAG);
        }

        if (getIntent().getStringExtra(TAG) != null) {
            mReason = getIntent().getStringExtra(TAG);
        }

        if (mArticle != null) {
            handleBlue();
            tvTitle.setText(mArticle.getTitle());
            tvDirectorsName.setText(mArticle.getDirectors().get(0).getName());
            int castSize = mArticle.getCasts().size();
            List<Casts> mCastsList = mArticle.getCasts();
            StringBuffer mStringBuffer = new StringBuffer();
            for (int i = 0; i < castSize; i++) {
                if (i == castSize - 1) {
                    mStringBuffer.append(mCastsList.get(i).getName());
                } else if (i == 0) {
                    if (mStringBuffer.length() > 15) {
                        mStringBuffer.append("主演: " + mCastsList.get(i).getName() + "/" + "'\n'");
                    } else {
                        mStringBuffer.append("主演: " + mCastsList.get(i).getName() + "/");
                    }
                } else {
                    mStringBuffer.append(mCastsList.get(i).getName() + "/");
                }
            }

            tvCastsList.setText(mStringBuffer.toString());
            String average = mArticle.getRating().getAverage() + "";
            tvDetailGrade.setText(average);
            svGrade.setStartMark((int) mArticle.getRating().getAverage());
            String collectName = mArticle.getCollect_count() + "人";
            tvDetailNum.setText(collectName);
            String reason = WannaModel.getInstance().queryReason(mArticle.getTitle());
            if (!TextUtils.isEmpty(reason)) {
                tvReason.setVisibility(View.VISIBLE);
                tvReason.setText(reason);
            }

            rvComment.setBackground(ResourcesUtils.getDrawable(R.drawable.bg_transparency_white, this));
        }

        if (!TextUtils.isEmpty(mReason)) {
            tvReason.setText(mReason);
        }

    }


    /**
     * 高清模糊
     */
    private void handleBlue() {

        Glide.with(this).load(mArticle.getImages().getMedium()).into(ivMovie);
        Glide.with(this).
                load(mArticle.getImages().getLarge()).
                asBitmap().
                transform(new FastBlur(this, 100)).
                into(ivBlur);

    }


}
