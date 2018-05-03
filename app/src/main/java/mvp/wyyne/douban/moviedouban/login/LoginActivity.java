package mvp.wyyne.douban.moviedouban.login;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;

import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;
import mvp.wyyne.douban.moviedouban.AndroidApplication;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.home.MainActivity;
import mvp.wyyne.douban.moviedouban.home.base.BaseActivity;
import mvp.wyyne.douban.moviedouban.utils.StatusUtils;
import mvp.wyyne.douban.moviedouban.utils.ToastUtils;

/**
 * @author Wynne
 * @date 2018/4/24
 */

public class LoginActivity extends BaseActivity<ILoginImp> implements ILoginMain {
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_password)
    EditText etPassword;


    @Override
    protected void refresh() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }


    @Override
    protected void initView() {
        StatusUtils.setStatusBarActivity(this, false, Color.TRANSPARENT);
        mPresent = new ILoginImp(this);
    }


    @OnClick({R.id.iv_back, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_login:
                mPresent.isLogin();
                break;
            default:
                break;
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public String getPassWord() {
        return etPassword.getText().toString();
    }

    @Override
    public String getAccount() {
        return etAccount.getText().toString();
    }

    @Override
    public void toMainActivity() {
        //记录等级
        AndroidApplication.getApplication().recodeLogin();
        startActivity(new Intent(this, MainActivity.class));

    }

    @Override
    public void showToast(String msg) {
        View inflater = View.inflate(this, R.layout.toast_login, null);
        ToastUtils.getInstance(getApplicationContext()).makeToastSelfViewAnim(inflater, R.style.ToastStyle);

    }
}

