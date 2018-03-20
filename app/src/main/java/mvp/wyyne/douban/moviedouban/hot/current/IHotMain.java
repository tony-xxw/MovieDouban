package mvp.wyyne.douban.moviedouban.hot.current;

import java.util.List;

import mvp.wyyne.douban.moviedouban.api.bean.Subjects;
import mvp.wyyne.douban.moviedouban.home.IMain;

/**
 * @author XXW
 * @date 2017/6/12
 */

public interface IHotMain extends IMain {
    /**
     * 初始化数据
     *
     * @param subjects 电影Bean
     */
    void initData(List<Subjects> subjects);
}
