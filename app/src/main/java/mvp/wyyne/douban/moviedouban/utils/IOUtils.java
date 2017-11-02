package mvp.wyyne.douban.moviedouban.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by XXW on 2017/11/2.
 */

public class IOUtils {
    public static void close(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
