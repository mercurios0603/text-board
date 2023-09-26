package Article.model;

import java.util.ArrayList;

public class LikeDao {

    public ArrayList<Like> likes = new ArrayList<>();

    public LikeDao() {
    }

    public Like getLikeByArticleIdAndMemberId(int articleid, String memberid) {

        for (int i = 0; i < likes.size(); i++) {
            Like like = likes.get(i);
            if (like.getArticleId() == articleid && like.getLikeUser() == memberid) {
                return like;
            }
        }
        return null;
    }

    public void insert(int articleId, String userId) {
        Like like = new Like(articleId, userId);
        likes.add(like);
    }

    public void delete(Like like) {
        likes.remove(like);
    }

    public int getCountOfLikeByArticleId(int articleId) {

        int count = 0;

        for (int i = 0; i < likes.size(); i++) {
            Like like2 = likes.get(i);
            if (articleId == like2.getArticleId()) {
                count = count + 1;
            }
        }
        return count;
    }
}

