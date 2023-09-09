package com.vj.lets.domain.group.dto;

import lombok.*;

/**
 * 스터디 멤버 리스트 빈
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
public class GroupMemberList {
    private int id;
    private String position;
    private int memberId;
    private int studyGroupId;
}
