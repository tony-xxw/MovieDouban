package mvp.wyyne.douban.moviedouban.api.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by XXW on 2017/6/5.
 */

public class Directors implements Parcelable{

    /**
     * alt : https://movie.douban.com/celebrity/1023041/
     * avatars : {"small":"https://img3.doubanio.com/img/celebrity/small/1496555593.75.jpg","large":"https://img3.doubanio.com/img/celebrity/large/1496555593.75.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/1496555593.75.jpg"}
     * name : 派蒂·杰金斯
     * id : 1023041
     */

    private String alt;
    private Avatars avatars;
    private String name;
    private String id;

    protected Directors(Parcel in) {
        alt = in.readString();
        name = in.readString();
        id = in.readString();
    }

    public static final Creator<Directors> CREATOR = new Creator<Directors>() {
        @Override
        public Directors createFromParcel(Parcel in) {
            return new Directors(in);
        }

        @Override
        public Directors[] newArray(int size) {
            return new Directors[size];
        }
    };

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public Avatars getAvatars() {
        return avatars;
    }

    public void setAvatars(Avatars avatars) {
        this.avatars = avatars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        dest.writeString(alt);
        dest.writeString(name);
        dest.writeString(id);
    }
}
