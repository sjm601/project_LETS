package com.vj.lets.domain.group.dto;

import lombok.*;

/**
 * 그룹 가입 신청 Bean
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
public class GroupContact {

    private int id;
    private String regdate;
    private int memberId;
    private int studyGroupId;

}
