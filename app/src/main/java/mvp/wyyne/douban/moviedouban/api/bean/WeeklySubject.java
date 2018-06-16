package mvp.wyyne.douban.moviedouban.api.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Wynne
 * @date 2018/6/16
 */

public class WeeklySubject implements Parcelable {

    protected WeeklySubject(Parcel in) {
        rank = in.readString();
        delta = in.readString();
        subject = in.readParcelable(Subjects.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(rank);
        dest.writeString(delta);
        dest.writeParcelable(subject, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<WeeklySubject> CREATOR = new Creator<WeeklySubject>() {
        @Override
        public WeeklySubject createFromParcel(Parcel in) {
            return new WeeklySubject(in);
        }

        @Override
        public WeeklySubject[] newArray(int size) {
            return new WeeklySubject[size];
        }
    };

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getDelta() {
        return delta;
    }

    public void setDelta(String delta) {
        this.delta = delta;
    }

    public Subjects getSubject() {
        return subject;
    }

    public void setSubject(Subjects subject) {
        this.subject = subject;
    }

    String rank;
    String delta;
    Subjects subject;
}
