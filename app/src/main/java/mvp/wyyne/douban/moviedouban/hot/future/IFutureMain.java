package mvp.wyyne.douban.moviedouban.hot.future;

import java.util.List;

import mvp.wyyne.douban.moviedouban.api.bean.Subjects;
import mvp.wyyne.douban.moviedouban.home.IMain;

/**
 * Created by XXW on 2017/6/13.
 */

public interface IFutureMain extends IMain {
    void initData(List<Subjects> subjectses);

    void bindData();
}
