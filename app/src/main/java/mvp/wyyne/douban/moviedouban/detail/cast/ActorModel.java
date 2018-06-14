package mvp.wyyne.douban.moviedouban.detail.cast;

import android.util.Log;

import java.util.List;

import mvp.wyyne.douban.moviedouban.AndroidApplication;
import mvp.wyyne.douban.moviedouban.api.model.ActorCollectTable;
import mvp.wyyne.douban.moviedouban.model.ActorCollectTableDao;

/**
 * 演员表操作类
 *
 * @author Wynne
 * @date 2018/6/14
 */

public class ActorModel {
    private static ActorModel model = new ActorModel();

    public static ActorModel getInstance() {
        return model;
    }

    public List<ActorCollectTable> queryModelList() {
        ActorCollectTableDao collectTableDao = AndroidApplication.getDaoSession().getActorCollectTableDao();
        return collectTableDao.queryBuilder().list();
    }

    public void insertModel(ActorCollectTable model) {
        ActorCollectTableDao collectTableDao = AndroidApplication.getDaoSession().getActorCollectTableDao();
        try {
            collectTableDao.insert(model);
        } catch (Exception e) {
            Log.d("XXW", "增加失败 : " + e.toString());
        }
    }

}
