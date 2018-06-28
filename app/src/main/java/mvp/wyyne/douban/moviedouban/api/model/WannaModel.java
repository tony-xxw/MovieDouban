package mvp.wyyne.douban.moviedouban.api.model;

import android.util.Log;

import java.util.List;

import mvp.wyyne.douban.moviedouban.AndroidApplication;
import mvp.wyyne.douban.moviedouban.model.WannaTableDao;

/**
 * @author Wynne
 * @date 2018/6/20
 */

public class WannaModel {

    private static WannaModel mModel = new WannaModel();

    public static WannaModel getInstance() {
        return mModel;
    }

    public void insertModel(WannaTable model) {
        WannaTableDao dao = AndroidApplication.getDaoSession().getWannaTableDao();
        try {
            dao.insert(model);
        } catch (Exception e) {
            Log.d("XXW", "增加失败 : " + e.toString());
        }
    }

    public void deleteModel(Long key) {
        WannaTableDao dao = AndroidApplication.getDaoSession().getWannaTableDao();
        try {
            dao.deleteByKey(key);
        } catch (Exception e) {
            Log.d("XXW", "删除失败 : " + e.toString());
        }
    }


    public void updateModel(WannaTable modelDao) {
        WannaTableDao dao = AndroidApplication.getDaoSession().getWannaTableDao();
        if (queryModelListCount() >= 4) {
            dao.delete(queryModelList().get(0));
            dao.insert(modelDao);
        }
    }

    public int queryModelListCount() {
        WannaTableDao dao = AndroidApplication.getDaoSession().getWannaTableDao();
        return dao.queryBuilder().list().size();
    }

    public List<WannaTable> queryModelList() {
        WannaTableDao dao = AndroidApplication.getDaoSession().getWannaTableDao();
        return dao.queryBuilder().list();
    }

    public boolean queryRecord(String title) {
        WannaTableDao dao = AndroidApplication.getDaoSession().getWannaTableDao();
        List<WannaTable> tables = dao.queryBuilder().where(WannaTableDao.Properties.Title.eq(title)).list();
        if (tables.size() != 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean queryMark(String title) {
        boolean isMark = false;
        WannaTableDao dao = AndroidApplication.getDaoSession().getWannaTableDao();
        List<WannaTable> list = dao.queryBuilder().list();
        for (WannaTable table : list) {
            if (table.getTitle().equals(title)) {
                isMark = table.getIsLabel();
                break;
            }
        }
        return isMark;
    }
}
