package mvp.wyyne.douban.moviedouban.api.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * @author XXW
 * @date 2017/6/25
 */

public class WelfarePhotoInfo implements Parcelable {
    /**
     * _id : 57facc74421aa95de3b8ab6b
     * createdAt : 2016-10-10T07:02:12.35Z
     * desc : 10-10
     * publishedAt : 2016-10-10T11:41:33.500Z
     * source : chrome
     * type : 福利
     * url : http://ww3.sinaimg.cn/large/610dc034jw1f8mssipb9sj20u011hgqk.jpg
     * used : true
     * who : daimajia
     */
    @SerializedName("_id")
    private String id;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;
    /**
     * 保存图片宽高
     **/
    private String pixel;

    protected WelfarePhotoInfo(Parcel in) {
        id = in.readString();
        createdAt = in.readString();
        desc = in.readString();
        publishedAt = in.readString();
        source = in.readString();
        type = in.readString();
        url = in.readString();
        used = in.readByte() != 0;
        who = in.readString();
        pixel = in.readString();
    }

    public static final Creator<WelfarePhotoInfo> CREATOR = new Creator<WelfarePhotoInfo>() {
        @Override
        public WelfarePhotoInfo createFromParcel(Parcel in) {
            return new WelfarePhotoInfo(in);
        }

        @Override
        public WelfarePhotoInfo[] newArray(int size) {
            return new WelfarePhotoInfo[size];
        }
    };

    public String getPixel() {
        return pixel;
    }

    public void setPixel(String pixel) {
        this.pixel = pixel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    @Override
    public String toString() {
        return "WelfarePhotoBean{" +
                "id='" + id + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", desc='" + desc + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", source='" + source + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", used=" + used +
                ", who='" + who + '\'' +
                ", pixel='" + pixel + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(createdAt);
        dest.writeString(desc);
        dest.writeString(publishedAt);
        dest.writeString(source);
        dest.writeString(type);
        dest.writeString(url);
        dest.writeByte((byte) (used ? 1 : 0));
        dest.writeString(who);
        dest.writeString(pixel);
    }
}
