package com.vj.lets.domain.group.dto;

import lombok.*;

/**
 * 스터디 참여 리스트 빈
 *
 * @author VJ특공대 이희영
 * @version 1.0
 * @since 2023-09-20 (수)
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ParticipationList {
    private int id;
    private int memberId;
    private String memberType;
    private int studyPlanId;
}
