import java.util.Scanner;
import java.util.ArrayList;

public class Main {
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

        ArrayList<Object> subject = new ArrayList<>();
        ArrayList<Object> detail = new ArrayList<>();

        while (true) { // 무한 반복문
            System.out.println("메뉴 입력 : ");
            Scanner scan = new Scanner(System.in);
            String func = scan.next();

            if (func.equals("a")) {
                System.out.println("기능1");
            } else if (func.equals("b")) {
                System.out.println("기능2");
            } else if (func.equals("도움말")) {
                System.out.println("a = 기능1");
                System.out.println("b = 기능2");
            } else if (func.equals("add")) {
                // 공백을 포함한 한 문장을 가져오는 방법
                System.out.println("게시물을 작성합니다.\n");

                scan.nextLine(); // 버퍼 비우기

                System.out.println("게시물 제목을 입력해주세요 : ");
                String title = scan.nextLine();
                System.out.println("당신이 입력한 제목은 : " + title);

                System.out.println("게시물 내용을 입력해주세요 : ");
                String contents = scan.nextLine();
                System.out.println("당신이 입력한 내용은 : " + contents);

                subject.add(title);
                detail.add(contents);

                System.out.println("게시물이 등록되었습니다.");

            } else if (func.equals("list")) {
                for (int i = 0; i < subject.size(); i++) {
                    System.out.println(i+1 + "번 게시물의 내용은 다음과 같습니다.");
                    System.out.println("제목 : " + subject.get(i));
                    System.out.println("내용 : " + detail.get(i));
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
}

//class DataBase<T> {
//
//    private Object[] title;
//    private Object[] contents;
//
//    int lastIdx;
//
//    DataBase() {
//        title = new Object[3];
//        contents = new Object[3];
//        lastIdx = 0;
//    }
//
//    public void setTitle(T title) {
//        this.title[lastIdx] = title;
//        lastIdx++;
//    }
//
//    public void setContents(T contents) {
//        this.contents[lastIdx] = contents;
//                lastIdx++;
//    }
//
//    public T getTitle(int idx) {
//        return (T) title[idx];
//    }
//
//    public T getContents(int idx) {
//        return (T) contents[idx];
//    }
//}
