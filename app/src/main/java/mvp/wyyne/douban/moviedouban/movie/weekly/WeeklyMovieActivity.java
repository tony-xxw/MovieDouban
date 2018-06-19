package mvp.wyyne.douban.moviedouban.movie.weekly;

import java.util.List;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.RetrofitService;
import mvp.wyyne.douban.moviedouban.api.bean.WeeklySubject;
import mvp.wyyne.douban.moviedouban.home.BaseObserver;
import mvp.wyyne.douban.moviedouban.home.IMain;
import mvp.wyyne.douban.moviedouban.home.base.BaseActivity;

/**
 * @author Wynne
 * @date 2018/6/19
 */

public class WeeklyMovieActivity extends BaseActivity implements IMain {
    public static final String TAG = WeeklyMovieActivity.class.getSimpleName();
    private List<WeeklySubject> mList;

    @Override
    protected void refresh() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_weekly_movie;
    }

    @Override
    protected void initView() {
        RetrofitService.getWeekList().subscribe(new BaseObserver<List<WeeklySubject>>(this) {
            @Override
            public void onSuccess(List<WeeklySubject> response) {
                mList = response;
            }
        });
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }
}
