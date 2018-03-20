package mvp.wyyne.douban.moviedouban.utils;

import mvp.wyyne.douban.moviedouban.api.bean.Subjects;

/**
 * @author XXW
 * @date 2017/6/14
 */

public class MovieType {

    private String mTags;

    public String getTags() {
        return mTags;
    }

    public void setTags(String tags) {
        mTags = tags;
    }

    public Subjects getSubjects() {
        return mSubjects;
    }

    public void setSubjects(Subjects subjects) {
        mSubjects = subjects;
    }

    private Subjects mSubjects;

    public MovieType(String tags, Subjects subjects) {
        mTags = tags;
        mSubjects = subjects;
    }
}
