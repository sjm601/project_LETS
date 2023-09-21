package com.vj.lets.web.member.exception;

/**
 * 회원 관련 예외 객체
 *
 * @author VJ특공대 김종원
 * @version 1.0
 * @since 2023-09-21 (목)
 */
public class MemberException extends RuntimeException {

    public MemberException() {
        super();
    }

    public MemberException(String message) {
        super(message);
    }

    public MemberException(String message, Throwable cause) {
        super(message, cause);
    }

}
