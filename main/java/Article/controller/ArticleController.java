package Article.controller;

import Article.model.*;
import Article.view.ArticleView;

import java.util.*;
import java.util.Comparator;


public class ArticleController {

    // 기본적으로 콘트롤러에서 모든 DAO에 접속가능하다.
    // 콘트롤러는 게시판의 페이지와 같다. (회원 관련 페이지는 별도이기 때문에 MemberController로 분리된 것)
    // 게시판에는 기사, 답글, 좋아요 등이 포함되어 있으므로 각각의 저장소(Dao)의 객체를 활용한다.
    // 또한 View로 데이터를 내보내기도 한다.

    ArticleDao articleDao = new ArticleDao();
    CommentDao commentDao = new CommentDao();
    LikeDao likeDao = new LikeDao();

    MemberDao memberDao = new MemberDao();

    Pagination pagination =  new Pagination();

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

                ArrayList<Comment> comments = commentDao.findcommentById(article.getArticleIndex());
                Member member = memberDao.getMemberById(sessionmember.getMemberId());
                Like like = likeDao.getLikeByArticleIdAndMemberId(article.getArticleIndex(), sessionmember.getMemberId());
                int likeCount = likeDao.getCountOfLikeByArticleId(article.getArticleIndex());

                articleView.printArticleDetail(article, member, comments, likeCount, like);
                DetailOption(article, member, comments);
            }
        }
    }


    public void DetailOption(Article article, Member member, ArrayList<Comment> comment) {

        System.out.print("상세보기 기능을 선택해주세요(1. 댓글 등록, 2. 추천, 3. 수정, 4. 삭제, 5. 목록으로) : ");
        int optionnumber = getParamInt(scan.nextLine(), -1);

        if (optionnumber == 1) {

            if (member == null) {
                System.out.println("로그인 하셔야 사용할 수 있는 기능입니다.");
            } else {
                System.out.print("댓글을 작성해주세요 : ");
                String inputcomment = scan.nextLine();
                commentDao.insert(article.getArticleIndex(), member.getMemberNickname(), inputcomment);

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
                Article findarticle = articleDao.findById(article.getArticleIndex());
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

                Article findarticle = articleDao.findById(article.getArticleIndex());

                if (findarticle.getMemberId() == member.getMemberId()) {

                    Article target = findarticle;

                    System.out.print("정말 게시물을 삭제하시겠습니까? (y/n) : ");
                    String confirmdelete = scan.nextLine();

                    articleDao.delete(target, confirmdelete);

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
        // 시나리오를 고민해볼 것!!! (아래 시나리오는 likeprocess 비즈니스 로직에 반영되어야 함)
        // 좋아요 버튼을 눌렀을 때
        // (1) 먼저 회원고유식별번호(회원아이디)와 게시글 번호를 가지고 좋아요 DB를 검색을 함 (둘다 일치 AND, DAO에서 진행)
        // (2) 없는 경우. 이 게시글에 대한 새로운 객체를 생성해서 좋아요 배열을 삽입함. (DAO에서 진행)
        // (3) 있는 경우, 이 게시글에 대한 좋아요 배열을 제거함. (DAO에서 진행)
        // DB에는 무조건 어떤 게시글에, 어떤 회원이, 좋아요를 누른것만 남음 (좋아요 0은 remove로 삭제됨)
        // 배열 그자체가 좋아요인 것이다. 별도의 변수 설정은 필요없다. 좋아요의 갯수는 곧 배열의 개수이다.

        Like like = likeDao.getLikeByArticleIdAndMemberId(article.getArticleIndex(), member.getMemberId());

        if (like == null) {
            likeDao.insert(article.getArticleIndex(), member.getMemberId());
            System.out.println("해당 게시물을 좋아합니다.");
        } else {
            likeDao.delete(like);
            System.out.println("해당 게시물의 좋아요를 해제합니다.");
        }

        int likeCount = likeDao.getCountOfLikeByArticleId(article.getArticleIndex());
        like = likeDao.getLikeByArticleIdAndMemberId(article.getArticleIndex(), member.getMemberId());
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
                System.out.println("번호: " + result.getArticleIndex());
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

    // 정렬은 DAO의 기능을 쓰는 것이 아닌, 저장된 기사를 가져와서 배열만 바꾸는 것이므로 Controller 단에서 모두 해결함.
    public void sort() {
        System.out.print("정렬 대상을 선택해주세요. (1. 번호, 2.조회수) : ");
        int targetsort = getParamInt(scan.nextLine(), -1);
        System.out.print("정렬 방법을 선택해주세요. (1. 오름차순, 2. 내림차순) : ");
        int methodsort = getParamInt(scan.nextLine(), -1);

        ArrayList<Article> articleforsort = articleDao.findAllArticles();

        Collections.sort(articleforsort, new SortFactory().getSort(targetsort).setDirection(methodsort));
        // 여기에서 sort는 Collections의 메서드임 (아래 클래스의 Sort가 아님 시작글자 대문자)
        // getSort 하는 순간 입력값(targetsort)에 따라 Sort 객체인 ArticleSortById 또는 ArticleSortByHit 객체가 반환된다.
        // 이 객체들은 sort의 자식이기 때문에 부모의 메서드인 setDirection() 을 사용할 있게 되는 것이다.

        // new SortFactory().getSort(targetsort).setDirection(methodsort) 표현식에서 메서드의 작동 순서는 다음과 같이 됩니다:
        // (1) new SortFactory(): SortFactory 클래스의 새로운 인스턴스를 생성합니다.
        // (2) getSort(targetsort): SortFactory 인스턴스의 getSort 메서드를 호출하여 targetsort에 해당하는 Sort 객체를 얻습니다.
        // 이때, targetsort에 따라 ArticleSortById 또는 ArticleSortByHit 객체가 반환됩니다. (상속 때문에 가능함. 이들은 Sort 객체로 취급되기 때문에 Sort 메서드 사용가능!)
        // (3) setDirection(methodsort): 앞서 얻은 Sort 객체의 setDirection 메서드를 호출하여 methodsort에 따라 정렬 방향을 설정하고, 이 Sort 객체를 반환합니다.
        // 이렇게 반환된 Sort 객체를 사용하여 Collections.sort(articleforsort, ...)에서 정렬을 수행합니다.
        // 정렬 동작에서는 Sort 객체의 setDirection 메서드에서 설정한 방향 설정이 적용되며, 실제 정렬 로직은 Sort 객체의 compare 메서드에서 처리됩니다.

        // setDirection(methodsort)를 실행할 때, 먼저 Sort 클래스의 setDirection 메서드가 호출됩니다.
        // 이 메서드 내에서 order 값을 설정하고, this를 반환합니다.
        // 그런 다음 getSort(targetsort) 메서드에서 반환한 객체는 setDirection 메서드의 결과인 this를 가지고 있습니다.
        // 이후 Collections.sort(articleforsort, new SortFactory().getSort(targetsort).setDirection(methodsort)) 라인에서는
        // new SortFactory().getSort(targetsort)을 통해 얻은 객체(여기서는 ArticleSortById 또는 ArticleSortByHit 객체)가
        // setDirection(methodsort) 메서드의 결과인 this를 가지고 있으므로, 실제 정렬 시에는 Sort 클래스의 setDirection 메서드가 먼저 호출되고,
        // 그 다음에 ArticleSortById 또는 ArticleSortByHit 클래스의 compare 메서드가 호출됩니다.
        // 이렇게 상속과 오버라이드가 동작하여 부모 클래스와 자식 클래스의 메서드가 혼합되어 사용됩니다.
        // 결과적으로 Sort 클래스의 setDirection에서 설정한 order 값이 정렬 동작에 영향을 미치게 됩니다.

        articleView.printArticles(articleforsort);

        // 아래의 반복되고 공통되는 부분을 제거하기 위해 상속, 다형성을 이용하였음
//        if (targetsort == 1) {
//            // ArticleSortById aci = new ArticleSortById(methodsort);
//            Collections.sort(articleforsort, new ArticleSortById(methodsort));
//            articleView.printArticles(articleforsort);
//
//        } else {
//            // ArticleSortByHit ach = new ArticleSortByHit(methodsort);
//            Collections.sort(articleforsort, new ArticleSortByHit(methodsort));
//            articleView.printArticles(articleforsort);
//        }
    }

    class Sort {
        protected int order = 1;

        // direction 값에 따라 order의 초기값이 오름차순, 내림차순에 영향을 미치게 되므로 if 분기로 나눔.
        Comparator<Article> setDirection(int direction) {
            if (direction == 2) {
                order = -1;
            }

            return (Comparator<Article>) this;
        }
    }

    class SortFactory {
        Map<Integer, Sort> sortMap = new HashMap<>();
        // 해쉬맵 계열. 정수형과 Sort형을 매칭하여 저장하겠다는 뜻.

        SortFactory() {
            sortMap.put(1, new ArticleSortById());
            sortMap.put(2, new ArticleSortByHit());
        }

        //Sort형으로 선언하고 ArticleSortById 또는 ArticleSortByHit을 넣을 수 있는 이유는
        //상속(extend)으로 인해 Sort가 두 클래스의 부모이기 때문에, 자식 객체를 부모 대신 사용할 수 있는 것.

        public Sort getSort(int sortTarget) {
            return sortMap.get(sortTarget);
        }
    }

    public class ArticleSortById extends Sort implements Comparator<Article> {

        // 하나의 Comparator 클래스에서는 하나의 변수만 다룰 수 있음(?)
        // ArrayList와 비슷하게 Article 클래스 객체에 정렬 커스터마이징
        // Comparator는 Java에서 사용되는 인터페이스로, 객체들의 정렬을 커스터마이징할 때 유용하게 활용됩니다.
        // Comparator를 구현하여 객체 간의 비교 규칙을 정의할 수 있습니다.


        @Override
        public int compare(Article o1, Article o2) {

            // Comparator은 반환값이 1, 0, -1에 좌우된다. 조건에 따라 부호를 변경하게 하면 가능할 것.
            // 기본을 오름차순으로 하고 order를 통해 결과를 반전시킨다.
            // 앞의 값이 더 크다면 -1을 반환해 자리를 유지하고 내림차순을 만들어라.
            // 뒤의값이 더 커서 if문을 만족하지 못하면 오름차순 상태이니 1을 반환해 내림차순을 만들어라.

            if (o1.getArticleIndex() > o2.getArticleIndex()) { // 앞의 값이 더 크다면 1을 반환해 자리를 바꿔 오름차순을 만들어라.
                return order; //                               // 입력값이 2인 상태로 내림차순을 만들 때는 order의 기본값이 -1이다.
            }                                                  // 따라서 order가 앞의 값이 클 때(내림차순) -1을 반환하면 자리를 유지한다.
            return -order;  // 뒤의 값이 더 커서 if문을 만족하지 못하면 오름차순인 상태이니, -1을 반환해 자리를 유지해라.
        }                   // 입력값이 2인 상태에서 내림차순을 만들 때는 order의 기본값이 -1이다.
    }                       // 만약 뒤의 값이 더 크다면 오름차순 상태이니 1을 반환해 자리를 바꿔 내림차순을 만들어라.

    public class ArticleSortByHit extends Sort implements Comparator<Article> {

        @Override
        public int compare(Article o1, Article o2) {

            if (o1.getCount() > o2.getCount()) {
                return order; //
            }
            return -order;  //
        }
    }

    public void page() {

        while(true) {
            System.out.println(" ");
            System.out.print("페이징 명령어를 입력해주세요 ((1. 이전, 2. 다음, 3. 선택, 4. 뒤로가기) : ");
            int pageCmd = getParamInt(scan.nextLine(), 1);

            switch (pageCmd) {
                case 3:
                    System.out.print("보고 싶은 페이지 번호를 입력해주세요 : ");
                    int pageNo = getParamInt(scan.nextLine(), 1);

                    pagination.selectPage(pageNo);

                    ArrayList<Article> articlelist = articleDao.findAllArticles();
                    articleView.printPagedArticles(articlelist, pagination);

                    break;
            }

            if (pageCmd == 4) {
                System.out.println("페이지 메뉴를 나갑니다.");
                break;
            }
        }
    }
}
