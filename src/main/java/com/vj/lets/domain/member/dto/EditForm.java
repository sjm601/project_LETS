package com.vj.lets.domain.member.dto;

import lombok.*;

/**
 * 회원 수정 용 폼 객체
 *
 * @author VJ특공대 김종원
 * @version 1.0
 * @since 2023-09-14 (목)
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class EditForm {

    String password;
    String name;
    String phoneNumber;
    String gender;
    int age;

}
