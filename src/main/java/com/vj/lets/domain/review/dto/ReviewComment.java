package com.vj.lets.domain.review.dto;

import lombok.*;

/**
 * Review Comment dto
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
public class ReviewComment {
    private int id;
    private String content;
    private String regdate;
    private int reviewId;
    private int memberId;
}
