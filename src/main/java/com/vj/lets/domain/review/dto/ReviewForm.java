package com.vj.lets.domain.review.dto;

import lombok.*;

/**
 * 리뷰 폼 객체
 *
 * @author VJ특공대 김종원
 * @version 1.0
 * @since 2023-09-15 (금)
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class ReviewForm {

    private int reviewId;
    private String content;
    private int rating;
    private int reservationId;

}
