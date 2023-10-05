package Article.controller;

import Article.model.Article;
import Article.model.ArticleDao;

import java.util.ArrayList;
import java.util.Scanner;

public class Pagination {

    ArticleDao articleDao = new ArticleDao();

    ArrayList<Article> articles = articleDao.findAllArticles();

    int currentPageNo = 1;
    int itemsCountPerPage = 3; // 한 페이지당 게시글 갯수 : 운영자 지정값
    int pageCntPerBlock = 3; // 페이지 묶음 pageSet(화면당 보여지는 페이지 묶음 단위) : 운영자 지정값

    public int getStartIdx() {
        return itemsCountPerPage * (currentPageNo - 1); // 지정 페이지의 시작 게시물의 배열 인덱스 (ArticleView에서 사용)
    }

    public int getEndIdx() {
        return Math.min(getStartIdx() + itemsCountPerPage, articles.size()); // 지정 페이지의 종료 게시물의 배열 인덱스 (ArticleView에서 사용)
    }

    public int getPageBlockNo() {
        return (int)Math.ceil((double)currentPageNo /pageCntPerBlock);
    }

    public int getTotalPageNo() {
        return (int) Math.ceil((double) articles.size() / itemsCountPerPage);
    }

    public int getStartPageNo() {
        return pageCntPerBlock * (getPageBlockNo() - 1) + 1; // 시작 페이지
    }

    public int getEndPageNo() {
        return Math.min(pageCntPerBlock * getPageBlockNo(), getTotalPageNo()); // 마지막 페이지
    }

    public int getCurrentPageNo() {
        return currentPageNo;
    }

    public void selectPage(int pageNo) {
        if(pageNo >= 1 && pageNo <= getEndPageNo()) {
            currentPageNo = pageNo;
        }
    }
}
