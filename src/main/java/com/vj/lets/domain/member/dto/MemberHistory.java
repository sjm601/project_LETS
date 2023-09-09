package com.vj.lets.domain.member.dto;

import lombok.*;


/**
 * 회원 히스토리 DTO Bean
 *
 * @author VJ특공대 김종원
 * @version 1.0
 * @since 2023-09-08 (금)
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class MemberHistory {
    private int id;
    private String status;
    private String modifyDate;
    private int memberId;
}
