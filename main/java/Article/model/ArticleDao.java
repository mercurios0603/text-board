package Article.model;


import Article.controller.ArticleController;
import Article.model.Article;
import Article.view.ArticleView;
import util.Util;

import java.util.ArrayList;
import java.util.Scanner;

public class ArticleDao {

    // 업계에서 "저장소"에 대해 많이 쓰이는 명칭 크게 두 가지
    // Dao 또는 Repository
    // 말 그대로 저장소이기 때문에 Article 클래스의 형태를 갖는 변수명 articles의 ArrayList를 생성.
    ArrayList<Article> articles = new ArrayList<>();

    ArticleView articleView = new ArticleView();

    CommentDao commentDao = new CommentDao();

    LikeDao likeDao = new LikeDao();

    Scanner scan = new Scanner(System.in);

    int listid = 4; // articles.size();

    // 생성자 초기값.
    public ArticleDao() {
        Article article1 = new Article(1,"홍길동",  "안녕하세요 반갑습니다", "질문이에요", "2023.08.31 14:01:23", "2023.08.31 14:05:46", 156);
        Article article2 = new Article(2, "김유신", "자바 질문좀 할게요~", "질문내용입니다", "2023.09.04 15:43:36", "2023.09.04 15:47:55", 350);
        Article article3 = new Article(3, "강감찬", "안녕용. 정처기 따야되나요?", "스펙 질문입니다", "2023.09.11 09:23:05", "2023.09.11 09:30:25", 117);
        articles.add(article1);
        articles.add(article2);
        articles.add(article3);
    }

    public void insert(String sessionname, String title, String contents) {

        // articles와 article은 다른 것입니다.

        // articles는 ArrayList 타입의 컬렉션입니다.
        // 이는 여러 개의 Article 객체를 저장할 수 있는 동적 배열 형태의 자료 구조입니다.
        // articles에는 여러 개의 Article 객체가 저장될 수 있으며, 이 객체들을 관리하는 용도로 사용됩니다.

        // article은 Article 클래스의 객체 하나입니다.
        // 이 객체는 listnumber, title, contents와 같은 멤버 변수와 메서드를 포함하며,
        // articles에 추가되거나 다루어질 수 있는 하나의 게시글을 나타냅니다.
        // 즉, articles는 여러 개의 Article 객체를 담을 수 있는 컬렉션(리스트)이고,
        // article은 그 중 하나의 Article 객체입니다.

        Article article = new Article(listid, sessionname, title, contents, Util.getCurrentTime(), Util.getCurrentTime(), 0);
        articles.add(article);
        System.out.println(listid + "번 게시물이 등록되었습니다.");

        // 글을 작성할 (add) 때마다 listid 값을 1씩 증가.
        listid++;

        // 게시물 기능의 일부 -> 비지니스 로직, 서비스 로직 (키워드만 기억해 둘 것)
        // 예를 들어. 조회수가 증가하는 것.
        // 이것들은 데이터를 다루는 클래스에 포함시키지 않는 것이 일반적이다.
    }

    public ArrayList<Article> findAllArticles() {
        return articles;
    }

    // 입력한 번호에 해당하는 Article을 반환하는 메서드
    public Article findById(int postidx) {

        Article target = null;

        for (int i = 0; i < articles.size(); i++) {
            Article article = articles.get(i);
            if (postidx == article.getArticleIndex()) {
                target = article;
            }
        }

        return target;
    }



    public ArrayList<Article> findByTitle(String keyword) {

        ArrayList<Article> searchKeyword = new ArrayList<>();

        // 기존의 게시물들이 저장된 articles를 순차적으로 검색하면서 제목에(getTitle()에)
        // keyword가 포함된(contains(keyword)) 배열을 찾아 존재하면 (참(True)이면)
        // 같은 구조를 갖는 searchByKeyword 배열에 조건문에서 찾아낸 배열을 넣어서 반환(return)하라는 뜻임.
        // 즉 배열을 반환한 셈.

        for (int i = 0; i < articles.size(); i++) {
            Article article = articles.get(i);
            String title = article.getTitle();

            if (title.contains(keyword)) {
                searchKeyword.add(article);
            }
        }

        return searchKeyword;
    }

    public void likeprocess(Member membersession, Article article) {

        // 시나리오를 고민해볼 것!!! (아래 시나리오는 likeprocess 비즈니스 로직에 반영되어야 함)
        // 좋아요 버튼을 눌렀을 때
        // (1) 먼저 회원고유식별번호(회원아이디)와 게시글 번호를 가지고 좋아요 DB를 검색을 함 (둘다 일치 AND, DAO에서 진행)
        // (2) 없는 경우. 이 게시글에 대한 새로운 객체를 생성해서 좋아요 배열을 삽입함. (DAO에서 진행)
        // (3) 있는 경우, 이 게시글에 대한 좋아요 배열을 제거함. (DAO에서 진행)
        // DB에는 무조건 어떤 게시글에, 어떤 회원이, 좋아요를 누른것만 남음 (좋아요 0은 remove로 삭제됨)
        // 배열 그자체가 좋아요인 것이다. 별도의 변수 설정은 필요없다. 좋아요의 갯수는 곧 배열의 개수이다.

        Like like = likeDao.getLikeByArticleIdAndMemberId(article.getArticleIndex(), membersession.getMemberId());

        if(like == null) {
            likeDao.insert(article.getArticleIndex(), membersession.getMemberId());
            System.out.println("해당 게시물을 좋아합니다.");
        } else {
            likeDao.delete(like);
            System.out.println("해당 게시물의 좋아요를 해제합니다.");
        }
    }

}
