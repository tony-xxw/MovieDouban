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
import mvp.wyyne.douban.moviedouban.discuss.DiscussFragment;
import mvp.wyyne.douban.moviedouban.home.BaseObserver;

/**
 * @author XXW
 * @date 2017/6/19
 */

public class DetailMovieImp implements IDetailPresent {
    private IDetailMain mMain;
    private FragmentManager mFragmentManager;
    private List<Fragment> mHotList;
    private Article mArticle;


    public DetailMovieImp(IDetailMain main, FragmentManager manager) {
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
                .subscribe(new BaseObserver<Article>(mMain) {
                    @Override
                    public void onSuccess(Article article) {
                        mArticle = article;
                        mMain.initMovieImg(article);
                    }
                });
    }

    @Override
    public void initPage(ViewPager viewPager) {
        mHotList.add(CommentFragment.getInstance(mArticle));
        mHotList.add(DiscussFragment.getInstance());
        DetailPagerAdapter mAdapter = new DetailPagerAdapter(mFragmentManager);
        mAdapter.setFragment(mHotList);
        viewPager.setAdapter(mAdapter);
        mMain.onBindPage();
    }


    class DetailPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> mList;
        private String[] mTitle = {"评论", "讨论区"};

        private DetailPagerAdapter(FragmentManager fm) {
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
            return mTitle[position];
        }
    }
}
