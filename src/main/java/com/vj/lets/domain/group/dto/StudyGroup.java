package com.vj.lets.domain.group.dto;

import lombok.*;

/**
 * 스터디 그룹 빈
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
public class StudyGroup {
    private int id;
    private String name;
    private int headCount;
    private String regdate;
    private String imagePath;
    private String subject;
    private String status;
    private int siGunGuId;
}