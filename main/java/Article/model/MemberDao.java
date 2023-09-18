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

    public Session signin(String loginid, String loginpass) {

        Session test2 = null;

        for (int i = 0; i < members.size(); i++) {
            Member test = members.get(i);
            if (loginid.equals(test.getMemberId()) && loginpass.equals(test.getMemberPassword())) {
                System.out.println(test.getMemberNickname() + "님 환영합니다.");
                test2 = new Session(test.getMemberId(), test.getMemberNickname());
                sessions.add(test2);
            } else {
                System.out.println("아이디 또는 패스워드가 잘못되었습니다.");
            }
        }
        return test2; // 성공한 경우 세션을 반환, 실패한 경우 null 반환
    }

    public ArrayList<Session> getSessions() {
        return sessions;
    }
}
