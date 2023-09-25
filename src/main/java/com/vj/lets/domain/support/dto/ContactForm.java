package com.vj.lets.domain.support.dto;

import lombok.*;

/**
 * 입점 신청 폼 객체
 *
 * @author VJ특공대 김종원
 * @version 1.0
 * @since 2023-09-24 (일)
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class ContactForm {

    String email;
    String name;
    String phoneNumber;
    String cafeName;
    String address;
    long businessNumber;
    String message;

}
