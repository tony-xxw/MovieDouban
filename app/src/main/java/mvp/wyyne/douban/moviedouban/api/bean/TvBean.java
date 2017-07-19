package mvp.wyyne.douban.moviedouban.api.bean;

/**
 * Created by XXW on 2017/7/19.
 */

public class TvBean {

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTvName() {
        return tvName;
    }

    public void setTvName(String tvName) {
        this.tvName = tvName;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }


    public int getUrl() {
        return url;
    }

    public void setUrl(int url) {
        this.url = url;
    }

    public TvBean(String avatar, String tvName, String money) {
        this.avatar = avatar;
        this.tvName = tvName;
        this.money = money;
    }

    public TvBean(int url, String tvName, String money) {
        this.url = url;
        this.tvName = tvName;
        this.money = money;
    }

    private int url;
    public String avatar;
    public String tvName;
    public String money;
}
