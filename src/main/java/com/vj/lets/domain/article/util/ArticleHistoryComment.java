package com.vj.lets.domain.article.util;

/**
* 클래스 설명 : ArticleHistoryComment
* 작성일 : 2023-09-14
* @author : 이한솔
*/

public enum ArticleHistoryComment {
    CREATE("create"), UPDATE("update"), DELETE("delete");

    private final String comment;

    private ArticleHistoryComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }
}
