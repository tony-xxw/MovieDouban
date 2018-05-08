package mvp.wyyne.douban.moviedouban.api.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 *
 * @author XXW
 * @date 2017/6/28
 */

public class MoviesReviews implements Parcelable {


    /**
     * count : 20
     * start : 0
     * total : 1018
     * next_start : 20
     */

    private List<Reviews> reviews;
    private Subjects subject;
    private int count;
    private int start;
    private int total;
    private int next_start;


    protected MoviesReviews(Parcel in) {
        reviews = in.createTypedArrayList(Reviews.CREATOR);
        subject = in.readParcelable(Subjects.class.getClassLoader());
        count = in.readInt();
        start = in.readInt();
        total = in.readInt();
        next_start = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(reviews);
        dest.writeParcelable(subject, flags);
        dest.writeInt(count);
        dest.writeInt(start);
        dest.writeInt(total);
        dest.writeInt(next_start);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MoviesReviews> CREATOR = new Creator<MoviesReviews>() {
        @Override
        public MoviesReviews createFromParcel(Parcel in) {
            return new MoviesReviews(in);
        }

        @Override
        public MoviesReviews[] newArray(int size) {
            return new MoviesReviews[size];
        }
    };

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getNext_start() {
        return next_start;
    }

    public void setNext_start(int next_start) {
        this.next_start = next_start;
    }


    public List<Reviews> getReviews() {
        return reviews;
    }

    public void setReviews(List<Reviews> reviews) {
        this.reviews = reviews;
    }

    public Subjects getSubject() {
        return subject;
    }

    public void setSubject(Subjects subject) {
        this.subject = subject;
    }
}
