package Article.model;

import java.util.ArrayList;


public class CommentDao {
    ArrayList<Comment> comments = new ArrayList<>();

    int lastReplyId = 1;

    public ArrayList<Comment> findcommentById(int postidx) {

        ArrayList<Comment> aaa = new ArrayList<>();

        for (int i = 0; i < comments.size(); i++) {
            Comment bbb = comments.get(i);
            if (postidx == bbb.getCommentid()) {
                aaa.add(bbb);
            }
        }
        return aaa;
    }

    public void insert(int articleId, String user, String content) {
        Comment reply = new Comment(lastReplyId, articleId, user, content);
        comments.add(reply);
        lastReplyId++;
    }
}
