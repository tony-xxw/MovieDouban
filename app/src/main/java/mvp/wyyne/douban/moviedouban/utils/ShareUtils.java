package mvp.wyyne.douban.moviedouban.utils;

import android.content.Context;
import android.content.Intent;

import mvp.wyyne.douban.moviedouban.R;

/**
 * 分享工具类
 *
 * @author Wynne
 * @date 2018/5/16
 */

public class ShareUtils {

    /**
     * 分享文本内容
     *
     * @param context 上下文
     */
    public static void shareTextContent(Context context) {
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Share");
        sendIntent.setType("text/plan");
        String title = "";
        //强制分享多个应用,如果想分享给默认应用则不需要通过createChoose来获取Intent
        Intent shareIntent = Intent.createChooser(sendIntent, title);
        //至少存在一个处理项
        if (sendIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(shareIntent);
        }
    }


    /**
     * 分享二进制内容
     *
     * @param context    上下文
     * @param uriToImage 图片url
     */
    public static void shareBinaryContent(Context context, String uriToImage) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_STREAM, uriToImage);
        sendIntent.setType("image/jpeg");
        Intent shareIntent = Intent.createChooser(sendIntent, "");
        if (sendIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(shareIntent);
        }

    }
}
