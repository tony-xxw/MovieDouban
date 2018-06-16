package mvp.wyyne.douban.moviedouban.api.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * @author Wynne
 * @date 2018/6/16
 */

public class UsSubjects implements Parcelable {

    /**
     * box : 41500000
     * new : true
     * rank : 1
     */

    private int box;
    @SerializedName("new")
    private boolean newX;
    private int rank;
    private Subjects subject;

    protected UsSubjects(Parcel in) {
        box = in.readInt();
        newX = in.readByte() != 0;
        rank = in.readInt();
        subject = in.readParcelable(Subjects.class.getClassLoader());
    }

    public static final Creator<UsSubjects> CREATOR = new Creator<UsSubjects>() {
        @Override
        public UsSubjects createFromParcel(Parcel in) {
            return new UsSubjects(in);
        }

        @Override
        public UsSubjects[] newArray(int size) {
            return new UsSubjects[size];
        }
    };

    public Subjects getSubject() {
        return subject;
    }

    public void setSubject(Subjects subject) {
        this.subject = subject;
    }


    public int getBox() {
        return box;
    }

    public void setBox(int box) {
        this.box = box;
    }

    public boolean isNewX() {
        return newX;
    }

    public void setNewX(boolean newX) {
        this.newX = newX;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(box);
        dest.writeByte((byte) (newX ? 1 : 0));
        dest.writeInt(rank);
        dest.writeParcelable(subject, flags);
    }
}
