package Article.model;

import java.util.ArrayList;

public class Comment {

    // 댓글이 작성된 글번호
    private int cid;

    // 댓글 내용
    private String replycontent;

    public Comment(int cid, String replycontent) {
        this.cid = cid;
        this.replycontent = replycontent;
    }

    public int getCommentid() {
        return cid;
    }

    public void setCommentid(int cid) {
        this.cid = cid;
    }

    public String getReplyContent() {
        return replycontent;
    }

    public void setgetReplyContent(String replycontent) {
        this.replycontent = replycontent;
    }
}
