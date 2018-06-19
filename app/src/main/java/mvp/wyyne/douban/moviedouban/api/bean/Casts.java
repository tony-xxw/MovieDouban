package mvp.wyyne.douban.moviedouban.api.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *
 * @author XXW
 * @date 2017/6/5
 */

public class Casts implements Parcelable{

    /**
     * alt : https://movie.douban.com/celebrity/1044996/
     * avatars : {"small":"https://img3.doubanio.com/img/celebrity/small/8710.jpg","large":"https://img3.doubanio.com/img/celebrity/large/8710.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/8710.jpg"}
     * name : 盖尔·加朵
     * id : 1044996
     */

    private String alt;

    protected Casts(Parcel in) {
        alt = in.readString();
        avatars = in.readParcelable(Avatars.class.getClassLoader());
        name = in.readString();
        id = in.readString();
        name_en = in.readString();
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

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    private Avatars avatars;
    private String name;
    private String id;
    private String name_en;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(alt);
        dest.writeParcelable(avatars, flags);
        dest.writeString(name);
        dest.writeString(id);
        dest.writeString(name_en);
    }
}
