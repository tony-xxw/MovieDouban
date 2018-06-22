package mvp.wyyne.douban.moviedouban.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.tinkerpatch.sdk.TinkerPatch;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.bean.Subjects;
import mvp.wyyne.douban.moviedouban.hot.main.HotFragment;
import mvp.wyyne.douban.moviedouban.movie.MovieFragment;
import mvp.wyyne.douban.moviedouban.oneself.OneselfFragment;
import mvp.wyyne.douban.moviedouban.utils.ResourcesUtils;
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
    private Fragment currentFragment;
    private List<Fragment> mListFragment = new ArrayList<>();
    private HotFragment hotFragment;
    private MovieFragment movieFragment;
    private OneselfFragment oneselfFragment;
    private WelfareFragment welfareFragment;
    public ArrayList<Subjects> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bnm_menu);
        disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        StatusUtils.setStatusBarColor(this, ResourcesUtils.getColor(R.color.white, this), true);
        initFragment();
        initView();

    }


    @Override
    protected void onResume() {
        super.onResume();
        hotFragment.llSearch.setVisibility(View.VISIBLE);
        hotFragment.llSearchMain.setVisibility(View.GONE);
        hotFragment.flSearch.setBackgroundColor(getResources().getColor(R.color.colorWhite));
    }


    public void setSubjects(ArrayList<Subjects> mList) {
        this.mList = mList;
    }

    public ArrayList<Subjects> getSubjects() {
        return mList;
    }

    /**
     * 下载热更新补丁
     */
    private void startPatch() {
        TinkerPatch.with().fetchPatchUpdate(true);
    }

    private void initFragment() {
        hotFragment = HotFragment.getInstance();
        movieFragment = MovieFragment.getInstance();
        oneselfFragment = OneselfFragment.getInstance();
        welfareFragment = WelfareFragment.getInstance();

    }


    private void initView() {
        currentFragment = hotFragment;
        getSupportFragmentManager()
                .beginTransaction().add(R.id.fl_main, currentFragment, StringUtils.getClassName(HotFragment.class))
                .addToBackStack(StringUtils.getClassName(HotFragment.class))
                .commit();
        mListFragment.add(hotFragment);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_hot:
                if (!item.isChecked()) {
                    switchFragment(hotFragment);
                    return true;
                }
            case R.id.main_movie:
                if (!item.isChecked()) {
                    switchFragment(movieFragment);
                    return true;
                }
            case R.id.main_oneself:
                if (!item.isChecked()) {
                    switchFragment(oneselfFragment);
                    return true;
                }
            case R.id.main_welfare:
                if (!item.isChecked()) {
                    switchFragment(welfareFragment);
                    return true;
                }
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
                try {
                    System.exit(0);
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
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


    /**
     * Fragment切换
     *
     * @param targetFragment 要显示的Fragment
     */
    private void switchFragment(Fragment targetFragment) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        if (!mListFragment.contains(targetFragment)) {
            transaction
                    .hide(currentFragment)
                    .add(R.id.fl_main, targetFragment)
                    .commit();
            mListFragment.add(targetFragment);
        } else {
            transaction
                    .hide(currentFragment)
                    .show(targetFragment)
                    .commit();

        }
        currentFragment = targetFragment;

        if (currentFragment.equals(welfareFragment)) {
            StatusUtils.setStatusImage(this, true);
        } else if (currentFragment.equals(oneselfFragment)) {
            StatusUtils.setStatusImage(this, R.color.transparent, false);
        } else {
            StatusUtils.tabSwitch(true, this);
        }
    }


}
