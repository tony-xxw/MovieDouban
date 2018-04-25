package mvp.wyyne.douban.moviedouban.home;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.hot.main.HotFragment;
import mvp.wyyne.douban.moviedouban.movie.MovieFragment;
import mvp.wyyne.douban.moviedouban.oneself.OneselfFragment;
import mvp.wyyne.douban.moviedouban.utils.StatusUtils;
import mvp.wyyne.douban.moviedouban.utils.StringUtils;
import mvp.wyyne.douban.moviedouban.welfare.WelfareFragment;

/**
 * 主页面
 *
 * @author Wynne
 */
public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private long mCurrentTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bnm_menu);
        disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        initView();
    }

    private void initView() {
        getSupportFragmentManager()
                .beginTransaction().add(R.id.fl_main, HotFragment.getInstance(), StringUtils.getClassName(HotFragment.class))
                .addToBackStack(StringUtils.getClassName(HotFragment.class))
                .commit();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_hot:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fl_main, HotFragment.getInstance(), HotFragment.TAG)
                        .addToBackStack(HotFragment.TAG)
                        .commit();
                return true;
            case R.id.main_movie:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fl_main, MovieFragment.getInstance(), MovieFragment.TAG)
                        .addToBackStack(MovieFragment.TAG)
                        .commit();
                return true;
            case R.id.main_oneself:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fl_main, OneselfFragment.getInstance(), OneselfFragment.TAG)
                        .addToBackStack(OneselfFragment.TAG)
                        .commit();
                return true;
            case R.id.main_welfare:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fl_main, WelfareFragment.getInstance(), WelfareFragment.TAG)
                        .addToBackStack(WelfareFragment.TAG)
                        .commit();
                return true;
            default:
                break;

        }
        return true;
    }


    /**
     * 退出两次判定
     *
     * @param keyCode
     * @param event
     * @return
     */
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


    /**
     * 修改BottomNavigationMenuView源码 让其动画失效
     *
     * @param view
     */
    @SuppressLint("RestrictedApi")
    public void disableShiftMode(BottomNavigationView view) {
        //获取子View BottomNavigationMenuView的对象
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            //设置私有成员变量mShiftingMode可以修改
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                //去除shift效果
                item.setShiftingMode(false);
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("BNVHelper", "没有mShiftingMode这个成员变量", e);
        } catch (IllegalAccessException e) {
            Log.e("BNVHelper", "无法修改mShiftingMode的值", e);
        }
    }


}
