package com.vj.lets.domain.cafe.dto;

import lombok.*;

/**
 * Cafe History dto
 *
 * @author VJ특공대 강소영
 * @version 1.0
 * @since 2023. 9. 7.
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class CafeHistory {
    private int id;
    private String status;
    private String modifyDate;
    private int cafeId;
}
