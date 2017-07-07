package mvp.wyyne.douban.moviedouban.api.bean;

import java.util.List;

/**
 * Created by XXW on 2017/7/8.
 */

public class Works {

    private List<String> roles;

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public Subjects getSubject() {
        return subject;
    }

    public void setSubject(Subjects subject) {
        this.subject = subject;
    }

    private Subjects subject;
}
