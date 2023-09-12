package com.vj.lets.domain.review.util;


public enum ReviewHistoryComment {
    CREATE("create"), UPDATE("update"), DELETE("delete");

    private final String comment;

    private ReviewHistoryComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }
}
