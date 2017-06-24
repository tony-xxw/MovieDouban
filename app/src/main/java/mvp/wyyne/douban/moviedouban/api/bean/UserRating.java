package mvp.wyyne.douban.moviedouban.api.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by XXW on 2017/6/24.
 */

public class UserRating implements Parcelable{
    /**
     * max : 5
     * value : 5
     * min : 0
     */

    private int max;
    private int value;
    private int min;

    protected UserRating(Parcel in) {
        max = in.readInt();
        value = in.readInt();
        min = in.readInt();
    }

    public static final Creator<UserRating> CREATOR = new Creator<UserRating>() {
        @Override
        public UserRating createFromParcel(Parcel in) {
            return new UserRating(in);
        }

        @Override
        public UserRating[] newArray(int size) {
            return new UserRating[size];
        }
    };

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
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
        dest.writeInt(value);
        dest.writeInt(min);
    }
}
