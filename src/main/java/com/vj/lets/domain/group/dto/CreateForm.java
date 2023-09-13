package com.vj.lets.domain.group.dto;

import lombok.*;

/**
 * 스터디 그룹 생성 폼
 *
 * @author VJ특공대 이희영
 * @version 1.0
 * @since 2023-09-13 (수)
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CreateForm {
    private String imagePath;
    private String name;
    private int totalCount;
    private String subject;
    private String siGunGuName;
}
