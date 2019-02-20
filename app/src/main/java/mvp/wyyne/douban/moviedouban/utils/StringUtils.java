package mvp.wyyne.douban.moviedouban.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import static com.wynne.common.Constant.LAKH;
import static com.wynne.common.Constant.THOUSAND;

/**
 * @author XXW
 * @date 2017/6/22
 */

public class StringUtils {


    /**
     * 演员叠加
     *
     * @param list
     * @return
     */
    public static String getString(List<String> list) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            if (i != list.size() - 1) {
                buffer.append(list.get(i) + " /");
            } else {
                buffer.append(list.get(i));
            }
        }
        return buffer.toString();
    }


    /**
     * 处理时间
     *
     * @param time
     * @return
     */
    public static String handleTime(String time) {
        String[] entmy = time.split(" ");
        Integer end = Integer.valueOf(entmy[0]);
        return time.substring(0, end);
    }


    public static String getClassName(Object object) {
        return object.getClass().getSimpleName();
    }


    /**
     * 百分比长度
     *
     * @param num 占有量
     * @param sum 总量
     * @return
     */
    public static String getNumberFormat(int num, int sum) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(2);
        return numberFormat.format((float) num / (float) sum * 100);
    }


    public static int getDoubleToInt(double count) {
        return Double.valueOf(count).intValue();
    }


    /**
     * @return 观影人数
     */
    public static String  getAttendance(int count) {
        DecimalFormat df = new DecimalFormat("######0.0");
        DecimalFormat dd = new DecimalFormat("######0.0");

        String attendance = null;
        if (count > LAKH) {
            attendance = df.format(count / 10000.0) + "万人观看";
        } else if (count > THOUSAND) {
            attendance = dd.format(count / 1000.0) + "万人观看";
        } else {
            attendance = count + "人观看";
        }

        return attendance;
    }
}
