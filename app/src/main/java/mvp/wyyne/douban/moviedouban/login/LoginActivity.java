package mvp.wyyne.douban.moviedouban.login;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import mvp.wyyne.douban.moviedouban.AndroidApplication;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.home.MainActivity;
import mvp.wyyne.douban.moviedouban.home.base.BaseActivity;
import mvp.wyyne.douban.moviedouban.utils.StatusUtils;

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
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) {
            View view = inflater.inflate(R.layout.toast_login, null);
            Toast toast = new Toast(this);
            toast.setGravity(Gravity.CENTER, 0, 200);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(view);
            toast.show();
        }
    }
}
