package mvp.wyyne.douban.moviedouban.api.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by XXW on 2017/6/13.
 */

public class ArticleAttrs implements Parcelable {

    protected ArticleAttrs(Parcel in) {
    }

    public static final Creator<ArticleAttrs> CREATOR = new Creator<ArticleAttrs>() {
        @Override
        public ArticleAttrs createFromParcel(Parcel in) {
            return new ArticleAttrs(in);
        }

        @Override
        public ArticleAttrs[] newArray(int size) {
            return new ArticleAttrs[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }


    private List<String> website;
    private List<String> pubdate;
    private List<String> language;
    private List<String> title;
    private List<String> country;
    private List<String> writer;
    private List<String> director;
    private List<String> cast;
    private List<String> movie_duration;
    private List<String> year;
    private List<String> movie_type;

    public List<String> getWebsite() {
        return website;
    }

    public void setWebsite(List<String> website) {
        this.website = website;
    }

    public List<String> getPubdate() {
        return pubdate;
    }

    public void setPubdate(List<String> pubdate) {
        this.pubdate = pubdate;
    }

    public List<String> getLanguage() {
        return language;
    }

    public void setLanguage(List<String> language) {
        this.language = language;
    }

    public List<String> getTitle() {
        return title;
    }

    public void setTitle(List<String> title) {
        this.title = title;
    }

    public List<String> getCountry() {
        return country;
    }

    public void setCountry(List<String> country) {
        this.country = country;
    }

    public List<String> getWriter() {
        return writer;
    }

    public void setWriter(List<String> writer) {
        this.writer = writer;
    }

    public List<String> getDirector() {
        return director;
    }

    public void setDirector(List<String> director) {
        this.director = director;
    }

    public List<String> getCast() {
        return cast;
    }

    public void setCast(List<String> cast) {
        this.cast = cast;
    }

    public List<String> getMovie_duration() {
        return movie_duration;
    }

    public void setMovie_duration(List<String> movie_duration) {
        this.movie_duration = movie_duration;
    }

    public List<String> getYear() {
        return year;
    }

    public void setYear(List<String> year) {
        this.year = year;
    }

    public List<String> getMovie_type() {
        return movie_type;
    }

    public void setMovie_type(List<String> movie_type) {
        this.movie_type = movie_type;
    }

}
