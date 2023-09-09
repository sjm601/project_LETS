package com.vj.lets.domain.review.dto;

import lombok.*;

/**
 * Review Comment History dto
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
public class ReviewCommentHistory {
    private int id;
    private String status;
    private String modifyDate;
    private int reviewCommentId;
}
