package com.vj.lets.domain.group.dto;

import lombok.*;

/**
 * 스터디 일정 빈
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
public class StudyPlan {
    private int id;
    private int totalCount;
    private int currentCount;
    private String regdate;
    private int studyGroupId;
    private int reservationId;
}
