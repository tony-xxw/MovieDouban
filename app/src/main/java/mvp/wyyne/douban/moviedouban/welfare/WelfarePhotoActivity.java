package mvp.wyyne.douban.moviedouban.welfare;

import android.app.AlertDialog;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.home.base.BaseActivity;
import mvp.wyyne.douban.moviedouban.utils.StatusUtils;

/**
 * 妹子图 相片管理器
 *
 * @author Wynne
 * @date 2018/5/31
 */

public class WelfarePhotoActivity extends BaseActivity implements View.OnLongClickListener {
    public static final String TAG = WelfarePhotoActivity.class.getSimpleName();
    @BindView(R.id.iv_girl)
    ImageView ivGirl;
    private String mPhotoUrl;
    private View dialogView;
    private AlertDialog alertDialog;

    @Override
    protected void refresh() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welfare_layout;
    }

    @Override
    protected void initView() {
        StatusUtils.setStatusBarActivity(this, true, Color.TRANSPARENT);
        if (getIntent() != null) {
            mPhotoUrl = getIntent().getStringExtra(TAG);
        }

        if (!TextUtils.isEmpty(mPhotoUrl)) {
            Glide.with(this).load(mPhotoUrl).into(ivGirl);
        }

        ivGirl.setOnLongClickListener(this);
        dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_list_welfare_photo, null);

        initDialog();
    }

    private void initDialog() {
        alertDialog = new AlertDialog.Builder(this)
                .setView(dialogView)
                .create();
        WindowManager windowManager = getWindowManager();
        WindowManager.LayoutParams layoutParams = alertDialog.getWindow().getAttributes();
        layoutParams.width = (int) (windowManager.getDefaultDisplay().getWidth() * 0.4);
        alertDialog.getWindow().setAttributes(layoutParams);

    }

    @Override
    public boolean onLongClick(View v) {
        alertDialog.show();
        return false;
    }
}
