package mvp.wyyne.douban.moviedouban.detail.cast;


import android.util.Log;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.bean.CastArticle;
import mvp.wyyne.douban.moviedouban.home.BaseActivity;

/**
 * Created by XXW on 2017/6/30.
 */

public class CastArticleActivity extends BaseActivity<ICastPresent> implements ICastMain {
    //影人条目Id
    public static final String CAST_ID = "cast_id";
    private String id;

    @Override
    protected void refresh() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_cast_detail;
    }

    @Override
    protected void initView() {
        if (getIntent() != null) {
            id = getIntent().getStringExtra(CAST_ID);
            Log.d("XXW", "id-->" + id);
        }
        mPresent = new CastArticleImp(this);
        mPresent.getCastArticle(id);


    }


    @Override
    public void show() {
        mLodingView.show();
    }

    @Override
    public void hide() {
        mLodingView.hide();
    }

    @Override
    public void showPage(CastArticle article) {

    }
}
