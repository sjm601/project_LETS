package com.vj.lets.domain.review.dto;

import lombok.*;

/**
 * Review History dto
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
public class ReviewHistory {
    private int id;
    private String status;
    private String modifyDate;
    private int reviewId;
}
