package mvp.wyyne.douban.moviedouban.detail.head;

import android.support.v4.view.ViewPager;

import mvp.wyyne.douban.moviedouban.detail.IDetailPresent;

/**
 * @author XXW
 * @date 2017/6/30
 */

public class DetailHeadImp implements IDetailPresent {

    private IDHeadMain mMain;

    public DetailHeadImp(IDHeadMain main) {
        mMain = main;

    }


    @Override
    public void getData() {

    }

    @Override
    public void getArticle(String subjectId) {

    }

    @Override
    public void initPage(ViewPager viewPager) {

    }
}
