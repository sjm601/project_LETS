package com.vj.lets.domain.group.dto;

import lombok.*;

/**
 * 검색 객체
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
public class Search {
    private String keyword;
    private String subject;
}
