package com.vj.lets.domain.member.util;


import lombok.Getter;

/**
 * 회원 타입 이넘 클래스
 *
 * @author VJ특공대 김종원
 * @version 1.0
 * @since 2023-09-15 (금)
 */
@Getter
public enum MemberType {
    GUEST("guest"), HOST("host"), ADMIN("admin");

    private final String type;

    private MemberType(String type) {
        this.type = type;
    }

}
