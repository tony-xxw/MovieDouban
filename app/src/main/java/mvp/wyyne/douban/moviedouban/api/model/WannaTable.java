package mvp.wyyne.douban.moviedouban.api.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 *
 * @author Wynne
 * @date 2018/6/20
 */

@Entity
public class WannaTable {
    @Id(autoincrement = true)
    private Long Id;
    /**
     * 封面URL
     */
    private String avatarUrl;
    /**
     * 电影名
     */
    private String title;
    /**
     * 导演
     */
    private String directors;
    /**
     * 演员
     */
    private String casts;
    /**
     * 创建时间
     */
    private String createtime;
    /**
     * 评分
     */
    private String average;
    @Generated(hash = 1499455713)
    public WannaTable(Long Id, String avatarUrl, String title, String directors,
            String casts, String createtime, String average) {
        this.Id = Id;
        this.avatarUrl = avatarUrl;
        this.title = title;
        this.directors = directors;
        this.casts = casts;
        this.createtime = createtime;
        this.average = average;
    }
    @Generated(hash = 1329159679)
    public WannaTable() {
    }
    public Long getId() {
        return this.Id;
    }
    public void setId(Long Id) {
        this.Id = Id;
    }
    public String getAvatarUrl() {
        return this.avatarUrl;
    }
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDirectors() {
        return this.directors;
    }
    public void setDirectors(String directors) {
        this.directors = directors;
    }
    public String getCasts() {
        return this.casts;
    }
    public void setCasts(String casts) {
        this.casts = casts;
    }
    public String getCreatetime() {
        return this.createtime;
    }
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
    public String getAverage() {
        return this.average;
    }
    public void setAverage(String average) {
        this.average = average;
    }

}
