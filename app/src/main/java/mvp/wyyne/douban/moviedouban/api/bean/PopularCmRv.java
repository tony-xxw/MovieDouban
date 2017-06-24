package mvp.wyyne.douban.moviedouban.api.bean;

/**
 * Created by XXW on 2017/6/24.
 */

public class PopularCmRv {

    /**
     * rating : {"max":5,"value":1,"min":0}
     * title : 12个不看这部烂片的理由
     * subject_id : 25824686
     * author : {"uid":"zhangzongqian","avatar":"https://img3.doubanio.com/icon/u3995080-211.jpg","signature":"I\u2019m back!","alt":"https://www.douban.com/people/zhangzongqian/","id":"3995080","name":"亵渎电影"}
     * summary : 本人首发于亵渎电影的公共号“亵渎叔推荐”（V号：xiedudianying）   星级推荐：负分滚粗  如果不是为了写这篇稿子，我绝对不会第一时间跑去看这部电影，因为它的烂番茄评分已经是系列最低。  这可不是那种烂，就...
     * alt : https://movie.douban.com/review/8620502/
     * id : 8620502
     */

    private UserRating rating;
    private String title;
    private String subject_id;
    private User author;
    private String summary;
    private String alt;
    private String id;

    public UserRating getRating() {
        return rating;
    }

    public void setRating(UserRating rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static class RatingBean {
        /**
         * max : 5
         * value : 1
         * min : 0
         */

        private int max;
        private int value;
        private int min;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }
    }

    public static class AuthorBean {
        /**
         * uid : zhangzongqian
         * avatar : https://img3.doubanio.com/icon/u3995080-211.jpg
         * signature : I’m back!
         * alt : https://www.douban.com/people/zhangzongqian/
         * id : 3995080
         * name : 亵渎电影
         */

        private String uid;
        private String avatar;
        private String signature;
        private String alt;
        private String id;
        private String name;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
