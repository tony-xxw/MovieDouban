package mvp.wyyne.douban.moviedouban.down;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import mvp.wyyne.douban.moviedouban.utils.SdCardUtils;

/**
 * 下载图片
 *
 * @author Wynne
 * @date 2018/5/14
 */

public class DownPhotoService implements Runnable {


    /**
     * 图片链接地址
     */
    private String imageUrl;
    /**
     * 当前文件
     */
    private File currentFile;

    private Context mContext;
    /**
     * 下载回调
     */
    private DownloadCallBack downListening;

    public DownPhotoService(String imageUrl, Context mContext, DownloadCallBack downListening) {
        this.imageUrl = imageUrl;
        this.mContext = mContext;
        this.downListening = downListening;
    }


    @Override
    public void run() {
        Bitmap bitmap = null;

        try {
            bitmap = Glide.with(mContext)
                    .load(imageUrl)
                    .asBitmap()
                    .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get();
            if (bitmap != null) {
                saveImageToGallery(mContext, bitmap);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            if (bitmap != null && currentFile.exists()) {
                downListening.onDownLoadSuccess(bitmap, currentFile.getPath());
            } else {
                downListening.onDownLoadFailed();
            }
        }
    }

    public void saveImageToGallery(Context context, Bitmap bitmap) {
        File localFile = SdCardUtils.getPicturesFilePath();
        String fileName = "DouBan";
        File appDir = new File(localFile, fileName);
        if (!appDir.exists()) {
            boolean isExists = appDir.mkdirs();
            Log.d("XXW", "isExists = " + isExists);
        }

        String currentName = System.currentTimeMillis() + ".jpg";
        currentFile = new File(appDir, currentName);

        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(currentFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        Log.d("XXW", "==下载路径==" + currentFile.getPath());

        /**
         * 通知相册刷新
         */
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                Uri.fromFile(new File(currentFile.getPath()))));
    }


}
