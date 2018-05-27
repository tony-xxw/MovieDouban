package mvp.wyyne.douban.moviedouban.api.bean;

/**
 * @author Wynne
 * @date 2018/5/27
 */

public class HotSearch {
    /**
     * 电影条目
     */
    private Subjects subjects;
    /**
     * 图片标签
     */
    private int tabImg;

    public Subjects getSubjects() {
        return subjects;
    }

    public int getTabImg() {
        return tabImg;
    }


    public HotSearch(Subjects subjects, int tabImg) {
        this.subjects = subjects;
        this.tabImg = tabImg;
    }
}
