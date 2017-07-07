package mvp.wyyne.douban.moviedouban.detail.cast;


import android.util.Log;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.home.BaseActivity;

/**
 * Created by XXW on 2017/6/30.
 */

public class CastDetailActivity extends BaseActivity {
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


    }


}
