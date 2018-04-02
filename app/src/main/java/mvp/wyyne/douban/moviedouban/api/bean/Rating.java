package mvp.wyyne.douban.moviedouban.api.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * @author XXW
 * @date 2017/6/5
 */

public class Rating implements Parcelable {


    /**
     * max : 10
     * average : 9.1
     * details : {"1":168,"2":380,"3":4350,"4":21229,"5":42205}
     * stars : 45
     * min : 0
     */

    private int max;
    private double average;
    private StarDetail details;
    private String stars;
    private int min;

    protected Rating(Parcel in) {
        max = in.readInt();
        average = in.readDouble();
        details = in.readParcelable(StarDetail.class.getClassLoader());
        stars = in.readString();
        min = in.readInt();
    }

    public static final Creator<Rating> CREATOR = new Creator<Rating>() {
        @Override
        public Rating createFromParcel(Parcel in) {
            return new Rating(in);
        }

        @Override
        public Rating[] newArray(int size) {
            return new Rating[size];
        }
    };

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public StarDetail getDetails() {
        return details;
    }

    public void setDetails(StarDetail details) {
        this.details = details;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(max);
        dest.writeDouble(average);
        dest.writeParcelable(details, flags);
        dest.writeString(stars);
        dest.writeInt(min);
    }
}
