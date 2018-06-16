package mvp.wyyne.douban.moviedouban.api.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author Wynne
 * @date 2018/6/16
 */

public class WeeklyMovieSubject implements Parcelable {
    protected WeeklyMovieSubject(Parcel in) {
        subjects = in.createTypedArrayList(WeeklySubject.CREATOR);
        title = in.readString();
    }

    public static final Creator<WeeklyMovieSubject> CREATOR = new Creator<WeeklyMovieSubject>() {
        @Override
        public WeeklyMovieSubject createFromParcel(Parcel in) {
            return new WeeklyMovieSubject(in);
        }

        @Override
        public WeeklyMovieSubject[] newArray(int size) {
            return new WeeklyMovieSubject[size];
        }
    };

    public List<WeeklySubject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<WeeklySubject> subjects) {
        this.subjects = subjects;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private List<WeeklySubject> subjects;
    private String title;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(subjects);
        dest.writeString(title);
    }
}
