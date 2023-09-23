package com.vj.lets.domain.article.util;

/**
 * 게시글 히스토리 코멘트 enum 클래스
 *
 * @author VJ특공대 이한솔
 * @version 1.0
 * @since 2023-09-13 (수)
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
