package com.vj.lets.domain.article.dto;

import lombok.*;

/**
 * 게시글 댓글 객체
 *
 * @author VJ특공대 이한솔
 * @version 1.0
 * @since 2023-09-08 (금)
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ArticleComment {

    /**
     * 게시글 댓글 번호
     */
    private int id;

    /**
     * 댓글 내용
     */
    private String content;

    /**
     * 댓글 작성일자
     */
    private String regdate;

    /**
     * 댓글 상태
     */
    private String status;

    /**
     * 회원번호 (참조)
     */
    private int memberId;

    /**
     * 게시글 번호(참조)
     */
    private int articleId;


}
