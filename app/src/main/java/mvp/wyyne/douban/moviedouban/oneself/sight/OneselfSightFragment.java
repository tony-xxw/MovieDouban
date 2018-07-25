package mvp.wyyne.douban.moviedouban.oneself.sight;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import mvp.wyyne.douban.moviedouban.AndroidApplication;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.home.base.BaseFragment;

/**
 * 我的 -在看
 *
 * @author XXW
 * @date 2017/7/22
 */

public class OneselfSightFragment extends BaseFragment {
    @BindView(R.id.tv_filtrate)
    TextView tvFiltrate;
    @BindView(R.id.ll_sight_content)
    LinearLayout llSightContent;


    public static Fragment getInstance() {
        OneselfSightFragment fragment = new OneselfSightFragment();
        return fragment;
    }


    @Override
    protected void refresh() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sight;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void onResume() {
        super.onResume();
        handleLogin();
    }

    private void handleLogin() {
        if (AndroidApplication.getApplication().isLogin()) {
            llSightContent.setVisibility(View.VISIBLE);
        } else {
            llSightContent.setVisibility(View.GONE);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            mIsVisible = true;
            lazyLoad();
        } else {
            mIsVisible = false;
        }
    }

}
