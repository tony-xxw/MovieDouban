package mvp.wyyne.douban.moviedouban.api.bean;

import java.util.List;

/**
 * Created by XXW on 2017/6/13.
 */

public class Article {


    /**
     * rating : {"max":10,"average":7.2,"stars":"40","min":0}
     * reviews_count : 252
     * wish_count : 13148
     * douban_site :
     * year : 2016
     * images : {"small":"https://img3.doubanio.com/view/movie_poster_cover/ipst/public/p2460139111.jpg","large":"https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p2460139111.jpg","medium":"https://img3.doubanio.com/view/movie_poster_cover/spst/public/p2460139111.jpg"}
     * alt : https://movie.douban.com/subject/26220650/
     * id : 26220650
     * mobile_url : https://movie.douban.com/subject/26220650/mobile
     * title : 雄狮
     * do_count : null
     * share_url : https://m.douban.com/movie/subject/26220650
     * seasons_count : null
     * schedule_url : https://movie.douban.com/subject/26220650/cinema/
     * episodes_count : null
     * countries : ["澳大利亚","美国","英国"]
     * genres : ["剧情"]
     * collect_count : 15973
     * casts : [{"alt":"https://movie.douban.com/celebrity/1025134/","avatars":{"small":"https://img1.doubanio.com/img/celebrity/small/28.jpg","large":"https://img1.doubanio.com/img/celebrity/large/28.jpg","medium":"https://img1.doubanio.com/img/celebrity/medium/28.jpg"},"name":"戴夫·帕特尔","id":"1025134"},{"alt":"https://movie.douban.com/celebrity/1274969/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/34813.jpg","large":"https://img3.doubanio.com/img/celebrity/large/34813.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/34813.jpg"},"name":"鲁妮·玛拉","id":"1274969"},{"alt":"https://movie.douban.com/celebrity/1049573/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/5140.jpg","large":"https://img3.doubanio.com/img/celebrity/large/5140.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/5140.jpg"},"name":"大卫·文翰","id":"1049573"},{"alt":"https://movie.douban.com/celebrity/1054442/","avatars":{"small":"https://img1.doubanio.com/img/celebrity/small/619.jpg","large":"https://img1.doubanio.com/img/celebrity/large/619.jpg","medium":"https://img1.doubanio.com/img/celebrity/medium/619.jpg"},"name":"妮可·基德曼","id":"1054442"}]
     * current_season : null
     * original_title : Lion
     * summary : 影片根据畅销书《漫漫寻家路》改编，故事源于作者萨罗·布莱尔利的一段真实经历。五岁时萨罗在印度的火车上不小心与家人走散，他在加尔各答的街头流浪了几个星期，之后被送进一所孤儿院，并被澳大利亚的一对夫妇收养。25年后，他开始凭借隐约的记忆，用谷歌地球去寻找过去的家。
     * subtype : movie
     * directors : [{"alt":"https://movie.douban.com/celebrity/1356931/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/1487844297.93.jpg","large":"https://img3.doubanio.com/img/celebrity/large/1487844297.93.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/1487844297.93.jpg"},"name":"加斯·戴维斯","id":"1356931"}]
     * comments_count : 7192
     * ratings_count : 14651
     * aka : ["漫漫回家路(港)","A Long Way Home"]
     */
    private List<String> trailer_urls;
    private boolean has_video;
    private Rating rating;
    private List<PopularCm> popular_comments;
    private List<PopularCmRv> popular_reviews;
    private int reviews_count;
    private int wish_count;
    private String douban_site;
    private String pubdate;
    private String year;
    private Avatars images;
    private String alt;
    private int photos_count;
    private String id;
    private String mainland_pubdate;
    private String mobile_url;
    private String title;
    private Object do_count;
    private String share_url;
    private Object seasons_count;
    private String schedule_url;
    private Object episodes_count;
    private int collect_count;
    private Object current_season;
    private String original_title;
    private String summary;
    private String subtype;
    private int comments_count;
    private int ratings_count;
    private List<Photos> photos;
    private List<String> languages;
    private List<String> blooper_urls;
    private List<String> countries;
    private List<String> genres;
    private List<String> pubdates;
    private List<Casts> casts;
    private List<Directors> directors;
    private List<String> aka;
    private List<Writers> writers;
    private String website;
    private List<String> tags;
    private boolean has_schedule;
    private List<String> durations;
    private List<Trailers> trailers;
    private boolean has_ticket;
    private List<Trailers> bloopers;
    private List<String> clip_urls;
    private List<Trailers> clips;

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public int getReviews_count() {
        return reviews_count;
    }

    public void setReviews_count(int reviews_count) {
        this.reviews_count = reviews_count;
    }

    public int getWish_count() {
        return wish_count;
    }

    public void setWish_count(int wish_count) {
        this.wish_count = wish_count;
    }

    public String getDouban_site() {
        return douban_site;
    }

    public void setDouban_site(String douban_site) {
        this.douban_site = douban_site;
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

    public String getMobile_url() {
        return mobile_url;
    }

    public void setMobile_url(String mobile_url) {
        this.mobile_url = mobile_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getDo_count() {
        return do_count;
    }

    public void setDo_count(Object do_count) {
        this.do_count = do_count;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public Object getSeasons_count() {
        return seasons_count;
    }

    public void setSeasons_count(Object seasons_count) {
        this.seasons_count = seasons_count;
    }

    public String getSchedule_url() {
        return schedule_url;
    }

    public void setSchedule_url(String schedule_url) {
        this.schedule_url = schedule_url;
    }

    public Object getEpisodes_count() {
        return episodes_count;
    }

    public void setEpisodes_count(Object episodes_count) {
        this.episodes_count = episodes_count;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public Object getCurrent_season() {
        return current_season;
    }

    public void setCurrent_season(Object current_season) {
        this.current_season = current_season;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public int getRatings_count() {
        return ratings_count;
    }

    public void setRatings_count(int ratings_count) {
        this.ratings_count = ratings_count;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
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

    public List<String> getAka() {
        return aka;
    }

    public void setAka(List<String> aka) {
        this.aka = aka;
    }
}
