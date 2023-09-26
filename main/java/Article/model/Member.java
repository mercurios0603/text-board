package Article.model;
public class Member {

    private int memberindex;

    private String memberid;

    private String memberpassword;

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
