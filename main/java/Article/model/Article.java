package Article.model;

import java.util.ArrayList;

public class Article {

    // 클래스를 디자인 할 때는 변수부터 정하는 것이 좋다.

    // PK
    private int articleindex;
    // 작성자 아이디
    private String memberid;
    // 제목
    private String title;
    // 내용
    private String content;

    // 작성일
    private String createtime;
    // 수정일
    private String modifytime;
    // 조회수
    private int count;

    public Article(int articleindex, String memberid, String title, String content, String createtime, String modifytime, int count) {
        this.articleindex = articleindex;
        this.memberid = memberid;
        this.title = title;
        this.content = content;
        this.createtime = createtime;
        this.modifytime = modifytime;
        this.count = count;
    }

    // 아래는 생성된 객체가 사용할 수 있는 메서드이다 (ArrayList의 기능을 대체하는 것으로 보여진다)

    public int getArticleIndex() {
        return articleindex;
    }

    public void setArticleIndex(int articleindex) {
        this.articleindex = articleindex;
    }
    public String getMemberId() {
        return memberid;
    }

    public void setMemberId(String memberid) {
        this.memberid = memberid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getModifytime() {
        return modifytime;
    }

    public void setModifytime(String modifytime) {
        this.modifytime = modifytime;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
