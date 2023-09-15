package com.vj.lets.domain.article.util;

/**
* 클래스 설명 : ArticleCommentHistoryComment
* 작성일 : 2023-09-14
* @author : 이한솔
*/

public enum ArticleCommentHistoryComment {
    CREATE("create"), DELETE("delete");

    private final String comment;

    private ArticleCommentHistoryComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }
}
