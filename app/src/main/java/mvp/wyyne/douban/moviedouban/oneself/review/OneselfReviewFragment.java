package mvp.wyyne.douban.moviedouban.oneself.review;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import mvp.wyyne.douban.moviedouban.AndroidApplication;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.home.base.BaseFragment;

/**
 * 我的 - 影评
 *
 * @author XXW
 * @date 2017/7/22
 */

public class OneselfReviewFragment extends BaseFragment {

    @BindView(R.id.tv_number_subject)
    TextView tvNumberSubject;
    @BindView(R.id.tv_filtrate)
    TextView tvFiltrate;
    @BindView(R.id.ll_tab_content)
    LinearLayout llTabContent;
    @BindView(R.id.ll_review_content)
    LinearLayout llReviewContent;
    @BindView(R.id.tv_empty)
    TextView tvEmpty;


    public static Fragment getInstance() {
        OneselfReviewFragment fragment = new OneselfReviewFragment();
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
        return R.layout.fragment_review;
    }

    @Override
    protected void initView() {


    }

    private void handleLogin() {
        if (AndroidApplication.getApplication().isLogin()) {
            llReviewContent.setVisibility(View.VISIBLE);
            tvNumberSubject.setText("0篇");
            tvEmpty.setText("你还没有发表影评");
            llTabContent.setVisibility(View.GONE);
        } else {
            llReviewContent.setVisibility(View.GONE);
        }
    }


}
