package mvp.wyyne.douban.moviedouban.api.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 *
 * @author Wynne
 * @date 2018/6/16
 */

public class UcMovieSubject implements Parcelable {
    private String title;

    protected UcMovieSubject(Parcel in) {
        title = in.readString();
        subjects = in.createTypedArrayList(Subjects.CREATOR);
        date = in.readString();
    }

    public static final Creator<UcMovieSubject> CREATOR = new Creator<UcMovieSubject>() {
        @Override
        public UcMovieSubject createFromParcel(Parcel in) {
            return new UcMovieSubject(in);
        }

        @Override
        public UcMovieSubject[] newArray(int size) {
            return new UcMovieSubject[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Subjects> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subjects> subjects) {
        this.subjects = subjects;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private List<Subjects> subjects;

    private String date;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeTypedList(subjects);
        dest.writeString(date);
    }
}
