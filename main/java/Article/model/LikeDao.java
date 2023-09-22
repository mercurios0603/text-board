package Article.model;

import java.util.ArrayList;

public class LikeDao {

    ArrayList<Like> likes = new ArrayList<>();

    // 입력한 번호에 해당하는 Article을 반환하는 메서드
    public Like findlikeById(int postidx) {

        Like target = null;

        for (int i = 0; i < likes.size(); i++) {
            Like like = likes.get(i);
            if (postidx == like.getArticleId()) {
                target = like;
            }
        }
        return target;
    }
}
