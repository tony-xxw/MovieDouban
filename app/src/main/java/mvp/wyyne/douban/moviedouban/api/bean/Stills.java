package mvp.wyyne.douban.moviedouban.api.bean;

import java.util.List;

/**
 * 剧照Bean
 * @author XXW
 * @date 2017/7/2
 */

public class Stills {

    /**
     * count : 20
     * total : 50
     * start : 0
     */

    private int count;
    private int total;
    private int start;

    public Subjects getSubject() {
        return subject;
    }

    public void setSubject(Subjects subject) {
        this.subject = subject;
    }


    private Subjects subject;

    public List<StillsPhotos> getPhotos() {
        return photos;
    }

    public void setPhotos(List<StillsPhotos> photos) {
        this.photos = photos;
    }

    private List<StillsPhotos> photos;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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
}
