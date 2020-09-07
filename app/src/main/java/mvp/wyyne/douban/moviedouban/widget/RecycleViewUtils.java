package mvp.wyyne.douban.moviedouban.widget;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;


/**
 * @author XXW
 * @date 2017/6/26
 */

public class RecycleViewUtils {

    public static View addHeadView(int rouseId, Context context, RecyclerView recyclerView) {
        return LayoutInflater.from(context).inflate(rouseId, recyclerView, false);
    }

    public static View addFooterView(int rouseId, Context context) {
        return LayoutInflater.from(context).inflate(rouseId, null);
    }
}
