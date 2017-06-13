package mvp.wyyne.douban.moviedouban.api.bean;

import java.util.List;

/**
 * Created by XXW on 2017/6/13.
 */

public class Article {


    /**
     * rating : {"max":10,"average":"7.4","numRaters":6641,"min":0}
     * author : [{"name":"雷德利·斯科特 Ridley Scott"}]
     * alt_title : 异形：契约 / 异形：圣约(港/台)
     * image : https://img3.doubanio.com/view/movie_poster_cover/ipst/public/p2459944375.jpg
     * title : Alien: Covenant
     * summary : “科幻之父”雷德利-斯科特将为他所开创的《异形》系列带来新篇章。《异形：契约》的故事发生在《普罗米修斯》10年后，一群新的宇航员乘坐着“契约号”飞船前往遥远的星系寻找殖民地，他们来到一处看似天堂般的星球，实则是黑暗、危险的地狱，在那里他们见到了“普罗米修斯”号唯一的幸存者——由迈克尔·法斯宾德饰演的生化人大卫，一场毁灭性的巨大灾难即将到来。
     * attrs : {"website":["www.foxmovies.com/movies/alien-covenant"],"pubdate":["2017-05-10(法国)","2017-05-19(美国)","2017-06-16(中国大陆)"],"language":["英语"],"title":["Alien: Covenant"],"country":["美国"],"writer":["迈克尔·格林 Michael Green","约翰·洛根 John Logan","杰克·帕格恩 Jack Paglen"],"director":["雷德利·斯科特 Ridley Scott"],"cast":["迈克尔·法斯宾德 Michael Fassbender","凯瑟琳·沃特斯顿 Katherine Waterston","比利·克鲁德普 Billy Crudup","丹尼·麦克布耐德 Danny McBride","德米安·比齐尔 Demián Bichir","卡门·艾乔戈 Carmen Ejogo","朱西·斯莫利特 Jussie Smollett","考莉·赫尔南德斯 Callie Hernandez","艾米·西米茨 Amy Seimetz","纳撒尼尔·迪安 Nathaniel Dean","亚历山大·英格兰 Alexander England","本杰明·里格比 Benjamin Rigby","乌利·拉图基孚 Uli Latukefu","泰丝·哈乌布里奇 Tess Haubrich","罗蕾莱·金 Lorelei King","哈维尔·博特 Javier Botet","詹姆斯·弗兰科 James Franco","盖·皮尔斯 Guy Pearce","劳米·拉佩斯 Noomi Rapace"],"movie_duration":["122分钟","116分钟(中国大陆)"],"year":["2017"],"movie_type":["科幻","惊悚","恐怖"]}
     * id : https://api.douban.com/movie/11803087
     * mobile_link : https://m.douban.com/movie/subject/11803087/
     * alt : https://movie.douban.com/movie/11803087
     * tags : [{"count":3487,"name":"科幻"},{"count":2219,"name":"惊悚"},{"count":1938,"name":"美国"},{"count":1365,"name":"2017"},{"count":948,"name":"恐怖"},{"count":568,"name":"冒险"},{"count":496,"name":"悬疑"},{"count":474,"name":"外星人"}]
     */

    private Rating rating;
    private String alt_title;
    private String image;
    private String title;
    private String summary;
    private ArticleAttrs attrs;
    private String id;
    private String mobile_link;
    private String alt;
    private List<AuthorBean> author;
    private List<TagsBean> tags;

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public String getAlt_title() {
        return alt_title;
    }

    public void setAlt_title(String alt_title) {
        this.alt_title = alt_title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public ArticleAttrs getAttrs() {
        return attrs;
    }

    public void setAttrs(ArticleAttrs attrs) {
        this.attrs = attrs;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile_link() {
        return mobile_link;
    }

    public void setMobile_link(String mobile_link) {
        this.mobile_link = mobile_link;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public List<AuthorBean> getAuthor() {
        return author;
    }

    public void setAuthor(List<AuthorBean> author) {
        this.author = author;
    }

    public List<TagsBean> getTags() {
        return tags;
    }

    public void setTags(List<TagsBean> tags) {
        this.tags = tags;
    }

    public static class RatingBean {
        /**
         * max : 10
         * average : 7.4
         * numRaters : 6641
         * min : 0
         */

        private int max;
        private String average;
        private int numRaters;
        private int min;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public String getAverage() {
            return average;
        }

        public void setAverage(String average) {
            this.average = average;
        }

        public int getNumRaters() {
            return numRaters;
        }

        public void setNumRaters(int numRaters) {
            this.numRaters = numRaters;
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
         * name : 雷德利·斯科特 Ridley Scott
         */

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class TagsBean {
        /**
         * count : 3487
         * name : 科幻
         */

        private int count;
        private String name;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
