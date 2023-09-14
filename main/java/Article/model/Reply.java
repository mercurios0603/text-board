package Article.model;

import util.Util;

import java.util.Scanner;

public class Reply {
//    for (int i = 0; i < articles.size(); i++) {
//        if (listnumber == articles.get(i).getId()) {
//            ReplyDao rrr = new ReplyDao(listnumber, comment, Util.getCurrentTime());
//            replydb.add(rrr);
//        }
//    }
    public void DetailOption(int optionnumber, int listnumber) {

        // 상세보기 기능을 선택해주세요(1. 댓글 등록, 2. 추천, 3. 수정, 4. 삭제, 5. 목록으로) : 1

        Scanner scan = new Scanner(System.in);

        if (optionnumber == 1) {

            System.out.print("댓글을 작성해주세요 : ");
            String comment = scan.nextLine();

            System.out.println("댓글이 정상적으로 등록되었습니다.");

        } else if (optionnumber == 2) {
            System.out.println("[추천 기능]");
        } else if (optionnumber == 3) {
            System.out.println("[수정 기능]");
        } else if (optionnumber == 4) {
            System.out.println("[삭제 기능]");
        } else if (optionnumber == 5) {
            System.out.println("상세보기 화면을 빠져나갑니다.");
        } else {
            System.out.println("잘못된 입력입니다. 다시 입력하세요.");
        }

    }
}
