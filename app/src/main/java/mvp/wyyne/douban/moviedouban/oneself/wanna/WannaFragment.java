package mvp.wyyne.douban.moviedouban.oneself.wanna;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import mvp.wyyne.douban.moviedouban.AndroidApplication;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.home.base.BaseFragment;

/**
 * 我的-想看
 *
 * @author XXW
 * @date 2017/7/22
 */

public class WannaFragment extends BaseFragment {
    @BindView(R.id.tv_filtrate)
    TextView tvFiltrate;
    @BindView(R.id.ll_wanna_content)
    LinearLayout llWannaContent;


    public static Fragment getInstance() {
        WannaFragment fragment = new WannaFragment();
        return fragment;
    }

    @Override
    protected void refresh() {

    }

    @Override
    public void onResume() {
        super.onResume();
        handleLogin();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wanna;
    }

    @Override
    protected void initView() {

    }

    private void handleLogin() {
        if (AndroidApplication.getApplication().Login()) {
            llWannaContent.setVisibility(View.VISIBLE);
        }else {
            llWannaContent.setVisibility(View.GONE);
        }
    }


}
