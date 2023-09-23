package com.vj.lets.domain.article.util;

/**
 * 댓글 히스토리 코멘트 enum 클래스
 *
 * @author VJ특공대 김종원
 * @version 1.0
 * @since 2023-09-14 (목)
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
