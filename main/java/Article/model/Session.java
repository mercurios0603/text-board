package Article.model;

public class Session {

    private String sessionid;

    private String sessionnickname;

    public Session(String sessionid, String sessionnickname) {
        this.sessionid = sessionid;
        this.sessionnickname = sessionnickname;
    }

    // 세션의 유효성에 관하여
    // 페이지를 이동할 때마다 id와 nickname이 존재하면 (즉 null 이 아니면) 각 페이지의 전용기능을 사용할 수 있음
    // 한 아이디로 로그인이 되어 있다면 로그아웃 전까지 새로운 로그인은 불가능하다.
    // 이는 세션에 로그인 정보의 존재유무로 판단하여 해당 메뉴를 숨기거나 비활성화 하는 방법으로 구현된다.

    // login 했을 때 session() != 이라면 거절.
    // logout을 하면 session()을 null로 초기화
    // 아.. 근데 어차피 session은 단일 객체라 굳이 배열이 필요할까 싶기도함;

    public void setSessionId (String sessionid) {
        this.sessionid = sessionid;
    }

    public String getSessionId () {
        return sessionid;
    }

    public void setSessionNickname (String sessionnickname) {
        this.sessionnickname = sessionnickname;
    }

    public String getSessionNickname () {
        return sessionnickname;
    }
}
