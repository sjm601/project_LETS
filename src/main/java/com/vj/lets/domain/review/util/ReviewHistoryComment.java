package com.vj.lets.domain.review.util;


import lombok.Getter;

/**
 * 리뷰 히스토리 코멘트 enum 클래스
 *
 * @author VJ특공대 김종원
 * @version 1.0
 * @since 2023-09-15 (금)
 */
@Getter
public enum ReviewHistoryComment {
    CREATE("create"), UPDATE("update"), DELETE("delete");

    private final String comment;

    private ReviewHistoryComment(String comment) {
        this.comment = comment;
    }

}
