package mvp.wyyne.douban.moviedouban.api.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author Wynne
 * @date 2018/6/16
 */

public class UsMovieSubject implements Parcelable {
    public List<UsSubjects> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<UsSubjects> subjects) {
        this.subjects = subjects;
    }

    private String title;


    protected UsMovieSubject(Parcel in) {
        title = in.readString();
        subjects = in.createTypedArrayList(UsSubjects.CREATOR);
        date = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeTypedList(subjects);
        dest.writeString(date);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UsMovieSubject> CREATOR = new Creator<UsMovieSubject>() {
        @Override
        public UsMovieSubject createFromParcel(Parcel in) {
            return new UsMovieSubject(in);
        }

        @Override
        public UsMovieSubject[] newArray(int size) {
            return new UsMovieSubject[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private List<UsSubjects> subjects;

    private String date;


}
