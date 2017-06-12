package mvp.wyyne.douban.moviedouban.api.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by XXW on 2017/6/5.
 */

public class Casts implements Parcelable{

    /**
     * alt : https://movie.douban.com/celebrity/1044996/
     * avatars : {"small":"https://img3.doubanio.com/img/celebrity/small/8710.jpg","large":"https://img3.doubanio.com/img/celebrity/large/8710.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/8710.jpg"}
     * name : 盖尔·加朵
     * id : 1044996
     */

    private String alt;
    private Avatars avatars;
    private String name;
    private String id;

    protected Casts(Parcel in) {
        alt = in.readString();
        name = in.readString();
        id = in.readString();
    }

    public static final Creator<Casts> CREATOR = new Creator<Casts>() {
        @Override
        public Casts createFromParcel(Parcel in) {
            return new Casts(in);
        }

        @Override
        public Casts[] newArray(int size) {
            return new Casts[size];
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
