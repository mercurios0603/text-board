package Article.model;
public class Member {

    // 회원 PK
    private int memberindex;

    // 회원 아이디
    private String memberid;

    // 회원 패스워드
    private String memberpassword;

    // 회언 닉네임
    private String membernickname;

    public Member(int memberindex, String memberid, String memberpassword, String membernickname) {
        this.memberindex = memberindex;
        this.memberid = memberid;
        this.memberpassword = memberpassword;
        this.membernickname = membernickname;

    }

    public int getMemberIndex () {
        return memberindex;
    }

    public void setMemberIndex (int memberid) {
        this.memberindex = memberindex;
    }
    public String getMemberId () {
        return memberid;
    }

    public void setMemberId (String memberid) {
        this.memberid = memberid;
    }

    public String getMemberPassword () {
        return memberpassword;
    }

    public void setMemberPassword (String memberpassword) {
        this.memberpassword = memberpassword;
    }

    public String getMemberNickname () {
        return membernickname;
    }

    public void setMemberNickname (String membernickname) {
        this.membernickname = membernickname;
    }


}
