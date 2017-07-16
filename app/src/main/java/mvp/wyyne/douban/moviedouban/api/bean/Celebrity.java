package mvp.wyyne.douban.moviedouban.api.bean;

/**
 * Created by XXW on 2017/7/15.
 */

public class Celebrity {

    /**
     * name_en : Eddie Peng
     * name : 彭于晏
     * alt : https://movie.douban.com/celebrity/1013782/
     * id : 1013782
     */

    private String name_en;
    private String name;
    private String alt;
    private String id;

    public Avatars getAvatars() {
        return avatars;
    }

    public void setAvatars(Avatars avatars) {
        this.avatars = avatars;
    }

    private Avatars avatars;

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
