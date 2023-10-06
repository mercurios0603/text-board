package Article.model;

import Article.controller.ArticleController;
import Article.view.ArticleView;
import util.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class ArticleDao {

    // 업계에서 "저장소"에 대해 많이 쓰이는 명칭 크게 두 가지
    // Dao 또는 Repository
    // 말 그대로 저장소이기 때문에 Article 클래스의 형태를 갖는 변수명 articles의 ArrayList를 생성.
    ArrayList<Article> articles = new ArrayList<>();

    ArticleView articleView = new ArticleView();


    int listid = 8; // articles.size();

    // 생성자 초기값.
    public ArticleDao() {
        Article article1 = new Article(1, "홍길동", "안녕하세요 반갑습니다", "질문이에요", "2023.08.31 14:01:23", "2023.08.31 14:05:46", 156);
        Article article2 = new Article(2, "김유신", "자바 질문좀 할게요~", "질문내용입니다", "2023.09.04 15:43:36", "2023.09.04 15:47:55", 510);
        Article article3 = new Article(3, "강감찬", "안녕이요. 정처기 따야되나요?", "스펙 질문입니다", "2023.09.06 19:23:35", "2023.09.07 09:30:25", 117);
        Article article4 = new Article(4, "임꺽정", "출석체크 합니다.", "좋은 하루 보내세요", "2023.06.11 14:14:05", "2023.06.12 09:30:25", 356);
        Article article5 = new Article(5, "한석봉", "붓글씨 글씨체 추천좀", "급함 빨리.", "2023.09.14 10:25:55", "2023.09.15 09:30:25", 287);
        Article article6 = new Article(6, "정약용", "유머글 하나 올리고 갑니다", "유하하하하", "2023.09.11 20:16:45", "2023.09.14 09:30:25", 134);
        Article article7 = new Article(7, "윤동주", "별 헤는 밤입니다.", "별 세고 싶은 날이네요", "2023.04.05 11:23:05", "2023.04.11 09:30:25", 477);
        Article article8 = new Article(8, "작성자8", "제목8.", "내용8", "2023.04.05 11:23:05", "2023.04.11 09:30:25", 345);
        Article article9 = new Article(9, "작성자9", "제목9.", "내용9", "2023.04.05 11:23:05", "2023.04.11 09:30:25", 124);
        Article article10 = new Article(10, "작성자10", "제목10.", "내용10", "2023.04.05 11:23:05", "2023.04.11 09:30:25", 872);
        Article article11 = new Article(11, "작성자11", "제목11.", "내용11", "2023.04.05 11:23:05", "2023.04.11 09:30:25", 632);
        Article article12 = new Article(12, "작성자12", "제목12.", "내용12", "2023.04.05 11:23:05", "2023.04.11 09:30:25", 120);
        Article article13 = new Article(13, "작성자13", "제목13.", "내용13", "2023.04.05 11:23:05", "2023.04.11 09:30:25", 445);
        Article article14 = new Article(14, "작성자14", "제목14.", "내용14", "2023.04.05 11:23:05", "2023.04.11 09:30:25", 777);
        Article article15 = new Article(15, "작성자15", "제목15.", "내용15", "2023.04.05 11:23:05", "2023.04.11 09:30:25", 231);
        Article article16 = new Article(16, "작성자16", "제목16.", "내용16", "2023.04.05 11:23:05", "2023.04.11 09:30:25", 236);
        Article article17 = new Article(17, "작성자17", "제목17.", "내용17", "2023.04.05 11:23:05", "2023.04.11 09:30:25", 442);
        Article article18 = new Article(18, "작성자18", "제목18.", "내용18", "2023.04.05 11:23:05", "2023.04.11 09:30:25", 477);
        Article article19 = new Article(19, "작성자19", "제목19.", "내용19", "2023.04.05 11:23:05", "2023.04.11 09:30:25", 551);
        Article article20 = new Article(20, "작성자20", "제목20.", "내용20", "2023.04.05 11:23:05", "2023.04.11 09:30:25", 921);
        Article article21 = new Article(21, "작성자21", "제목21.", "내용21", "2023.04.05 11:23:05", "2023.04.11 09:30:25", 816);
        articles.add(article1);
        articles.add(article2);
        articles.add(article3);
        articles.add(article4);
        articles.add(article5);
        articles.add(article6);
        articles.add(article7);
        articles.add(article8);
        articles.add(article9);
        articles.add(article10);
        articles.add(article11);
        articles.add(article12);
        articles.add(article13);
        articles.add(article14);
        articles.add(article15);
        articles.add(article16);
        articles.add(article17);
        articles.add(article18);
        articles.add(article19);
        articles.add(article20);
        articles.add(article21);
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

    public void delete(Article article, String confirmdelete) {

        if (confirmdelete.equals("y")) {
            articles.remove(article);
            System.out.println("삭제가 완료되었습니다.");
            articleView.printArticles(articles);
        } else if (confirmdelete.equals("n")) {
            System.out.println("메인 화면으로 돌아갑니다");
        }
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
}
