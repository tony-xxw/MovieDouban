package mvp.wyyne.douban.moviedouban.detail;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mvp.wyyne.douban.moviedouban.adapter.viewpage.SubjectTitlePageAdapter;
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
    private String[] mTitle = {"评论", "讨论区"};

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
        SubjectTitlePageAdapter mAdapter = new SubjectTitlePageAdapter(mFragmentManager);
        mAdapter.setFragment(mHotList);
        mAdapter.setTitleList(Arrays.asList(mTitle));
        viewPager.setAdapter(mAdapter);
        mMain.onBindPage();
    }


}
