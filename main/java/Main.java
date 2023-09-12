import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    static ArrayList<Article> articles = new ArrayList<>();

    public static void main(String[] args) {

        // 이름 짓는 규칙
        // 변수, 클래스 -> 명사
        // 함수 -> 동사
        // 단어 조합시 단어 의미가 바뀔 때마다 대문자 표현
        //  -> 카멜 표기법
        // 클래스 이름 지을 때는 첫글자 무조건 대문자.
        // 누가 봐도. 추측 가능한. 단어 조합으로 적으세요.
        // 파파고나 구글 번역기. 챗 GPT를 이용할 것.
        // 이름 짓는 건 너무너무너무너무너무너무너무너무 중요함.

        // 코드 정렬 (심심할 때마다 눌러주세요)
        // Alt + Ctrl + L

//        ArrayList<Integer> number = new ArrayList<>();
//        ArrayList<Object> subject = new ArrayList<>();
//        ArrayList<Object> detail = new ArrayList<>();

        Article article1 = new Article(1, "안녕하세요 반갑습니다", "질문이에요", "2023-08-31 14:01:23", "2023-08-31 14:05:463");
        Article article2 = new Article(2, "자바 질문좀 할게요~", "질문내용입니다", "2023-09-04 15:43:36", "2023-09-04 15:47:55");
        Article article3 = new Article(3, "정처기 따야되나요?", "스펙 질문입니다", "2023-09-11 09:23:05", "2023-09-11 09:30:25");
        articles.add(article1);
        articles.add(article2);
        articles.add(article3);

        int listid = articles.size(); // 글작성 번호는 프로그램이 살아있는 한 계속해서 증가되어야 한다.

        while (true) { // 무한 반복문
            System.out.print("메뉴 입력 : ");
            Scanner scan = new Scanner(System.in);
            String func = scan.next();

            if (func.equals("add")) {

                // 글번호를 별도로 입력받지 않지만 자동으로 입력됨.
                // 글을 작성할 (add) 때마다 listid 값을 1씩 증가.

                listid++;

                System.out.println("게시물을 작성합니다.\n");

                scan.nextLine(); // 버퍼 비우기

                System.out.print("게시물 제목을 입력해주세요 : ");
                String title = scan.nextLine();
                System.out.println("당신이 입력한 제목은 : " + title);

                System.out.print("게시물 내용을 입력해주세요 : ");
                String contents = scan.nextLine();
                System.out.println("당신이 입력한 내용은 : " + contents);

                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String createtime = now.format(formatter);
                System.out.println("작성 날짜 : " + createtime);

                // articles와 article은 다른 것입니다.

                // articles는 ArrayList 타입의 컬렉션입니다.
                // 이는 여러 개의 Article 객체를 저장할 수 있는 동적 배열 형태의 자료 구조입니다.
                // articles에는 여러 개의 Article 객체가 저장될 수 있으며, 이 객체들을 관리하는 용도로 사용됩니다.

                // article은 Article 클래스의 객체 하나입니다.
                // 이 객체는 listnumber, title, contents와 같은 멤버 변수와 메서드를 포함하며,
                // articles에 추가되거나 다루어질 수 있는 하나의 게시글을 나타냅니다.
                // 즉, articles는 여러 개의 Article 객체를 담을 수 있는 컬렉션(리스트)이고,
                // article은 그 중 하나의 Article 객체입니다.

                Article article = new Article(listid, title, contents, createtime, createtime);
                articles.add(article);

//                number.add(listid); // 현재의 listnumber 값을 number 배열에 저장
//                subject.add(title); // 입력한 제목을 subject 배열에 저장
//                detail.add(contents); // 입력한 내용을 detail 배열에 저장

                System.out.println(listid + "번 게시물이 등록되었습니다.");

            } else if (func.equals("list")) {
                System.out.println("===============================");
                for (int i = 0; i < articles.size(); i++) {

                    Article article = articles.get(i);

                    System.out.println(article.getId() + "번 게시물의 내용은 다음과 같습니다.");
                    System.out.println("번호 : " + article.getId());
                    System.out.println("제목 : " + article.getTitle());
                    System.out.println("등록날짜 : " + article.getCreatetime());
                    System.out.println("수정날짜 : " + article.getModifytime());
                    System.out.println("===============================");
                }
            } else if (func.equals("update")) {


                scan.nextLine(); // 버퍼 비우기

                System.out.print("수정할 게시물 번호를 입력해주세요 : ");
                int postidx = Integer.parseInt(scan.next());

                // indexOf 메서드를 사용하여 해당 게시물 번호에 해당하는 인덱스 찾기
                // articles인 이유는 indexOf가 ArrayList의 메서드이기 때문임

                // 기존 방법1: indexOf 메서드를 사용하여 해당 게시물 번호를 가지는 인덱스 찾기

                // 방법 1

//                Article targetArticle = new Article(postidx, null, null);
//                int index = articles.indexOf(targetArticle);

                // 방법2 : for 구문을 사용해서 인덱스를 찾는 방법.

                // 일반적으로 index를 초기화하여 사용하는 것이 좋습니다.
                // 초기값을 -1로 설정하는 이유는 보통 인덱스가 음수가 아니라 0부터 시작하기 때문입니다.
                // 게시물이 리스트에 없는 경우를 나타내기 위해 -1을 사용하는 것이 관례적입니다.

                int index = -1;

                for (int i = 0; i < articles.size(); i++) {
                    if (articles.get(i).getId() == postidx) {
                        index = i; // postidx 값을 가지는 Article 객체를 찾으면 해당 인덱스 저장
                        break; // 찾았으면 루프 종료
                    }
                }

                // 만약 postidx가 특정 값을 가지면 (존재한다면 index에 그 값이 들어갈 것이고, 아니면 -1을 반환)

                if (index != -1) {

                    scan.nextLine();

                    Article article = articles.get(index);

                    System.out.print("수정할 제목을 입력해주세요 : ");
                    String modifytitle = scan.nextLine();
                    System.out.println("당신이 수정한 제목은 : " + modifytitle);

                    System.out.print("수정할 내용을 입력해주세요 : ");
                    String modifycontents = scan.nextLine();
                    System.out.println("당신이 수정한 내용은 : " + modifycontents);

                    LocalDateTime now = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String modifytime = now.format(formatter);

                    Article newArticle = new Article(postidx, modifytitle, modifycontents, article.getCreatetime(), modifytime);
                    articles.set(index, newArticle);

                    System.out.println("게시물이 수정되었습니다.");

                } else {

                    System.out.println("없는 게시물 번호입니다.");

                }


            } else if (func.equals("detail")) {

                scan.nextLine();

                System.out.print("상세보기 할 게시물 번호를 입력해주세요 : ");

                int targetId = scan.nextInt();
                Article article = findById(targetId);

                if (article == null) {
                    System.out.println("없는 게시물 번호입니다.");
                } else {
                    System.out.println("===============================");
                    System.out.println(article.getId() + "번 게시물의 상세내용은 다음과 같습니다.");
                    System.out.println("번호 : " + article.getId());
                    System.out.println("제목 : " + article.getTitle());
                    System.out.println("내용 : " + article.getContent());
                    System.out.println("등록날짜 : " + article.getCreatetime());
                    System.out.println("수정날짜 : " + article.getModifytime());
                    System.out.println("===============================");
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

            } else if (func.equals("delete")) {

                // 버퍼 비우기. 해당 메서드가 실행된 이후의 Enter키로 입력이 종료되기 전의 모든 글자를 읽어옴.
                scan.nextLine();
                System.out.print("삭제할 게시물 번호를 입력해주세요 : ");

                int targetId = scan.nextInt();
                Article article = findById(targetId);

                if (article == null) {
                    System.out.println("없는 게시물 번호입니다.");
                } else {
                    articles.remove(article);
                    System.out.println("게시물이 삭제되었습니다.");
                }

            } else if (func.equals("exit")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }

            // 숫자 비교
            // int a = 10;
            // System.out.println(a == 10);

            // 문자열 비교
            // String str = "hello";
            // System.out.println(str.equals("hello"));

        }
    }

    public static Article findById(int id) {

        Article target = null;

        for (int i = 0; i < articles.size(); i++) {
            Article article = articles.get(i);
            if (id == article.getId()) {
                target = article;
            }
        }
        return target;
    }
}

