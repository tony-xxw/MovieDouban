package mvp.wyyne.douban.moviedouban.api.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by XXW on 2017/6/24.
 */

public class Trailers implements Parcelable{

    /**
     * medium : https://img3.doubanio.com/img/trailer/medium/2460281372.jpg?
     * title : 中国预告片：定档版 (中文字幕)
     * subject_id : 25824686
     * alt : https://movie.douban.com/trailer/217410/
     * small : https://img3.doubanio.com/img/trailer/small/2460281372.jpg?
     * resource_url : http://vt1.doubanio.com/201706241613/5d985a07cd6bfdb3f6018fb33de7dbe4/view/movie/M/302170410.mp4
     * id : 217410
     */

    private String medium;
    private String title;
    private String subject_id;
    private String alt;
    private String small;
    private String resource_url;
    private String id;

    protected Trailers(Parcel in) {
        medium = in.readString();
        title = in.readString();
        subject_id = in.readString();
        alt = in.readString();
        small = in.readString();
        resource_url = in.readString();
        id = in.readString();
    }

    public static final Creator<Trailers> CREATOR = new Creator<Trailers>() {
        @Override
        public Trailers createFromParcel(Parcel in) {
            return new Trailers(in);
        }

        @Override
        public Trailers[] newArray(int size) {
            return new Trailers[size];
        }
    };

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getResource_url() {
        return resource_url;
    }

    public void setResource_url(String resource_url) {
        this.resource_url = resource_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(medium);
        dest.writeString(title);
        dest.writeString(subject_id);
        dest.writeString(alt);
        dest.writeString(small);
        dest.writeString(resource_url);
        dest.writeString(id);
    }
}
