package mvp.wyyne.douban.moviedouban.detail.head;

import android.util.Log;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.home.BaseActivity;

/**
 * Created by XXW on 2017/8/21.
 */

public class CommentCountActivity extends BaseActivity {
    @Override
    protected void refresh() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_comment;
    }

    @Override
    protected void initView() {

    }

    public void show(){
        Log.d("向鲜汶","你天天在这里虚度时光？");
    }
}
