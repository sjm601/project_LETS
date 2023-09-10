package com.vj.lets.domain.support.dto;

import lombok.*;

/**
 * FAQ 카테고리 DTO Bean
 *
 * @author VJ특공대 김종원
 * @version 1.0
 * @since 2023-09-10 (일)
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class FaqCategory {

    int id;
    String name;
    String description;

}
