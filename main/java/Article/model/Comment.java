package Article.model;

import java.util.ArrayList;

public class Comment {

    // 댓글이 작성된 글번호
    private int cid;

    // 댓글 작성자
    private String commentuser;

    // 댓글 내용
    private String replycontent;

    public Comment(int cid, String commentuser, String replycontent) {
        this.cid = cid;
        this.commentuser = commentuser;
        this.replycontent = replycontent;
    }

    public int getCommentid() {
        return cid;
    }

    public void setCommentid(int cid) {
        this.cid = cid;
    }

    public String getCommentUser() {
        return commentuser;
    }

    public void setCommentUser(String commentuser) {
        this.commentuser = commentuser;
    }

    public String getReplyContent() {
        return replycontent;
    }

    public void setgetReplyContent(String replycontent) {
        this.replycontent = replycontent;
    }
}
