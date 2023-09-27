package Article.controller;

import Article.model.Member;
import Article.model.MemberDao;

import java.util.Scanner;

public class MemberController {

    MemberDao memberDao = new MemberDao();

    Scanner scan = new Scanner(System.in);

    Member sessioninfo = null;

    int memberindex = 3; // 현재 kyg로 본계정, 부계정 두명이 회원가입 되어 있으므로 3으로 설정하였음

    public void signup() {

        System.out.println("==== 회원 가입을 진행합니다. ====");
        System.out.print("아이디를 입력해주세요 : ");
        String inputId = scan.nextLine();
        System.out.print("비밀번호를 입력해주세요 : ");
        String inputPass = scan.nextLine();
        System.out.print("닉네임을 입력해주세요 : ");
        String inputNick = scan.nextLine();

        memberDao.signup(memberindex, inputId, inputPass, inputNick);

        // 회원가입이 증가할수록 회원 인덱스 PK 증가.
        memberindex++;

        System.out.println("==== 회원가입이 완료되었습니다. ====");
    }

    public Member login() {
        System.out.println("==== 로그인을 진행합니다. ====");
        System.out.print("아이디 : ");
        String loginId = scan.nextLine();
        System.out.print("비밀번호 : ");
        String loginPass = scan.nextLine();

        sessioninfo = memberDao.signin(loginId, loginPass);

        return sessioninfo;
    }

    public Member loginedMember() {
        return sessioninfo;
    }
}
