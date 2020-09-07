package mvp.wyyne.douban.moviedouban.oneself.read;


import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import butterknife.BindView;
import mvp.wyyne.douban.moviedouban.AndroidApplication;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.home.base.BaseFragment;

/**
 * 我的- 看过
 *
 * @author XXW
 * @date 2017/7/22
 */

public class OneselfReadFragment extends BaseFragment {

    @BindView(R.id.tv_filtrate)
    TextView tvFiltrate;
    @BindView(R.id.ll_read_content)
    LinearLayout llReadContent;

    public static Fragment getInstance() {
        OneselfReadFragment fragment = new OneselfReadFragment();
        return fragment;
    }

    @Override
    protected void refresh() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_read_oneself;
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
            llReadContent.setVisibility(View.VISIBLE);
        } else {
            llReadContent.setVisibility(View.GONE);
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
