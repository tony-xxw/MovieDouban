package mvp.wyyne.douban.moviedouban.oneself.cast;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import mvp.wyyne.douban.moviedouban.AndroidApplication;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.home.base.BaseFragment;

/**
 * 我的-影人
 *
 * @author XXW
 * @date 2017/7/22
 */

public class CastFragment extends BaseFragment {

    @BindView(R.id.tv_number_subject)
    TextView tvNumberSubject;
    @BindView(R.id.tv_filtrate)
    TextView tvFiltrate;
    @BindView(R.id.ll_tab_content)
    LinearLayout llTabContent;
    @BindView(R.id.ll_cast_content)
    LinearLayout llCastContent;

    public static Fragment getInstance() {
        CastFragment fragment = new CastFragment();
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
        return R.layout.fragment_cast;
    }

    @Override
    protected void initView() {
    }

    private void handleLogin() {
        if (AndroidApplication.getApplication().Login()) {
            llCastContent.setVisibility(View.VISIBLE);
            tvNumberSubject.setText("0位");
            llTabContent.setVisibility(View.GONE);
        }else {
            llCastContent.setVisibility(View.GONE);
        }
    }


}
