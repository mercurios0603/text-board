public class ReplyDB {


    // 댓글 DB에는 고유 id (작성자 id 또는 글번호 id)에 따른 여러가지 댓글이 순차적으로 저장될 것.
    // 작성순서는 컴퓨터의 고유 배열순서에 따라 i = 0,1,2,3...
    // 예를들어 (글번호, 내용, 작성시간)
    // 1, 확실한가여?, 230912
    // 2, 그거 출처가 어디죠?, 230913
    // 2, 공식내용이라고 하네요, 230913
    // 2, 감사합니다, 230914
    // 1, 맞을듯, 230502
    // 이 경우일 떄 1번 글의 댓글을 순서대로 저장하거나 불러오는 메서드가 있어야 할 것.

    private int articleid;

    private String reply;
    // 댓글
    private String replytime;
    // 댓글 작성시간

    public ReplyDB(int articleid, String reply, String replytime) {
        this.articleid = articleid;
        this.reply = reply;
        this.replytime = replytime;
    }

    public int setArticleid() {
        return articleid;
    }

    public void setArticleid(int articleid) {

        this.articleid = articleid;
    }

    public String getReply() {

        return reply;
    }

    public void setReply(String reply) {

        this.reply = reply;
    }

    public String getReplytime() {

        return replytime;
    }

    public void setReplytime(String replytime) {

        this.reply = replytime;
    }

}
