package mvp.wyyne.douban.moviedouban.api.bean;

import java.util.List;

/**
 * Created by XXW on 2017/6/5.
 */

public class Subjects {

    /**
     * rating : {"max":10,"average":7.4,"stars":"40","min":0}
     * genres : ["动作","奇幻","冒险"]
     * title : 神奇女侠
     * casts : [{"alt":"https://movie.douban.com/celebrity/1044996/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/8710.jpg","large":"https://img3.doubanio.com/img/celebrity/large/8710.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/8710.jpg"},"name":"盖尔·加朵","id":"1044996"},{"alt":"https://movie.douban.com/celebrity/1053621/","avatars":{"small":"https://img1.doubanio.com/img/celebrity/small/32637.jpg","large":"https://img1.doubanio.com/img/celebrity/large/32637.jpg","medium":"https://img1.doubanio.com/img/celebrity/medium/32637.jpg"},"name":"克里斯·派恩","id":"1053621"},{"alt":"https://movie.douban.com/celebrity/1009298/","avatars":{"small":"https://img1.doubanio.com/img/celebrity/small/1416109882.48.jpg","large":"https://img1.doubanio.com/img/celebrity/large/1416109882.48.jpg","medium":"https://img1.doubanio.com/img/celebrity/medium/1416109882.48.jpg"},"name":"康妮·尼尔森","id":"1009298"}]
     * collect_count : 77647
     * original_title : Wonder Woman
     * subtype : movie
     * directors : [{"alt":"https://movie.douban.com/celebrity/1023041/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/1496555593.75.jpg","large":"https://img3.doubanio.com/img/celebrity/large/1496555593.75.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/1496555593.75.jpg"},"name":"派蒂·杰金斯","id":"1023041"}]
     * year : 2017
     * images : {"small":"https://img3.doubanio.com/view/movie_poster_cover/ipst/public/p2460006593.jpg","large":"https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p2460006593.jpg","medium":"https://img3.doubanio.com/view/movie_poster_cover/spst/public/p2460006593.jpg"}
     * alt : https://movie.douban.com/subject/1578714/
     * id : 1578714
     */

    private Rating rating;
    private String title;
    private int collect_count;
    private String original_title;
    private String subtype;
    private String year;
    private Avatars images;
    private String alt;
    private String id;
    private List<String> genres;
    private List<Casts> casts;
    private List<Directors> directors;

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Avatars getImages() {
        return images;
    }

    public void setImages(Avatars images) {
        this.images = images;
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

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<Casts> getCasts() {
        return casts;
    }

    public void setCasts(List<Casts> casts) {
        this.casts = casts;
    }

    public List<Directors> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Directors> directors) {
        this.directors = directors;
    }


}
