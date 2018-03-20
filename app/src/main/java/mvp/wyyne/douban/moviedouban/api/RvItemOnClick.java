package mvp.wyyne.douban.moviedouban.api;

/**
 * @author XXW
 * @date 2017/6/18
 */

public interface RvItemOnClick {
    /**
     * Rv点击事件回调
     *
     * @param position
     * @param tag
     */
    void onItemClick(int position, String tag);
}
