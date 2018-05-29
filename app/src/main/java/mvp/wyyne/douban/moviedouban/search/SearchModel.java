package mvp.wyyne.douban.moviedouban.search;

import android.util.Log;

import java.util.List;

import mvp.wyyne.douban.moviedouban.AndroidApplication;
import mvp.wyyne.douban.moviedouban.api.model.SearchModelBean;
import mvp.wyyne.douban.moviedouban.model.SearchModelBeanDao;

/**
 * 搜索页面Model层
 *
 * @author Wynne
 * @date 2018/5/28
 */

public class SearchModel {
    private String TAG = SearchModel.class.getSimpleName();
    private static SearchModel model = new SearchModel();

    public static SearchModel getInstance() {
        return model;
    }


    public void insertModel(SearchModelBean model) {
        SearchModelBeanDao dao = AndroidApplication.getDaoSession().getSearchModelBeanDao();
        try {
            dao.insert(model);
        } catch (Exception e) {
            Log.d("XXW", TAG + ":   增加失败");
        }
    }

    public void deleteModel() {
        SearchModelBeanDao dao = AndroidApplication.getDaoSession().getSearchModelBeanDao();
        try {
            dao.deleteAll();
        } catch (Exception e) {
            Log.d("XXW", TAG + ":   删除失败");
        }
    }

    public void updateModel(SearchModelBean modelDao) {
        SearchModelBeanDao dao = AndroidApplication.getDaoSession().getSearchModelBeanDao();
        if (queryModelListCount() == 4) {
            dao.deleteByKey((long) queryModelList().size());
            dao.insert(modelDao);
        }
    }

    public int queryModelListCount() {
        SearchModelBeanDao dao = AndroidApplication.getDaoSession().getSearchModelBeanDao();
        return dao.queryBuilder().list().size();
    }

    public List<SearchModelBean> queryModelList() {
        SearchModelBeanDao dao = AndroidApplication.getDaoSession().getSearchModelBeanDao();
        return dao.queryBuilder().list();
    }

}
