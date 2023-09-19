package Article.controller;

import Article.model.Member;
import Article.model.MemberDao;

import java.util.Scanner;

public class MemberController {

    MemberDao memberDao = new MemberDao();

    Scanner scan = new Scanner(System.in);

    Member sessions = null;

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

        sessions = memberDao.signin(loginId, loginPass);

    }
}
