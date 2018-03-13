package mvp.wyyne.douban.moviedouban.utils;

import java.util.List;

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
}
