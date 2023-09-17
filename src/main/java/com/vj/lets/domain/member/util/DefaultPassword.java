package com.vj.lets.domain.member.util;

import lombok.Getter;

/**
 * 회원 디폴트 비밀번호 이넘 클래스
 *
 * @author VJ특공대 김종원
 * @version 1.0
 * @since 2023-09-15 (금)
 */
@Getter
public enum DefaultPassword {

    DEFAULT("1111");

    private final String password;

    private DefaultPassword(String password) {
        this.password = password;
    }

}
