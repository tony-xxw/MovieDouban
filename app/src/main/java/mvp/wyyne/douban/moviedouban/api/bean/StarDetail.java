package mvp.wyyne.douban.moviedouban.api.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * @author XXW
 * @date 2017/6/24
 */

public class StarDetail implements Parcelable {


    /**
     * 1 : 168
     * 2 : 380
     * 3 : 4350
     * 4 : 21229
     * 5 : 42205
     */

    @SerializedName("1")
    private int _$1;
    @SerializedName("2")
    private int _$2;
    @SerializedName("3")
    private int _$3;
    @SerializedName("4")
    private int _$4;
    @SerializedName("5")
    private int _$5;

    protected StarDetail(Parcel in) {
        _$1 = in.readInt();
        _$2 = in.readInt();
        _$3 = in.readInt();
        _$4 = in.readInt();
        _$5 = in.readInt();
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

    public int get_$1() {
        return _$1;
    }

    public void set_$1(int _$1) {
        this._$1 = _$1;
    }

    public int get_$2() {
        return _$2;
    }

    public void set_$2(int _$2) {
        this._$2 = _$2;
    }

    public int get_$3() {
        return _$3;
    }

    public void set_$3(int _$3) {
        this._$3 = _$3;
    }

    public int get_$4() {
        return _$4;
    }

    public void set_$4(int _$4) {
        this._$4 = _$4;
    }

    public int get_$5() {
        return _$5;
    }

    public void set_$5(int _$5) {
        this._$5 = _$5;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(_$1);
        dest.writeInt(_$2);
        dest.writeInt(_$3);
        dest.writeInt(_$4);
        dest.writeInt(_$5);
    }
}
