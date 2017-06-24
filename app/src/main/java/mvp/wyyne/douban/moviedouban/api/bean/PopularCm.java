package mvp.wyyne.douban.moviedouban.api.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by XXW on 2017/6/24.
 */

public class PopularCm implements Parcelable{

    /**
     * rating : {"max":5,"value":5,"min":0}
     * useful_count : 931
     * author : {"uid":"139534469","avatar":"https://img1.doubanio.com/icon/user_normal.jpg","signature":"","alt":"https://www.douban.com/people/139534469/","id":"139534469","name":"马竞"}
     * subject_id : 25824686
     * content : 麻痹的看个擎天柱还要剧情，真是文艺青年聚集地啊
     * created_at : 2017-06-23 11:01:45
     * id : 1206159937
     */

    private UserRating rating;
    private int useful_count;
    private User author;
    private String subject_id;
    private String content;
    private String created_at;
    private String id;

    protected PopularCm(Parcel in) {
        useful_count = in.readInt();
        subject_id = in.readString();
        content = in.readString();
        created_at = in.readString();
        id = in.readString();
    }

    public static final Creator<PopularCm> CREATOR = new Creator<PopularCm>() {
        @Override
        public PopularCm createFromParcel(Parcel in) {
            return new PopularCm(in);
        }

        @Override
        public PopularCm[] newArray(int size) {
            return new PopularCm[size];
        }
    };

    public UserRating getRating() {
        return rating;
    }

    public void setRating(UserRating rating) {
        this.rating = rating;
    }

    public int getUseful_count() {
        return useful_count;
    }

    public void setUseful_count(int useful_count) {
        this.useful_count = useful_count;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
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
        dest.writeInt(useful_count);
        dest.writeString(subject_id);
        dest.writeString(content);
        dest.writeString(created_at);
        dest.writeString(id);
    }
}
