package com.vj.lets.domain.review.dto;

import lombok.*;

/**
 * 리뷰 히스토리 DTO Bean
 *
 * @author VJ특공대 김종원
 * @version 1.0
 * @since 2023-09-11 (월)
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ReviewHistory {

    private int id;
    private String modifyComment;
    private String modifyDate;
    private int reviewId;
}
