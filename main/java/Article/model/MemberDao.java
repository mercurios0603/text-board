package Article.model;

import java.util.ArrayList;
import java.util.Scanner;

public class MemberDao {

    ArrayList<Member> members = new ArrayList<>();

    public MemberDao() {
        Member master = new Member(1, "kyg", "1234", "k89");
        Member submaster = new Member(2, "kyg2", "1234", "k890603");
        members.add(master);
        members.add(submaster);
    }

    public void signup(int memberindex, String userid, String userpass, String usernick) {

        Member newmembers = new Member(memberindex, userid, userpass, usernick);
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

    public Member getMemberById(String id) {
        for (int i = 0; i < members.size(); i++) {
            Member member = members.get(i);
            if (member.getMemberId() == id) {
                return member;
            }
        }
        return null;
    }
}
