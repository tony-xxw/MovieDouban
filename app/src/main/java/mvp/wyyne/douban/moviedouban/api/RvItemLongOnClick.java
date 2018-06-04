package mvp.wyyne.douban.moviedouban.api;

/**
 * 点击长按事件
 *
 * @author Wynne
 * @date 2018/6/4
 */

public interface RvItemLongOnClick {

    /**
     * 长按事件
     *
     * @param position position
     * @param tag      tag
     */
    void onItemLongClick(int position, String tag);
}
