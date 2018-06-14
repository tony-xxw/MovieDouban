package mvp.wyyne.douban.moviedouban.api.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 *
 * @author XXW
 * @date 2017/7/8
 */

public class ActorInfo implements Parcelable{

    /**
     * website :
     * mobile_url : https://movie.douban.com/celebrity/1054391/mobile
     * aka_en : ["Steven John Carell (本名)"]
     * name : 史蒂夫·卡瑞尔
     * name_en : Steve Carell
     * gender : 男
     * professions : ["演员","编剧","导演","制片"]
     * summary : 　　史蒂夫·卡瑞尔，中学在私立男子高校芬恩中学（Fenn School）读书，后来进入俄亥俄州的丹尼生大学，曾经想读法律专业，但因学不好攻读法律专业的原因而作罢。
     * 　　毕业后，他开始尝试演艺职业。他曾经是电视节目《每日秀》(The Daily Show)长达7年的通讯员。在2005年，卡瑞尔在NBC的热门剧，英国情景喜剧《办公室》中当上了主角，并因此得到了2006年的金球奖。他在2005年的自己参与制作和编剧的电影《40岁的老处男》(The 40-Year-Old Virgin)中扮演主角。在同年里他还在妮可-基德曼和威尔-法瑞尔主演的翻拍片《家有仙妻》(Bewitched)中有短暂出镜。目前他正在忙于拍摄翻拍片《糊涂侦探》(Get Smart)，他在里面扮演马克斯维尔-斯马特。
     * 　　卡瑞尔其他的荧幕作品还包括在金-凯瑞的《全能布鲁斯》(或译《冒牌天神》，Bruce Almighty)中饰演金-凯瑞的播音员死对头，而这一角色在2006年的续集《全能伊万》(或译《冒牌天神2》,Evan Almighty)中升级成了主角。另外还出演了伍迪-艾伦的《双生美莲达》(Melinda and Melinda)。
     * birthday : 1962-08-16
     * aka : ["史提夫卡路爾(港)"]
     * alt : https://movie.douban.com/celebrity/1054391/
     * born_place : 美国,马萨诸塞,康科德
     * constellation : 狮子座
     * id : 1054391
     */

    private String website;
    private String mobile_url;
    private String name;
    private String name_en;
    private String gender;
    private String summary;
    private String birthday;
    private String alt;
    private String born_place;
    private String constellation;
    private String id;
    private List<String> aka_en;
    private List<String> professions;
    private List<String> aka;
    private List<Works> works;
    private Avatars avatars;

    protected ActorInfo(Parcel in) {
        website = in.readString();
        mobile_url = in.readString();
        name = in.readString();
        name_en = in.readString();
        gender = in.readString();
        summary = in.readString();
        birthday = in.readString();
        alt = in.readString();
        born_place = in.readString();
        constellation = in.readString();
        id = in.readString();
        aka_en = in.createStringArrayList();
        professions = in.createStringArrayList();
        aka = in.createStringArrayList();
        avatars = in.readParcelable(Avatars.class.getClassLoader());
        photos = in.createTypedArrayList(Photos.CREATOR);
    }

    public static final Creator<ActorInfo> CREATOR = new Creator<ActorInfo>() {
        @Override
        public ActorInfo createFromParcel(Parcel in) {
            return new ActorInfo(in);
        }

        @Override
        public ActorInfo[] newArray(int size) {
            return new ActorInfo[size];
        }
    };

    public List<Works> getWorks() {
        return works;
    }

    public void setWorks(List<Works> works) {
        this.works = works;
    }

    public Avatars getAvatars() {
        return avatars;
    }

    public void setAvatars(Avatars avatars) {
        this.avatars = avatars;
    }

    public List<Photos> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photos> photos) {
        this.photos = photos;
    }

    private List<Photos> photos;

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getMobile_url() {
        return mobile_url;
    }

    public void setMobile_url(String mobile_url) {
        this.mobile_url = mobile_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getBorn_place() {
        return born_place;
    }

    public void setBorn_place(String born_place) {
        this.born_place = born_place;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getAka_en() {
        return aka_en;
    }

    public void setAka_en(List<String> aka_en) {
        this.aka_en = aka_en;
    }

    public List<String> getProfessions() {
        return professions;
    }

    public void setProfessions(List<String> professions) {
        this.professions = professions;
    }

    public List<String> getAka() {
        return aka;
    }

    public void setAka(List<String> aka) {
        this.aka = aka;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(website);
        dest.writeString(mobile_url);
        dest.writeString(name);
        dest.writeString(name_en);
        dest.writeString(gender);
        dest.writeString(summary);
        dest.writeString(birthday);
        dest.writeString(alt);
        dest.writeString(born_place);
        dest.writeString(constellation);
        dest.writeString(id);
        dest.writeStringList(aka_en);
        dest.writeStringList(professions);
        dest.writeStringList(aka);
        dest.writeParcelable(avatars, flags);
        dest.writeTypedList(photos);
    }
}
