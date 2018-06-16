package mvp.wyyne.douban.moviedouban.api.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 口碑榜
 *
 * @author Wynne
 * @date 2018/6/16
 */

public class WeeklyMovieSubject implements Parcelable {
    private String title;

    private List<Subjects> subjects;

    protected WeeklyMovieSubject(Parcel in) {
        title = in.readString();
        subjects = in.createTypedArrayList(Subjects.CREATOR);
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeTypedList(subjects);
    }
}
