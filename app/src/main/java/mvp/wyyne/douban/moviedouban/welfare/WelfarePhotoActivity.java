package mvp.wyyne.douban.moviedouban.welfare;

import android.app.AlertDialog;
import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.BindView;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.down.DownPhotoService;
import mvp.wyyne.douban.moviedouban.down.DownloadCallBack;
import mvp.wyyne.douban.moviedouban.home.base.BaseActivity;
import mvp.wyyne.douban.moviedouban.utils.DialogUtils;
import mvp.wyyne.douban.moviedouban.utils.StatusUtils;
import mvp.wyyne.douban.moviedouban.utils.ToastUtils;

/**
 * 妹子图 相片管理器
 *
 * @author Wynne
 * @date 2018/5/31
 */

public class WelfarePhotoActivity extends BaseActivity implements View.OnLongClickListener, View.OnClickListener {
    public static final String TAG = WelfarePhotoActivity.class.getSimpleName();
    @BindView(R.id.iv_girl)
    ImageView ivGirl;
    public static final int SUCCESS = 0;


    private String mPhotoUrl;
    private View dialogView;
    private AlertDialog alertDialog;
    private MyHandle myHandle = new MyHandle();

    @Override
    protected void refresh() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welfare_layout;
    }

    @Override
    protected void initView() {
        StatusUtils.setStatusImage(this, true);
        if (getIntent() != null) {
            mPhotoUrl = getIntent().getStringExtra(TAG);
            Log.d("XXW", "mPhotoUrl  :" + mPhotoUrl);
        }

        if (!TextUtils.isEmpty(mPhotoUrl)) {
            Glide.with(this).load(mPhotoUrl).into(ivGirl);
        }

        ivGirl.setOnLongClickListener(this);
        dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_welfare_list_photo, null);
        dialogView.findViewById(R.id.tv_wallpaper).setOnClickListener(this);
        dialogView.findViewById(R.id.tv_save).setOnClickListener(this);

        initDialog();
    }

    private void initDialog() {
        alertDialog = new AlertDialog.Builder(this)
                .setView(dialogView)
                .create();

    }


    @Override
    public boolean onLongClick(View v) {
        alertDialog.show();
        DialogUtils.setDialogWidth(alertDialog, this, 0.75);
        return false;
    }


    public void setWallpaper() {
        if (!TextUtils.isEmpty(mPhotoUrl)) {
            final WallpaperManager paperManager = WallpaperManager.getInstance(this);
            Glide.with(this).load(mPhotoUrl).asBitmap().into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    try {
                        paperManager.setBitmap(resource);
                    } catch (IOException e) {
                        Log.d("XXW", "设置壁纸失败: " + e.toString());
                    }
                }
            });
        }
    }

    public void downLoadImage() {
        if (!TextUtils.isEmpty(mPhotoUrl)) {
            DownPhotoService downService = new DownPhotoService(mPhotoUrl, this, new DownloadCallBack() {
                @Override
                public void onDownLoadSuccess(File file, String path) {
                    String toastMsg = "图片已经保存到 :" + path;
                    showToast(toastMsg);
                }

                @Override
                public void onDownLoadSuccess(Bitmap bitmap, String path) {
                    String toastMsg = "图片已经保存到 :" + path;
                    Message msg = myHandle.obtainMessage();
                    msg.obj = toastMsg;
                    msg.what = SUCCESS;
                    myHandle.sendMessage(msg);
                }

                @Override
                public void onDownLoadFailed() {
                    String toastMsg = "下载失败";
                    showToast(toastMsg);
                }
            });

            ExecutorService executors = Executors.newCachedThreadPool();
            executors.execute(downService);
        }
    }

    public class MyHandle extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SUCCESS:
                    String toastMsg = (String) msg.obj;
                    showToast(toastMsg);
                    break;
                default:
                    break;
            }
        }
    }

    public void showToast(String toastString) {
        View inflater = LayoutInflater.from(this).inflate(R.layout.toast_login, null);
        TextView textView = (TextView) inflater.findViewById(R.id.tv_text);
        textView.setText(toastString);
        LinearLayout llShape = (LinearLayout) inflater.findViewById(R.id.ll_shape);
        llShape.setBackground(getResources().getDrawable(R.drawable.bg_green));
        ToastUtils.getInstance(getApplicationContext()).makeToastSelfViewAnim(inflater, R.style.ToastStyle);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_wallpaper:
                setWallpaper();
                alertDialog.dismiss();
                break;
            case R.id.tv_save:
                downLoadImage();
                alertDialog.dismiss();
                break;
            default:
                break;
        }
    }


}
