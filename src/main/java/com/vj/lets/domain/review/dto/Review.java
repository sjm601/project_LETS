package com.vj.lets.domain.review.dto;

import lombok.*;

/**
 * 리뷰 DTO Bean
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
public class Review {

    private int id;
    private String title;
    private String content;
    private int rating;
    private String writeDate;
    private String status;
    private int reservationId;
    private int memberId;

}
