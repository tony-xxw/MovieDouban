package mvp.wyyne.douban.moviedouban.hot.current;

import java.util.List;

import mvp.wyyne.douban.moviedouban.api.bean.Subjects;
import mvp.wyyne.douban.moviedouban.home.IMain;

/**
 * Created by XXW on 2017/6/12.
 */

public interface ICurrentMain extends IMain {
    void initData(List<Subjects> subjects);
}
