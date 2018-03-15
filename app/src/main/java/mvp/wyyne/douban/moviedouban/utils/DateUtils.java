package mvp.wyyne.douban.moviedouban.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * 时间获取工具类
 *
 * @author Wynne
 * @date 2018/3/15
 */

public class DateUtils {


    /**
     * 数字版本
     *
     * @return 获取当前年月日
     */
    public static String getCurrentDateYMT() {
        SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
        return date.format(new Date());
        /*Calendar calendar = Calendar.getInstance();

        return String.valueOf(calendar.get(Calendar.YEAR)) +
                String.valueOf(calendar.get(Calendar.MONTH) + 1) +
                String.valueOf(calendar.get(Calendar.DATE));*/
    }
}
