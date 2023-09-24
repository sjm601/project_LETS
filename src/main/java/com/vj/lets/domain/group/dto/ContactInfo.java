package com.vj.lets.domain.group.dto;

import lombok.*;

/**
 * 그룹 가입 신청 정보 Bean
 *
 * @author 이희영
 * @version 1.0
 * @since 2023-09-11 (월)
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ContactInfo {

    private int id;
    private String name;
    private String gender;
    private String birthday;
    private int studyGroupId;
    
}
