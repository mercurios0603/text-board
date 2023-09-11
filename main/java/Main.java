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

        ArrayList<Integer> number = new ArrayList<>();
        ArrayList<Object> subject = new ArrayList<>();
        ArrayList<Object> detail = new ArrayList<>();

        int listnumber = 0; // 글작성 번호는 프로그램이 살아있는 한 계속해서 증가되어야 한다.

        while (true) { // 무한 반복문
            System.out.print("메뉴 입력 : ");
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

                System.out.print("게시물 제목을 입력해주세요 : ");
                String title = scan.nextLine();
                System.out.println("당신이 입력한 제목은 : " + title);

                System.out.print("게시물 내용을 입력해주세요 : ");
                String contents = scan.nextLine();
                System.out.println("당신이 입력한 내용은 : " + contents);


                listnumber++; // listnumber 값을 1 증가

                number.add(listnumber); // 현재의 listnumber 값을 number 배열에 저장
                subject.add(title); // 입력한 제목을 subject 배열에 저장
                detail.add(contents); // 입력한 내용을 detail 배열에 저장

                System.out.println(listnumber + "번 게시물이 등록되었습니다.");

            } else if (func.equals("list")) {
                System.out.println("===============================");
                for (int i = 0; i < subject.size(); i++) {
                    System.out.println(number.get(i) + "번 게시물의 내용은 다음과 같습니다.");
                    System.out.println("번호 : " + number.get(i));
                    System.out.println("제목 : " + subject.get(i));
                    System.out.println("내용 : " + detail.get(i));
                    System.out.println("===============================");
                }
            } else if (func.equals("update")) {

                scan.nextLine(); // 버퍼 비우기

                System.out.print("수정할 게시물 번호를 입력해주세요 : ");
                String inputidx = scan.next();
                int postidx = Integer.parseInt(inputidx);

                // indexOf 메서드를 사용하여 해당 게시물 번호를 가지는 인덱스 찾기
                int index = number.indexOf(postidx);

                // 만약 postidx가 특정 값을 가지면 (존재한다면 index에 그 값이 들어갈 것이고, 아니면 0을 반환)

                    if (index != -1) {

                        scan.nextLine(); // 버퍼 비우기

                        System.out.print("수정할 제목을 입력해주세요 : ");
                        String modifytitle = scan.nextLine();
                        System.out.println("당신이 수정한 제목은 : " + modifytitle);

                        System.out.print("수정할 내용을 입력해주세요 : ");
                        String modifycontents = scan.nextLine();
                        System.out.println("당신이 수정한 내용은 : " + modifycontents);

                        subject.set(index, modifytitle);
                        detail.set(index, modifycontents);

                        System.out.println("게시물이 수정되었습니다.");

                    } else {

                        System.out.println("없는 게시물 번호입니다.");

                    }




            } else if (func.equals("delete")) {

                scan.nextLine(); // 버퍼 비우기

                System.out.print("삭제할 게시물 번호를 입력해주세요 : ");

                String inputidx2 = scan.nextLine();
                int postidx2 = Integer.parseInt(inputidx2);

                // indexOf 메서드를 사용하여 해당 게시물 번호를 가지는 인덱스 찾기
                int index2 = number.indexOf(postidx2);

                if (index2 != -1) {

                    number.remove(index2);
                    subject.remove(index2);
                    detail.remove(index2);

                    System.out.println("게시물이 삭제되었습니다.");

                } else {
                    System.out.println("없는 게시물 번호입니다.");
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
