import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        while(true) { // 무한 반복문
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

            } else if (func.equals("exit")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }

        // 숫자 비교
        int a = 10;
        System.out.println(a == 10);

        // 문자열 비교
        String str = "hello";
        System.out.println(str.equals("hello"));

        }
    }
}
