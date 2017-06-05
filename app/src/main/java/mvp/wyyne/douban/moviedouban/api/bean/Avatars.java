package mvp.wyyne.douban.moviedouban.api.bean;

/**
 * Created by XXW on 2017/6/5.
 */

public class Avatars {


    /**
     * small : https://img3.doubanio.com/img/celebrity/small/8710.jpg
     * large : https://img3.doubanio.com/img/celebrity/large/8710.jpg
     * medium : https://img3.doubanio.com/img/celebrity/medium/8710.jpg
     */

    private String small;
    private String large;
    private String medium;

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }
}
