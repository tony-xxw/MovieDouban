package mvp.wyyne.douban.moviedouban.search;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.RetrofitService;
import mvp.wyyne.douban.moviedouban.api.bean.MovieSubject;
import mvp.wyyne.douban.moviedouban.api.bean.HotSearch;
import mvp.wyyne.douban.moviedouban.api.bean.Subjects;
import mvp.wyyne.douban.moviedouban.api.model.SearchModel;
import mvp.wyyne.douban.moviedouban.api.model.SearchModelTable;
import mvp.wyyne.douban.moviedouban.home.BaseObserver;

/**
 * @author Wynne
 * @date 2018/5/27
 */

public class SearchMovieImp implements ISearchMoviePresent {
    private Context mContext;
    private Activity mActivity;
    private List<Subjects> mList;
    private List<HotSearch> mHotList = new ArrayList<>();
    private int[] tabList = {R.drawable.ic_hot_1, R.drawable.ic_hot_2, R.drawable.ic_hot_3,
            R.drawable.ic_hot_4, R.drawable.ic_hot_5, R.drawable.ic_hot_6, R.drawable.ic_hot_7
            , R.drawable.ic_hot_8, R.drawable.ic_hot_9, R.drawable.ic_hot_10};
    private ISearchMovieMain movieMain;
    private List<Subjects> mSearchList;

    public SearchMovieImp(Context context, ISearchMovieMain main) {
        mContext = context;
        movieMain = main;
        mActivity = (SearchMovieActivity) context;
        mHotList = new ArrayList<>();
    }


    @Override
    public void getData() {

    }

    @Override
    public List<HotSearch> getSubjectsList() {
        if (mHotList.size() == 0) {
            if (mActivity.getIntent().getParcelableArrayListExtra(SearchMovieActivity.TAG) != null) {
                mList = mActivity.getIntent().getParcelableArrayListExtra(SearchMovieActivity.TAG);
                for (int i = 0; i < 10; i++) {
                    mHotList.add(new HotSearch(mList.get(i), tabList[i]));
                }

            }
        }
        return mHotList;
    }

    @Override
    public void searchMovieSubject(String text, String start, String count) {
        RetrofitService.searchMovieSubject(text, start, count)
                .subscribe(new BaseObserver<MovieSubject>(movieMain) {
                    @Override
                    public void onSuccess(MovieSubject response) {
                        mSearchList = response.getSubjectsList();
                        movieMain.setSubject(mSearchList);
                        Log.d("XXW", " mSearchList   size :" + mSearchList.size());
                    }
                });
    }

    @Override
    public List<SearchModelTable> getSearchBeanList() {
        return SearchModel.getInstance().queryModelList();
    }

    @Override
    public int getSearchBeanLisCount() {
        return SearchModel.getInstance().queryModelListCount();
    }

    @Override
    public void insertSearchBean(SearchModelTable bean) {
        SearchModel.getInstance().insertModel(bean);
    }

    @Override
    public void clearSearchBean() {
        SearchModel.getInstance().deleteModel();
    }

    @Override
    public void updateSearchLast(SearchModelTable bean) {
        SearchModel.getInstance().updateModel(bean);
    }

    @Override
    public SearchModelTable createSearchModelBean(String title, String id) {
        SearchModelTable searchModelBean = new SearchModelTable();
        searchModelBean.setMovieId(id);
        searchModelBean.setName(title);
        return searchModelBean;
    }
}
