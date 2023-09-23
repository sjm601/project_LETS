package com.vj.lets.domain.support.dto;

import lombok.*;

/**
 * FAQ DTO Bean
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
public class Faq {

    private int id;
    private String title;
    private String content;
    private int categoryId;

}
