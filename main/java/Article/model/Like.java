package Article.model;

public class Like {


    private int articleid;

    private String likeuser;

    public Like(int articleid, String likeuser) {
        this.articleid = articleid;
        this.likeuser = likeuser;
    }

    public int getArticleId() {
        return articleid;
    }

    public void setArticleId(int articleid) {
        this.articleid = articleid;
    }

    public String getLikeUser() {
        return likeuser;
    }

    public void setLikeUser(String likeuser) {
        this.likeuser = likeuser;
    }


}
