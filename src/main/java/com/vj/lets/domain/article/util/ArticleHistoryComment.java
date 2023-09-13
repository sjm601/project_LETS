package com.vj.lets.domain.article.util;


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
