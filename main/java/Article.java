public class Article {

    // 클래스를 디자인 할 때는 변수부터 정하는 것이 좋다.

    // 글번호
    private int number;

    // 제목
    private String title;
    // 내용
    private String content;

    // 작성자
    // 작성일
    // 조회수

    public Article(int number, String title, String content) {
        this.number = number;
        this.title = title;
        this.content = content;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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
}
