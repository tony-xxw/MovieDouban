package mvp.wyyne.douban.moviedouban.detail.comment;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import mvp.wyyne.douban.moviedouban.api.RetrofitService;
import mvp.wyyne.douban.moviedouban.api.bean.MoviesReviews;
import mvp.wyyne.douban.moviedouban.api.bean.Reviews;
import mvp.wyyne.douban.moviedouban.detail.comment.photo.IPhotoCommentMain;
import mvp.wyyne.douban.moviedouban.detail.comment.photo.IPhotoCommentPresent;

/**
 * @author Wynne
 * @date 2018/5/8
 */

public class PhotoCommentImp implements IPhotoCommentPresent {
    private IPhotoCommentMain mMain;
    private String mId;
    private List<Reviews> mList;

    public PhotoCommentImp(IPhotoCommentMain main, String id) {
        mId = id;
        mMain = main;
    }


    @Override
    public void getData() {
        RetrofitService.getReviews(mId).subscribe(new Observer<MoviesReviews>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MoviesReviews moviesReviews) {
                mList = moviesReviews.getReviews();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                mMain.noticeAdapter(mList);
            }
        });
    }
}
