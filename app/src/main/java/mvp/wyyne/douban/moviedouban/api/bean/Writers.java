package mvp.wyyne.douban.moviedouban.api.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by XXW on 2017/6/24.
 */

public class Writers implements Parcelable {

    /**
     * avatars : {"small":"https://img3.doubanio.com/img/celebrity/small/28451.jpg","large":"https://img3.doubanio.com/img/celebrity/large/28451.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/28451.jpg"}
     * name_en : Akiva Goldsman
     * name : 阿齐瓦·高斯曼
     * alt : https://movie.douban.com/celebrity/1009507/
     * id : 1009507
     */

    private Avatars avatars;
    private String name_en;
    private String name;
    private String alt;
    private String id;

    protected Writers(Parcel in) {
        avatars = in.readParcelable(Avatars.class.getClassLoader());
        name_en = in.readString();
        name = in.readString();
        alt = in.readString();
        id = in.readString();
    }

    public static final Creator<Writers> CREATOR = new Creator<Writers>() {
        @Override
        public Writers createFromParcel(Parcel in) {
            return new Writers(in);
        }

        @Override
        public Writers[] newArray(int size) {
            return new Writers[size];
        }
    };

    public Avatars getAvatars() {
        return avatars;
    }

    public void setAvatars(Avatars avatars) {
        this.avatars = avatars;
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(avatars, flags);
        dest.writeString(name_en);
        dest.writeString(name);
        dest.writeString(alt);
        dest.writeString(id);
    }
}
