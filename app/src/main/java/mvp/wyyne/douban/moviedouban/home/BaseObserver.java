package mvp.wyyne.douban.moviedouban.home;

import android.net.ParseException;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonParseException;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import mvp.wyyne.douban.moviedouban.detail.IDetailMain;

/**
 * Observer 封装体
 *
 * @author XXW
 * @date 2017/6/3
 */

public abstract class BaseObserver<T> implements Observer<T> {
    public static final String TAG = "BaseObserver";


    private IMain mMain;

    protected BaseObserver(IMain mMain) {
        this.mMain = mMain;
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (mMain != null) {
            switchInterface(false);
        }
    }

    @Override
    public void onNext(T response) {
        onSuccess(response);
        onFinish();
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof HttpException) {
            onException(ExceptionReason.BAD_NETWORK);
        } else if (e instanceof ConnectException
                || e instanceof UnknownHostException) {
            onException(ExceptionReason.CONNECT_ERROR);
        } else if (e instanceof InterruptedIOException) {
            onException(ExceptionReason.CONNECT_TIMEOUT);
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            onException(ExceptionReason.PARSE_ERROR);
        } else {
            onException(ExceptionReason.UNKNOWN_ERROR);
        }
        onFinish();
    }

    @Override
    public void onComplete() {
        if (mMain != null) {
            switchInterface(true);
        }
    }


    /**
     * 请求成功
     *
     * @param response
     */
    abstract public void onSuccess(T response);

    public void onFinish() {

    }


    /**
     * 请求异常
     *
     * @param reason
     */
    public void onException(ExceptionReason reason) {
        switch (reason) {
            case CONNECT_ERROR:
                Log.e(TAG, "===连接错误===");
                break;
            case CONNECT_TIMEOUT:
                Log.e(TAG, "===连接超时===");
                break;
            case BAD_NETWORK:
                Log.e(TAG, "===网络问题===");
                break;
            case PARSE_ERROR:
                Log.e(TAG, "===解析失败===");
                break;
            case UNKNOWN_ERROR:
            default:
                Log.e(TAG, "===未知错误===");
                break;
        }
    }

    /**
     * 请求网络失败原因
     */
    public enum ExceptionReason {
        /**
         * 解析数据失败
         */
        PARSE_ERROR,
        /**
         * 网络问题
         */
        BAD_NETWORK,
        /**
         * 连接错误
         */
        CONNECT_ERROR,
        /**
         * 连接超时
         */
        CONNECT_TIMEOUT,
        /**
         * 未知错误
         */
        UNKNOWN_ERROR,
    }


    private void switchInterface(boolean isFinish) {
        if (isFinish) {
            if (mMain instanceof IDetailMain) {
                ((IDetailMain) mMain).initMovieGrade();
            }
            mMain.hide();
        } else {
            if (mMain instanceof IDetailMain) {

            }
            mMain.show();
        }
    }

}
