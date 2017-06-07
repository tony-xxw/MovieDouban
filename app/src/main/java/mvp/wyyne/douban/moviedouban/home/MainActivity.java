package mvp.wyyne.douban.moviedouban.home;

import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.hot.HotFragment;
import mvp.wyyne.douban.moviedouban.movie.MovieFragment;
import mvp.wyyne.douban.moviedouban.oneself.OneselfFragment;
import mvp.wyyne.douban.moviedouban.welfare.WelfareFragment;

public class MainActivity extends AppCompatActivity {
    private FragmentTabHost mTabHost;
    private View mTabView;
    private Class[] mFragment = {HotFragment.class, MovieFragment.class, OneselfFragment.class, WelfareFragment.class};
    private String[] mTabName = {"热门", "找片", "我的", "福利"};
    private int[] mTabDrawable = {R.drawable.hot_selected, R.drawable.movie_selected, R.drawable.oneself_selected, R.drawable.welfare_selected};
    private String[] tag = {"hot", "movie", "oneself", "welfare"};
    private TextView mTabNameView;
    private ImageView mTabImageVIew;
    private long mCurrentTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        initView();
    }

    private void initView() {
        mTabHost.setup(this, getSupportFragmentManager(), R.id.fl_main);
        for (int i = 0; i < mTabDrawable.length; i++) {
            mTabHost.addTab(mTabHost.newTabSpec(tag[i]).setIndicator(getTabView(i)), mFragment[i], null);
        }

    }

    private View getTabView(int i) {
        mTabView = View.inflate(this, R.layout.tab_layout, null);
        mTabImageVIew = (ImageView) mTabView.findViewById(R.id.tab_iv);
        mTabNameView = (TextView) mTabView.findViewById(R.id.tab_tv);
        mTabImageVIew.setImageResource(mTabDrawable[i]);
        mTabNameView.setText(mTabName[i]);
        return mTabView;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - mCurrentTime > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mCurrentTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
