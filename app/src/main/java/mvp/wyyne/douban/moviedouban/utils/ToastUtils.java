package mvp.wyyne.douban.moviedouban.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;


/**
 * Toast 工具类
 *
 * @author Wynne
 * @date 2018/5/2
 */

public class ToastUtils {


    public Toast customToast(Context context, int resouce) {
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.TOP, 0, 200);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
        return toast;
    }
}
