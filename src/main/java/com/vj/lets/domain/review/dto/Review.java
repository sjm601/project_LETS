package com.vj.lets.domain.review.dto;

import lombok.*;

/**
 * Review dto
 *
 * @author 강소영
 * @version 1.0
 * @since 2023-09-08 (금)
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class Review {
    private int id;
    private String title;
    private String content;
    private int starRating;
    private String writeDate;
    private int roomId;
    private int memberId;
}
