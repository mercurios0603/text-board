package Article.controller;

import Article.model.*;
import Article.view.ArticleView;

import java.util.ArrayList;
import java.util.Scanner;


public class ArticleController {

    // 기본적으로 콘트롤러에서 모든 DAO에 접속가능하다.
    // 콘트롤러는 게시판의 페이지와 같다. (회원 관련 페이지는 별도이기 때문에 MemberController로 분리된 것)

    ArticleDao articleDao = new ArticleDao();
    CommentDao commentDao = new CommentDao();
    LikeDao likeDao = new LikeDao();

    MemberDao memberDao = new MemberDao();

    ArticleView articleView = new ArticleView();

    Scanner scan = new Scanner(System.in);

    public void add(Member membersession) {

        // 클래스를 어떻게 연결하느냐에 따라서 변수 라이프사이클 주기와 연결방식이 달라짐

        if (membersession == null) {
            System.out.println("게시물은 회원만 작성할 수 있습니다.");
        } else {
            System.out.print("게시물 제목을 입력해주세요 : ");
            String title = scan.nextLine();
            System.out.println("당신이 입력한 제목은 : " + title);

            System.out.print("게시물 내용을 입력해주세요 : ");
            String contents = scan.nextLine();
            System.out.println("당신이 입력한 내용은 : " + contents);

            articleDao.insert(membersession.getMemberId(), title, contents);
        }
    }

    public void list() {

        ArrayList<Article> articlelist = articleDao.findAllArticles();
        articleView.printArticles(articlelist);

    }

    public void detail(Member sessionmember) {

        if (sessionmember == null) {
            System.out.println("상세보기는 회원만 가능합니다. 로그인 해주세요.");

        } else {

            System.out.print("상세보기 할 게시물 번호를 입력해주세요 : ");

            int targetId = getParamInt(scan.nextLine(), -1);

            Article article = articleDao.findById(targetId);

            if (article == null) {
                System.out.println("없는 게시물 번호입니다.");
            } else {

                // 상세보기 할 때 조회수 증가
                int checkcount = article.getCount();
                checkcount++;
                article.setCount(checkcount);

                ArrayList<Comment> comments = commentDao.findcommentById(article.getId());
                Member member = memberDao.getMemberById(article.getMemberId());
                Like like = likeDao.getLikeByArticleIdAndMemberId(article.getId(), sessionmember.getMemberId());
                int likeCount = likeDao.getCountOfLikeByArticleId(article.getId());

                articleView.printArticleDetail(article, member, comments, likeCount, like);
                DetailOption(article, member, comments);
            }
        }
    }


    public void DetailOption(Article article, Member member,  ArrayList<Comment> comment) {

        System.out.print("상세보기 기능을 선택해주세요(1. 댓글 등록, 2. 추천, 3. 수정, 4. 삭제, 5. 목록으로) : ");
        int optionnumber = getParamInt(scan.nextLine(), -1);

        if (optionnumber == 1) {

            if (member == null) {
                System.out.println("로그인 하셔야 사용할 수 있는 기능입니다.");
            } else {
                System.out.print("댓글을 작성해주세요 : ");
                String inputcomment = scan.nextLine();

                System.out.println("댓글이 정상적으로 등록되었습니다.");
            }
        } else if (optionnumber == 2) {
            if (member == null) {
                System.out.println("로그인 하셔야 사용할 수 있는 기능입니다.");
            } else {
                checkLike(article, member, comment);
            }

        } else if (optionnumber == 3) {
            if (member == null) {
                System.out.println("로그인 하셔야 사용할 수 있는 기능입니다.");

            } else {
                Article findarticle = articleDao.findById(article.getId());
                if (findarticle.getMemberId() == member.getMemberId()) {

                    Article target = findarticle;

                    System.out.print("수정할 제목 입력 : ");
                    String modifytitle = scan.nextLine();
                    System.out.print("수정할 내용 입력 : ");
                    String modifycontent = scan.nextLine();

                    target.setTitle(modifytitle);
                    target.setContent(modifycontent);

                    System.out.println("수정이 완료되었습니다.");

                } else {
                    System.out.println("자신의 게시물만 수정할 수 있습니다.");
                }
            }


        } else if (optionnumber == 4) {

            if (member == null) {

                System.out.println("로그인 하셔야 사용할 수 있는 기능입니다.");

            } else {

                Article findarticle = articleDao.findById(article.getId());

                if (findarticle.getMemberId() == member.getMemberId()) {

                    Article target = findarticle;

                    System.out.print("정말 게시물을 삭제하시겠습니까? (y/n) : ");
                    String confirmdelete = scan.nextLine();
//
//                    if (confirmdelete.equals("y")) {
//                        //articles.remove(i); // 위치 기반으로 삭제
//                        articles.remove(target);
//                        System.out.println("삭제가 완료되었습니다.");
//
//                        articleView.printArticles(articles);
//
//                    } else if (confirmdelete.equals("n")) {
//                        System.out.println("메인 화면으로 돌아갑니다");
//                    }

                } else {
                    System.out.println("자신의 게시글만 삭제할 수 있습니다.");
                }
            }

        } else if (optionnumber == 5) {
            System.out.println("상세보기 화면을 빠져나갑니다.");
        } else {
            System.out.println("잘못된 입력입니다. 다시 입력하세요.");
        }

    }

    public void checkLike(Article article, Member member, ArrayList<Comment> replies) {
        // 하나의 게시물에 한명의 유저가 체크 가능 -> 어떤 게시물에 어떤 회원이 좋아요 체크 했는지 기억해야 한다.
        // 좋아요 -> 어떤 게시물, 어떤 회원, 언제
        // 좋아요 여러개 -> 1번 게시물에 1번 유저, 1번 게시물에 2번 유저, 1번 게시물에 3번 유저, 2번 게시물에 1번 유저 ....

        Like like = likeDao.getLikeByArticleIdAndMemberId(article.getId(), member.getMemberId());

        if(like == null) {
            likeDao.insert(article.getId(), member.getMemberId());
            System.out.println("해당 게시물을 좋아합니다.");
        } else {
            likeDao.delete(like);
            System.out.println("해당 게시물의 좋아요를 해제합니다.");
        }

        int likeCount = likeDao.getCountOfLikeByArticleId(article.getId());
        like = likeDao.getLikeByArticleIdAndMemberId(article.getId(), member.getMemberId());
        articleView.printArticleDetail(article, member, replies, likeCount, like);
    }

    public void search() {

        System.out.print("검색 키워드를 입력해주세요 : ");
        String keyword = scan.nextLine();

        ArrayList<Article> searcharticle = articleDao.findByTitle(keyword);

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
