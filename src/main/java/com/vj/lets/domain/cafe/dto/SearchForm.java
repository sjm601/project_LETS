package com.vj.lets.domain.cafe.dto;

import lombok.*;

/**
 * Cafe 검색을 위한 dto
 *
 * @author VJ특공대 강소영
 * @version 1.0
 * @since 2023-09-11 (월)
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class SearchForm {
    String name;
    String countPerson;
    String option;
    double currentX;
    double currentY;
}
