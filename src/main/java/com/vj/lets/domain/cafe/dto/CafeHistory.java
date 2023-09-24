package com.vj.lets.domain.cafe.dto;

import lombok.*;

/**
 * 카페 히스토리 DTO
 *
 * @author VJ특공대 강소영
 * @version 1.0
 * @since 2023. 9. 7. (목)
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class CafeHistory {

    private int id;
    private String modifyComment;
    private String modifyDate;
    private int cafeId;

}
