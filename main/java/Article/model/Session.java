package Article.model;

public class Session {

    private String sessionid;

    private String sessionnickname;

    public Session(String sessionid, String sessionnickname) {
        this.sessionid = sessionid;
        this.sessionnickname = sessionnickname;
    }
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
