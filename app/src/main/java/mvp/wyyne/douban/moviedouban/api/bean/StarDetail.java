package mvp.wyyne.douban.moviedouban.api.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by XXW on 2017/6/24.
 */

public class StarDetail implements Parcelable {

    /**
     * grade1 : 1159
     * grade2 : 1753
     * grade3 : 1723
     * grade4 : 478
     * grade5 : 263
     */

    private int grade1;
    private int grade2;
    private int grade3;
    private int grade4;
    private int grade5;

    protected StarDetail(Parcel in) {
        grade1 = in.readInt();
        grade2 = in.readInt();
        grade3 = in.readInt();
        grade4 = in.readInt();
        grade5 = in.readInt();
    }

    public static final Creator<StarDetail> CREATOR = new Creator<StarDetail>() {
        @Override
        public StarDetail createFromParcel(Parcel in) {
            return new StarDetail(in);
        }

        @Override
        public StarDetail[] newArray(int size) {
            return new StarDetail[size];
        }
    };

    public int getGrade1() {
        return grade1;
    }

    public void setGrade1(int grade1) {
        this.grade1 = grade1;
    }

    public int getGrade2() {
        return grade2;
    }

    public void setGrade2(int grade2) {
        this.grade2 = grade2;
    }

    public int getGrade3() {
        return grade3;
    }

    public void setGrade3(int grade3) {
        this.grade3 = grade3;
    }

    public int getGrade4() {
        return grade4;
    }

    public void setGrade4(int grade4) {
        this.grade4 = grade4;
    }

    public int getGrade5() {
        return grade5;
    }

    public void setGrade5(int grade5) {
        this.grade5 = grade5;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(grade1);
        dest.writeInt(grade2);
        dest.writeInt(grade3);
        dest.writeInt(grade4);
        dest.writeInt(grade5);
    }
}
