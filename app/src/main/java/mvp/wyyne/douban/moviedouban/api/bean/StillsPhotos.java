package mvp.wyyne.douban.moviedouban.api.bean;

/**
 * Created by XXW on 2017/7/2.
 */

public class StillsPhotos {

    /**
     * photos_count : 303
     * thumb : https://img1.doubanio.com/view/photo/thumb/public/p2463952118.jpg
     * icon : https://img1.doubanio.com/view/photo/icon/public/p2463952118.jpg
     * author : {"uid":"MarvelUniverse","avatar":"https://img3.doubanio.com/icon/u104573353-2.jpg","signature":"微博@MarvelUniverse","alt":"https://www.douban.com/people/MarvelUniverse/","id":"104573353","name":"MarvelUniverse"}
     * created_at : 2017-06-19 09:52:14
     * album_id : 155710745
     * cover : https://img1.doubanio.com/view/photo/albumcover/public/p2463952118.jpg
     * id : 2463952118
     * prev_photo : 2463952170
     * album_url : https://movie.douban.com/subject/25824686/photos
     * comments_count : 13
     * image : https://img1.doubanio.com/view/photo/photo/public/p2463952118.jpg
     * recs_count : 2
     * position : 79
     * alt : https://movie.douban.com/photos/photo/2463952118/
     * album_title : Transformers 5(25824686)
     * next_photo : 2463952117
     * subject_id : 25824686
     * desc :
     */

    private int photos_count;
    private String thumb;
    private String icon;
    private User author;
    private String created_at;
    private String album_id;
    private String cover;
    private String id;
    private String prev_photo;
    private String album_url;
    private int comments_count;
    private String image;
    private int recs_count;
    private int position;
    private String alt;
    private String album_title;
    private String next_photo;
    private String subject_id;
    private String desc;

    public int getPhotos_count() {
        return photos_count;
    }

    public void setPhotos_count(int photos_count) {
        this.photos_count = photos_count;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(String album_id) {
        this.album_id = album_id;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrev_photo() {
        return prev_photo;
    }

    public void setPrev_photo(String prev_photo) {
        this.prev_photo = prev_photo;
    }

    public String getAlbum_url() {
        return album_url;
    }

    public void setAlbum_url(String album_url) {
        this.album_url = album_url;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getRecs_count() {
        return recs_count;
    }

    public void setRecs_count(int recs_count) {
        this.recs_count = recs_count;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getAlbum_title() {
        return album_title;
    }

    public void setAlbum_title(String album_title) {
        this.album_title = album_title;
    }

    public String getNext_photo() {
        return next_photo;
    }

    public void setNext_photo(String next_photo) {
        this.next_photo = next_photo;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static class AuthorBean {
        /**
         * uid : MarvelUniverse
         * avatar : https://img3.doubanio.com/icon/u104573353-2.jpg
         * signature : 微博@MarvelUniverse
         * alt : https://www.douban.com/people/MarvelUniverse/
         * id : 104573353
         * name : MarvelUniverse
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
