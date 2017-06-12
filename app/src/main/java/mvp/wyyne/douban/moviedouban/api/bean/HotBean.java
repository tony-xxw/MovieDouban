package mvp.wyyne.douban.moviedouban.api.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by XXW on 2017/6/5.
 */

public class HotBean implements Parcelable{

    /**
     * title : 正在上映的电影-北京
     * total : 39
     * start : 0
     * count : 20
     */

    private String title;
    private int total;
    private int start;
    private int count;

    protected HotBean(Parcel in) {
        title = in.readString();
        total = in.readInt();
        start = in.readInt();
        count = in.readInt();
        subjects = in.createTypedArrayList(Subjects.CREATOR);
    }

    public static final Creator<HotBean> CREATOR = new Creator<HotBean>() {
        @Override
        public HotBean createFromParcel(Parcel in) {
            return new HotBean(in);
        }

        @Override
        public HotBean[] newArray(int size) {
            return new HotBean[size];
        }
    };

    public List<Subjects> getSubjectsList() {
        return subjects;
    }

    public void setSubjectsList(List<Subjects> subjectsList) {
        subjects = subjectsList;
    }

    private List<Subjects> subjects;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "HotBean{" +
                "title='" + title + '\'' +
                ", total=" + total +
                ", start=" + start +
                ", count=" + count +
                ", mSubjectsList=" + subjects +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeInt(total);
        dest.writeInt(start);
        dest.writeInt(count);
        dest.writeTypedList(subjects);
    }
}
