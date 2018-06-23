package mvp.wyyne.douban.moviedouban.api.bean;

/**
 *
 * @author Wynne
 * @date 2018/6/23
 */

public class FlowBean {

    private String name;
    private int id;

    public FlowBean(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
