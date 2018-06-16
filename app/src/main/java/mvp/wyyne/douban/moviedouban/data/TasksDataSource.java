package mvp.wyyne.douban.moviedouban.data;

import io.reactivex.Observable;
import mvp.wyyne.douban.moviedouban.api.bean.MovieSubject;

/**
 * @author Wynne
 * @date 2018/3/26
 */

public interface TasksDataSource {

    interface TaskLocalData {

    }

    interface TaskRemoteData {

        /**
         * 获取热映数据
         *
         * @return
         */
        Observable<MovieSubject> getHotList();

    }
}
