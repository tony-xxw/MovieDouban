package mvp.wyyne.douban.moviedouban.api.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author XXW
 * @date 2017/6/5
 */

public class Subjects implements Parcelable {

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

    protected Subjects(Parcel in) {
        rating = in.readParcelable(Rating.class.getClassLoader());
        title = in.readString();
        collect_count = in.readInt();
        original_title = in.readString();
        subtype = in.readString();
        year = in.readString();
        images = in.readParcelable(Avatars.class.getClassLoader());
        alt = in.readString();
        id = in.readString();
        mainland_pubdate = in.readString();
        has_video = in.readByte() != 0;
        pubdates = in.createStringArrayList();
        durations = in.createStringArrayList();
        genres = in.createStringArrayList();
        casts = in.createTypedArrayList(Casts.CREATOR);
        directors = in.createTypedArrayList(Directors.CREATOR);
    }

    public static final Creator<Subjects> CREATOR = new Creator<Subjects>() {
        @Override
        public Subjects createFromParcel(Parcel in) {
            return new Subjects(in);
        }

        @Override
        public Subjects[] newArray(int size) {
            return new Subjects[size];
        }
    };

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

    public String getMainland_pubdate() {
        return mainland_pubdate;
    }

    public void setMainland_pubdate(String mainland_pubdate) {
        this.mainland_pubdate = mainland_pubdate;
    }

    public boolean isHas_video() {
        return has_video;
    }

    public void setHas_video(boolean has_video) {
        this.has_video = has_video;
    }

    public List<String> getPubdates() {
        return pubdates;
    }

    public void setPubdates(List<String> pubdates) {
        this.pubdates = pubdates;
    }

    public List<String> getDurations() {
        return durations;
    }

    public void setDurations(List<String> durations) {
        this.durations = durations;
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

    private String alt;
    private String id;


    private String mainland_pubdate;
    private boolean has_video;
    private List<String> pubdates;
    private List<String> durations;
    private List<String> genres;
    private List<Casts> casts;
    private List<Directors> directors;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(rating, flags);
        dest.writeString(title);
        dest.writeInt(collect_count);
        dest.writeString(original_title);
        dest.writeString(subtype);
        dest.writeString(year);
        dest.writeParcelable(images, flags);
        dest.writeString(alt);
        dest.writeString(id);
        dest.writeString(mainland_pubdate);
        dest.writeByte((byte) (has_video ? 1 : 0));
        dest.writeStringList(pubdates);
        dest.writeStringList(durations);
        dest.writeStringList(genres);
        dest.writeTypedList(casts);
        dest.writeTypedList(directors);
    }
}
