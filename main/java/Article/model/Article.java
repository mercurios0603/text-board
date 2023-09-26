package Article.model;

import java.util.ArrayList;

public class Article {

    // 클래스를 디자인 할 때는 변수부터 정하는 것이 좋다.

    // 작성자
    private String memberid;
    // 글번호
    private int id;
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

    public Article(String memberid, int id, String title, String content, String createtime, String modifytime, int count) {
        this.memberid = memberid;
        this.id = id;
        this.title = title;
        this.content = content;
        this.createtime = createtime;
        this.modifytime = modifytime;
        this.count = count;
    }

    // 아래는 생성된 객체가 사용할 수 있는 메서드이다.
    public String getMemberId() {
        return memberid;
    }

    public void setMemberId(String memberid) {
        this.memberid = memberid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
