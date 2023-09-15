package com.vj.lets.domain.support.dto;

import lombok.*;

/**
 * 입점 신청 DTO Bean
 *
 * @author VJ특공대 김종원
 * @version 1.0
 * @since 2023-09-10 (일)
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class Contact {

    int id;
    String email;
    String name;
    String cafeName;
    String phoneNumber;
    String address;
    String message;
    long businessNumber;
    String contactDate;
    String status;

}
