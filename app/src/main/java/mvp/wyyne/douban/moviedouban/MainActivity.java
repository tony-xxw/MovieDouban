package mvp.wyyne.douban.moviedouban;

import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import mvp.wyyne.douban.moviedouban.hot.HotFragment;

public class MainActivity extends AppCompatActivity {
    private FragmentTabHost mTabHost;
    private View mTabView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        initView();
    }

    private void initView() {
        mTabHost.setup(this, getSupportFragmentManager(), R.id.fl_main);
        mTabHost.addTab(mTabHost.newTabSpec("hot").setIndicator(getTabView()), HotFragment.class, null);
    }

    private View getTabView() {
        mTabView = View.inflate(this, R.layout.tab_layout, null);
        return mTabView;
    }
}
