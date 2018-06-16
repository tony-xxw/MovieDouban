package mvp.wyyne.douban.moviedouban.api.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author Wynne
 * @date 2018/6/16
 */

public class UcMovieSubject implements Parcelable {
    public List<UsSubjects> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<UsSubjects> subjects) {
        this.subjects = subjects;
    }

    private String title;


    protected UcMovieSubject(Parcel in) {
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


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private List<UsSubjects> subjects;

    private String date;


}
