package mvp.wyyne.douban.moviedouban.utils;

import java.util.List;

/**
 * Created by XXW on 2017/6/22.
 */

public class StringUtils {


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
}
