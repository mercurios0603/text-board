package Article.controller;

import Article.model.Member;

import java.util.Scanner;

public class BoardApp {

    ArticleController articleController = new ArticleController();

    MemberController memberController = new MemberController();

    public void start() {

        // 일반적으로 핵심 로직을 Main에 작성하지 않는다.

        //저장된 공간은 ArrayList의 동적배열을 따르는 Article(틀)이고, 그것을 컨트롤 하는 것이 articles(내용물)?

        // 이름 짓는 규칙
        // 변수, 클래스 -> 명사
        // 함수 -> 동사
        // 단어 조합시 단어 의미가 바뀔 때마다 대문자 표현
        //  -> 카멜 표기법 (+ 파스칼 표기법)
        // 클래스 이름 지을 때는 첫글자 무조건 대문자.
        // 누가 봐도. 추측 가능한. 단어 조합으로 적으세요.
        // 파파고나 구글 번역기. 챗 GPT를 이용할 것.
        // 이름 짓는 건 너무너무너무너무너무너무너무너무 중요함.

        // 코드 정렬 (심심할 때마다 눌러주세요)
        // Alt + Ctrl + L

        Scanner scan = new Scanner(System.in);

        while (true) { // 무한 반복문

            // 로그인이 끝난 이후에 반복문이 계속되고, 로그인 정보가 존재할경우
            // 회원에게 제공되는 정보는 로그인 정보를 input 받아 콘트롤러에서 활용한다.

            Member loginedmember = memberController.loginedMember();

            if (loginedmember == null) {
                System.out.print("명령어: ");
            } else {
                System.out.printf("명령어[%s(%s)]: ", loginedmember.getMemberId(), loginedmember.getMemberNickname());
            }

            String func = scan.next();

            if (func.equals("add")) {

                articleController.add(loginedmember);

            } else if (func.equals("list")) {

                articleController.list();

            } else if (func.equals("sort")) {

                articleController.sort();

            }else if (func.equals("detail")) {

                articleController.detail(loginedmember);

            } else if (func.equals("search")) {

                articleController.search();

            } else if (func.equals("signup")) {

                memberController.signup();

            } else if (func.equals("signin")) {

                memberController.login();

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
