package Article.model;

import java.util.ArrayList;
import java.util.Scanner;

public class MemberDao {

    ArrayList<Member> members = new ArrayList<>();


    public void signup(String userid, String userpass, String usernick) {

        Member newmembers = new Member(userid, userpass, usernick);
        members.add(newmembers);

    }

    public Member signin(String loginid, String loginpass) {

        // 성공한 경우 세션을 반환, 실패한 경우 null 반환

        for (int i = 0; i < members.size(); i++) {
            Member sessions = members.get(i);
            if (loginid.equals(sessions.getMemberId()) && loginpass.equals(sessions.getMemberPassword())) {
                System.out.println(sessions.getMemberNickname() + "님 환영합니다.");
                return sessions;
            }
        }
        System.out.println("아이디 또는 패스워드가 잘못되었습니다.");
        return null;
    }
}
