package mvp.wyyne.douban.moviedouban.api.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 演员表
 *
 * @author Wynne
 * @date 2018/6/14
 */
@Entity
public class ActorCollectTable {
    @Id(autoincrement = true)
    private Long id;
    private String avatarUrl;
    private String actorName;
    private String representative;

    @Generated(hash = 772579989)
    public ActorCollectTable(Long id, String avatarUrl, String actorName,
                             String representative) {
        this.id = id;
        this.avatarUrl = avatarUrl;
        this.actorName = actorName;
        this.representative = representative;
    }

    @Generated(hash = 1432923284)
    public ActorCollectTable() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAvatarUrl() {
        return this.avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getActorName() {
        return this.actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public String getRepresentative() {
        return this.representative;
    }

    public void setRepresentative(String representative) {
        this.representative = representative;
    }

}
