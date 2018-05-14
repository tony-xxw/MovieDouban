package mvp.wyyne.douban.moviedouban.down;

import android.graphics.Bitmap;

import java.io.File;

/**
 * 下载回调
 *
 * @author Wynne
 * @date 2018/5/14
 */

public interface DownloadCallBack {

    /**
     * 下载成功
     *
     * @param file 文件
     * @param path 文件路径
     */
    void onDownLoadSuccess(File file, String path);

    /**
     * 下载成功
     *
     * @param bitmap bitmap
     * @param path   文件路径
     */
    void onDownLoadSuccess(Bitmap bitmap, String path);


    /**
     * 下载失败
     */
    void onDownLoadFailed();
}
