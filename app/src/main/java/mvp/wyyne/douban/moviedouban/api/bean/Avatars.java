package mvp.wyyne.douban.moviedouban.api.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *
 * @author XXW
 * @date 2017/6/5
 */

public class Avatars implements Parcelable{


    /**
     * small : https://img3.doubanio.com/img/celebrity/small/8710.jpg
     * large : https://img3.doubanio.com/img/celebrity/large/8710.jpg
     * medium : https://img3.doubanio.com/img/celebrity/medium/8710.jpg
     */

    private String small;
    private String large;
    private String medium;

    protected Avatars(Parcel in) {
        small = in.readString();
        large = in.readString();
        medium = in.readString();
    }

    public static final Creator<Avatars> CREATOR = new Creator<Avatars>() {
        @Override
        public Avatars createFromParcel(Parcel in) {
            return new Avatars(in);
        }

        @Override
        public Avatars[] newArray(int size) {
            return new Avatars[size];
        }
    };

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(small);
        dest.writeString(large);
        dest.writeString(medium);
    }
}
