package mvp.wyyne.douban.moviedouban.detail.photo;

import android.content.Context;
import android.graphics.Bitmap;

import java.io.File;
import java.util.List;

import mvp.wyyne.douban.moviedouban.api.RetrofitService;
import mvp.wyyne.douban.moviedouban.api.bean.Stills;
import mvp.wyyne.douban.moviedouban.api.bean.StillsPhotos;
import mvp.wyyne.douban.moviedouban.down.DownPhotoService;
import mvp.wyyne.douban.moviedouban.down.DownloadCallBack;
import mvp.wyyne.douban.moviedouban.home.BaseObserver;

/**
 * @author XXW
 * @date 2017/7/3
 */

public class IPhotoImp implements IPhotoPresent {

    private IPhotoMain mMain;
    private List<StillsPhotos> mList;
    private Context mContext;

    public IPhotoImp(IPhotoMain main, Context context) {
        mMain = main;
        mContext = context;
    }


    @Override
    public void getData() {

    }

    @Override
    public void getPhoto(String subjectId) {

        RetrofitService.getStillsPhotos(subjectId)
                .subscribe(new BaseObserver<Stills>(mMain) {
                    @Override
                    public void onSuccess(Stills stills) {
                        if (stills.getPhotos() != null) {
                            mList = stills.getPhotos();
                            mMain.showPage(stills);
                        }
                    }
                });
    }

    @Override
    public void downloadToLocal(int position) {

        String imageUrl = mList.get(position).getImage();
        DownPhotoService downService = new DownPhotoService(imageUrl, mContext, new DownloadCallBack() {
            @Override
            public void onDownLoadSuccess(File file, String path) {
                String toastMsg = "图片已经保存到 :" + path;
                mMain.showToast(toastMsg);
            }

            @Override
            public void onDownLoadSuccess(Bitmap bitmap, String path) {
                String toastMsg = "图片已经保存到 :" + path;
                mMain.showToast(toastMsg);
            }

            @Override
            public void onDownLoadFailed() {
                String toastMsg = "下载失败";
                mMain.showToast(toastMsg);
            }
        });

        new Thread(downService).start();
    }


}
