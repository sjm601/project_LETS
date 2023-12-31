package com.vj.lets.domain.group.dto;

import lombok.*;

/**
 * 스터디 그룹 히스토리 Bean
 *
 * @author 이희영
 * @version 1.0
 * @since 2023-09-08 (금)
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class GroupHistory {

    private int id;
    private String modifyComment;
    private String modifyDate;
    private int studyGroupId;

}