package mvp.wyyne.douban.moviedouban.movie;

import java.util.List;

import mvp.wyyne.douban.moviedouban.api.RetrofitService;
import mvp.wyyne.douban.moviedouban.api.bean.Subjects;
import mvp.wyyne.douban.moviedouban.api.bean.UsSubjects;
import mvp.wyyne.douban.moviedouban.api.bean.WeeklySubject;
import mvp.wyyne.douban.moviedouban.home.BaseObserver;

/**
 * @author Wynne
 * @date 2018/6/16
 */

public class MovieFragmentImp implements IMoviePresent {
    private IMovieMain movieMain;

    public MovieFragmentImp(IMovieMain movieMain) {
        this.movieMain = movieMain;
    }


    @Override
    public void getData() {

    }

    @Override
    public void getTopMovie() {
        RetrofitService.getTopMovie().subscribe(new BaseObserver<List<Subjects>>(movieMain) {
            @Override
            public void onSuccess(List<Subjects> response) {
                if (response.size() != 0) {
                    movieMain.notifyTopRefresh(response);
                }
            }
        });
    }

    @Override
    public void getWeekly() {
        RetrofitService.getWeekList().subscribe(new BaseObserver<List<WeeklySubject>>(movieMain) {
            @Override
            public void onSuccess(List<WeeklySubject> response) {
                if (response.size() != 0) {
                    movieMain.notifyWeeklyRefresh(response);
                }
            }
        });
    }

    @Override
    public void getNowMovies() {
        RetrofitService.getNowMoviesList().subscribe(new BaseObserver<List<Subjects>>(movieMain) {
            @Override
            public void onSuccess(List<Subjects> response) {
                if (response.size() != 0) {
                    movieMain.notifyNowRefresh(response);
                }
            }
        });
    }

    @Override
    public void getUsBox() {
        RetrofitService.getUsBoxList().subscribe(new BaseObserver<List<UsSubjects>>(movieMain) {
            @Override
            public void onSuccess(List<UsSubjects> response) {
                if (response.size() != 0) {
                    movieMain.notifyUsRefresh(response);
                }
            }
        });
    }
}
