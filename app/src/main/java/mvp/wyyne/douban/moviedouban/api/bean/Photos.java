package mvp.wyyne.douban.moviedouban.api.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by XXW on 2017/6/24.
 */

public class Photos implements Parcelable{

    /**
     * thumb : https://img3.doubanio.com/view/photo/thumb/public/p2462506861.jpg
     * image : https://img3.doubanio.com/view/photo/photo/public/p2462506861.jpg
     * cover : https://img3.doubanio.com/view/photo/albumcover/public/p2462506861.jpg
     * alt : https://movie.douban.com/photos/photo/2462506861/
     * id : 2462506861
     * icon : https://img3.doubanio.com/view/photo/icon/public/p2462506861.jpg
     */

    private String thumb;
    private String image;
    private String cover;
    private String alt;
    private String id;
    private String icon;

    protected Photos(Parcel in) {
        thumb = in.readString();
        image = in.readString();
        cover = in.readString();
        alt = in.readString();
        id = in.readString();
        icon = in.readString();
    }

    public static final Creator<Photos> CREATOR = new Creator<Photos>() {
        @Override
        public Photos createFromParcel(Parcel in) {
            return new Photos(in);
        }

        @Override
        public Photos[] newArray(int size) {
            return new Photos[size];
        }
    };

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(thumb);
        dest.writeString(image);
        dest.writeString(cover);
        dest.writeString(alt);
        dest.writeString(id);
        dest.writeString(icon);
    }
}
