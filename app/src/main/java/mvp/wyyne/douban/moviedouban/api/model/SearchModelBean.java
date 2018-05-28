package mvp.wyyne.douban.moviedouban.api.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author Wynne
 * @date 2018/5/28
 */
@Entity
public class SearchModelBean {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private String movieId;

    @Generated(hash = 1150333050)
    public SearchModelBean(Long id, String name, String movieId) {
        this.id = id;
        this.name = name;
        this.movieId = movieId;
    }

    @Generated(hash = 692158201)
    public SearchModelBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMovieId() {
        return this.movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }


}
