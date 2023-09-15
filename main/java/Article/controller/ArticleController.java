package Article.controller;

import Article.model.*;
import Article.view.ArticleView;

import java.util.ArrayList;
import java.util.Scanner;


public class ArticleController {

    ArticleView articleView = new ArticleView();
    ArticleDao articleDao = new ArticleDao();

    MemberDao memberDao = new MemberDao();

    ArrayList<Session> sessions = new ArrayList<>();

    Scanner scan = new Scanner(System.in);

    public void signup() {

        System.out.println("==== 회원 가입을 진행합니다. ====");
        System.out.print("아이디를 입력해주세요 : ");
        String inputId = scan.nextLine();
        System.out.print("비밀번호를 입력해주세요 : ");
        String inputPass = scan.nextLine();
        System.out.print("닉네임을 입력해주세요 : ");
        String inputNick = scan.nextLine();

        memberDao.signup(inputId, inputPass, inputNick);

        System.out.println("==== 회원가입이 완료되었습니다. ====");
    }

    public void signin() {
        System.out.println("==== 로그인을 진행합니다. ====");
        System.out.print("아이디 : ");
        String loginId = scan.nextLine();
        System.out.print("비밀번호 : ");
        String loginPass = scan.nextLine();

        memberDao.signin(loginId, loginPass);

    }

    public void add() {

        System.out.println(sessions.get(0).getSessionId());
        System.out.println(sessions.get(0).getSessionNickname());

        String abc = null;

        if (abc == null ) {
            System.out.println("게시물은 회원만 작성할 수 있습니다.");
        } else {

            System.out.print("게시물 제목을 입력해주세요 : ");
            String title = scan.nextLine();
            System.out.println("당신이 입력한 제목은 : " + title);

            System.out.print("게시물 내용을 입력해주세요 : ");
            String contents = scan.nextLine();
            System.out.println("당신이 입력한 내용은 : " + contents);

            articleDao.insert("임시", title, contents);

        }
    }

    public void list() {

        ArrayList<Article> articlelist = articleDao.findAllArticles();
        articleView.printArticles(articlelist);

    }

    public void update() {

         try {
             System.out.print("수정할 게시물 번호를 입력해주세요 : ");
             int postidx = getParamInt(scan.nextLine(), -1);

             Article article = articleDao.findById(postidx);

             if (article == null) {
                 System.out.println("없는 게시물입니다.");
             } else {


                 System.out.print("제목 : ");
                 String newTitle = scan.nextLine();
                 System.out.print("내용 : ");
                 String newContent = scan.nextLine();

                 article.setTitle(newTitle);
                 article.setContent(newContent);

                 System.out.println("수정이 완료되었습니다.");
             }

         } catch(NumberFormatException e) {
             System.out.println("숫자만 입력해야 합니다.");
         }

    }

    public void detail() {

        System.out.print("상세보기 할 게시물 번호를 입력해주세요 : ");

        int targetId = getParamInt(scan.nextLine(), -1);
        Article article = articleDao.findById(targetId);
        ArrayList<Comment> test = articleDao.findcommentById(targetId);

        if (article == null) {
            System.out.println("없는 게시물 번호입니다.");
        } else {

            // 상세보기 할 때 조회수 증가
            int checkcount = article.getCount();
            checkcount++;

            article.setCount(checkcount);
            articleView.printArticledetail(article);
            articleView.printCommentview(test);

            System.out.print("상세보기 기능을 선택해주세요(1. 댓글 등록, 2. 추천, 3. 수정, 4. 삭제, 5. 목록으로) : ");
            int optionNumber = getParamInt(scan.nextLine(), -1);
            articleDao.DetailOption(optionNumber, article.getId());

//            int option = getParamInt(scan.nextLine(), -1);
//            replyDao.DetailOption(option, article.getId());
//
//            System.out.println("===============================");
//            System.out.println("수정날짜 : ");
//            System.out.println("조회수 : ");

        }

//                int postidx2 = Integer.parseInt(scan.next());
//
//                int index2 = -1;
//
//                for (int i = 0; i < articles.size(); i++) {
//                    if (articles.get(i).getId() == postidx2) {
//                        index2 = i; // postidx 값을 가지는 Article 객체를 찾으면 해당 인덱스 저장
//                        break;
//                    }
//                }

//                if (index2 != -1) {
//                    Article article = articles.get(index2);

    }

    public void delete() {

        // 버퍼 비우기. 해당 메서드가 실행된 이후의 Enter키로 입력이 종료되기 전의 모든 글자를 읽어옴.

        System.out.print("삭제할 게시물 번호를 입력해주세요 : ");
        int targetId = getParamInt(scan.nextLine(), -1);

        Article article = articleDao.findById(targetId);

        if (article == null) {
            System.out.println("없는 게시물 번호입니다.");
        } else {
            articleDao.delete(article);
            System.out.println("게시물이 삭제되었습니다.");
        }
    }

    public void search() {

        scan.nextLine();

        System.out.print("검색 키워드를 입력해주세요 : ");
        String keyword = scan.nextLine();

        ArrayList<Article> searcharticle = articleDao.findByTitle(keyword);

        // 즉 함수가 실행되고 난 뒤 위의 코드구문은 아래와 같이 해석 가능하다.
        // Article의 형태를 갖는 searcharticle에 배열에 조건에 맞는 searchKeyword 배열을 넣었다.
        // 즉 Article의 틀을 갖는 객체 articles, searcharticle, searchKeyword는 모양이 같다.

//                for (int i = 0; i < articles.size(); i++) {
//                    if (articles.get(i).getTitle().contains(keyword)) {
//                        System.out.println("번호 : " + articles.get(i).getId());
//                        System.out.println("제목 : " + articles.get(i).getTitle());
//                        System.out.println("---------------------");
//                    } else {
//                        System.out.println("관련 키워드가 없습니다.");
//                    }
//                }

        // 즉, 두 가지 방법이 있는 것이다. article의 초기값을 searchBykeyword에서 null 로 선언하면
        // 첫번째 조건문이 article == null 이 될 것이고. 선언하지만 않으면 빈배열 []로 표기된다.
        // 이를 만족시키려면 isEmpty() String 메소드를 사용할 수 있고 아래와 같이 작성 가능하다.
        // isEmpty()는 ArrayList의 메서드 중 하나이다.

        if (searcharticle.isEmpty()) {
            System.out.println("검색 결과가 없습니다.");
        } else {
            for (int i = 0; i < searcharticle.size(); i++) {
                Article result = searcharticle.get(i);
                System.out.println("번호: " + result.getId());
                System.out.println("제목: " + result.getTitle());
                System.out.println("---------------------");
            }
        }

    }



    public int getParamInt(String input, int defaultvalue) {

        try {
            int num = Integer.parseInt(input);
            return num;
        } catch (NumberFormatException e) {
            System.out.println("숫자만 입력 가능합니다.");
        }
        return defaultvalue;
    }
}
