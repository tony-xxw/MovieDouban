package mvp.wyyne.douban.moviedouban.api.model;

import android.util.Log;

import java.util.List;

import mvp.wyyne.douban.moviedouban.AndroidApplication;
import mvp.wyyne.douban.moviedouban.model.SearchModelTableDao;

/**
 * 搜索页面Model层
 *
 * @author Wynne
 * @date 2018/5/28
 */

public class SearchModel {
    private String TAG = SearchModel.class.getSimpleName();
    private static SearchModel mModel = new SearchModel();

    public static SearchModel getInstance() {
        return mModel;
    }


    public void insertModel(SearchModelTable model) {
        SearchModelTableDao dao = AndroidApplication.getDaoSession().getSearchModelTableDao();
        try {
            dao.insert(model);
        } catch (Exception e) {
            Log.d("XXW", "增加失败 : " + e.toString());
        }
    }

    public void deleteModel() {
        SearchModelTableDao dao = AndroidApplication.getDaoSession().getSearchModelTableDao();
        try {
            dao.deleteAll();
        } catch (Exception e) {
            Log.d("XXW", "删除失败 : " + e.toString());
        }
    }

    public void updateModel(SearchModelTable modelDao) {
        SearchModelTableDao dao = AndroidApplication.getDaoSession().getSearchModelTableDao();
        if (queryModelListCount() >= 4) {
            dao.delete(queryModelList().get(0));
            dao.insert(modelDao);
        }
    }

    public int queryModelListCount() {
        SearchModelTableDao dao = AndroidApplication.getDaoSession().getSearchModelTableDao();
        return dao.queryBuilder().list().size();
    }

    public List<SearchModelTable> queryModelList() {
        SearchModelTableDao dao = AndroidApplication.getDaoSession().getSearchModelTableDao();
        return dao.queryBuilder().list();
    }

}
