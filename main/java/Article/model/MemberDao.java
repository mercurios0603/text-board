package Article.model;

import java.util.ArrayList;
import java.util.Scanner;

public class MemberDao {

    ArrayList<Member> members = new ArrayList<>();
    ArrayList<Session> sessions = new ArrayList<>();

    public void signup(String userid, String userpass, String usernick) {

        Member newmembers = new Member(userid, userpass, usernick);
        members.add(newmembers);

    }

    public void signin(String loginid, String loginpass) {

        for (int i = 0; i < members.size(); i++) {
            Member test = members.get(i);
            if (loginid.equals(test.getMemberId()) && loginpass.equals(test.getMemberPassword())) {
                System.out.println(test.getMemberNickname() + "님 환영합니다.");
                Session test2 = new Session(test.getMemberId(), test.getMemberNickname());
                sessions.add(test2);
            } else {
                System.out.println("아이디 또는 패스워드가 잘못되었습니다.");
            }
        }
    }
}
