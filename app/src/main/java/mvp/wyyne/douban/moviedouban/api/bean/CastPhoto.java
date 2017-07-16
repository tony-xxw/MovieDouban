package mvp.wyyne.douban.moviedouban.api.bean;

import java.util.List;

/**
 * Created by XXW on 2017/7/15.
 */

public class CastPhoto {
    public Celebrity getCelebrity() {
        return celebrity;
    }

    public void setCelebrity(Celebrity celebrity) {
        this.celebrity = celebrity;
    }

    public List<StillsPhotos> getPhotos() {
        return photos;
    }

    public void setPhotos(List<StillsPhotos> photos) {
        this.photos = photos;
    }

    Celebrity celebrity;
    List<StillsPhotos> photos;

    /**
     * count : 20
     * total : 758
     * start : 0
     */

    private int count;
    private int total;
    private int start;

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
