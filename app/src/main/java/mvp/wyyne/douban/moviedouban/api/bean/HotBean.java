package mvp.wyyne.douban.moviedouban.api.bean;

import java.util.List;

/**
 * Created by XXW on 2017/6/5.
 */

public class HotBean {

    /**
     * title : 正在上映的电影-北京
     * total : 39
     * start : 0
     * count : 20
     */

    private String title;
    private int total;
    private int start;
    private int count;

    public List<Subjects> getSubjectsList() {
        return subjects;
    }

    public void setSubjectsList(List<Subjects> subjectsList) {
        subjects = subjectsList;
    }

    private List<Subjects> subjects;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "HotBean{" +
                "title='" + title + '\'' +
                ", total=" + total +
                ", start=" + start +
                ", count=" + count +
                ", mSubjectsList=" + subjects +
                '}';
    }
}
