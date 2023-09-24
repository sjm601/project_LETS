package com.vj.lets.domain.cafe.dto;

import lombok.*;

/**
 * 카페 옵션 리스트 DTO
 *
 * @author VJ특공대 강소영
 * @version 1.0
 * @since 2023-09-08 (금)
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class CafeOptionList {

    private int id;
    private int cafeId;
    private int optionId;

}
