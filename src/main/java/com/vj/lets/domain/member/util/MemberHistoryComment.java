package com.vj.lets.domain.member.util;


public enum MemberHistoryComment {
    CREATE("create"), UPDATE("update"), DELETE("delete");

    private final String comment;

    private MemberHistoryComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }
}
