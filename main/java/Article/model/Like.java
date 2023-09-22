package Article.model;

public class Like {

    private int articleid;

    private String articleuser;

    private int likechoose;


    public Like(int articleId, String articleUser, int likechoose) {
        this.articleid = articleid;
        this.articleuser = articleuser;
        this.likechoose = likechoose;
    }

    public int getArticleId() {
        return articleid;
    }

    public void setArticleId(int articleid) {
        this.articleid = articleid;
    }

    public String getArticleUser() {
        return articleuser;
    }

    public void setArticleUser(String articleuser) {
        this.articleuser = articleuser;
    }

    public int getLikeChoose() {
        return likechoose;
    }

    public void setLikeChoose(int likechoose) {
        this.likechoose = likechoose;
    }

}
