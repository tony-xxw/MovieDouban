package mvp.wyyne.douban.moviedouban.detail;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import mvp.wyyne.douban.moviedouban.api.RetrofitService;
import mvp.wyyne.douban.moviedouban.api.bean.Article;
import mvp.wyyne.douban.moviedouban.comment.CommentFragment;
import mvp.wyyne.douban.moviedouban.movie.MovieFragment;

/**
 * Created by XXW on 2017/6/19.
 */

public class DetailMoviePresent implements IDetailPresent {
    private IDetailMain mMain;
    private List<Fragment> mHotList;
    private FragmentManager mFragmentManager;
    private DetailPagerAdapter mAdapter;
    private Article mArticle;



    public DetailMoviePresent(IDetailMain main, FragmentManager manager) {
        mMain = main;
        mFragmentManager = manager;
        mHotList = new ArrayList<>();
    }


    @Override
    public void getData() {

    }


    @Override
    public void getArticle(String subjectId) {
        RetrofitService.getArticle(subjectId)
                .subscribe(new Observer<Article>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mMain.show();
                    }

                    @Override
                    public void onNext(@NonNull Article article) {
                        mArticle = article;
                        mMain.initMovieImg(article);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("XXW", e.toString());
                    }

                    @Override
                    public void onComplete() {
                        mMain.initMovieGrade();
                        mMain.hide();

                    }
                });

    }

    @Override
    public void initPage(ViewPager viewPager) {
        mHotList.add(CommentFragment.getInstance(mArticle));
        mHotList.add(new MovieFragment());
        mAdapter = new DetailPagerAdapter(mFragmentManager);
        mAdapter.setFragment(mHotList);
        viewPager.setAdapter(mAdapter);
        mMain.onBindPage();
    }



    class DetailPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> mList;
        private String[] mTitl = {"评论", "讨论区"};

        public DetailPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        private void setFragment(List<Fragment> list) {
            mList = list;
        }

        @Override
        public Fragment getItem(int position) {
            return mList.get(position);
        }

        @Override
        public int getCount() {
            return mList.size();
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return mTitl[position];
        }
    }
}
