package mvp.wyyne.douban.moviedouban.api.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by XXW on 2017/6/24.
 */

public class User implements Parcelable{
    /**
     * uid : 139534469
     * avatar : https://img1.doubanio.com/icon/user_normal.jpg
     * signature :
     * alt : https://www.douban.com/people/139534469/
     * id : 139534469
     * name : 马竞
     */

    private String uid;
    private String avatar;
    private String signature;
    private String alt;
    private String id;
    private String name;

    protected User(Parcel in) {
        uid = in.readString();
        avatar = in.readString();
        signature = in.readString();
        alt = in.readString();
        id = in.readString();
        name = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uid);
        dest.writeString(avatar);
        dest.writeString(signature);
        dest.writeString(alt);
        dest.writeString(id);
        dest.writeString(name);
    }
}
