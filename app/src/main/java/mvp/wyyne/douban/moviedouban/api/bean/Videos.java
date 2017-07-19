package mvp.wyyne.douban.moviedouban.api.bean;

/**
 * Created by XXW on 2017/7/19.
 */

public class Videos {

    /**
     * source : {"literal":"youku","pic":"https://img1.doubanio.com/f/movie/3bb15010bb66a89962a5faf0f7f071fec8aaa763/pics/movie/logo_youku_small.png","name":"优酷视频"}
     * sample_link : http://cps.youku.com/redirect.html?id=0000a213&url=http://v.youku.com/v_show/id_XMTUxMDcxMzA2OA==.html
     * video_id : XMTUxMDcxMzA2OA==
     * need_pay : true
     */

    private Source source;
    private String sample_link;
    private String video_id;
    private boolean need_pay;

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public String getSample_link() {
        return sample_link;
    }

    public void setSample_link(String sample_link) {
        this.sample_link = sample_link;
    }

    public String getVideo_id() {
        return video_id;
    }

    public void setVideo_id(String video_id) {
        this.video_id = video_id;
    }

    public boolean isNeed_pay() {
        return need_pay;
    }

    public void setNeed_pay(boolean need_pay) {
        this.need_pay = need_pay;
    }


}
