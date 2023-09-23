package com.vj.lets.domain.member.util;


import lombok.Getter;

/**
 * 회원 히스토리 코멘트 enum 클래스
 *
 * @author VJ특공대 김종원
 * @version 1.0
 * @since 2023-09-15 (금)
 */
@Getter
public enum MemberHistoryComment {
    CREATE("create"), UPDATE("update"), DELETE("delete");

    private final String comment;

    private MemberHistoryComment(String comment) {
        this.comment = comment;
    }

}
