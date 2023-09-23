package com.vj.lets.domain.article.dto;

import lombok.*;

/**
 * 게시글 댓글 히스토리 객체
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
public class ArticleCommentHistory {
    /** 게시글 댓글 히스토리 번호*/
    private int id;
    /** 댓글 히스토리 변경내용 */
    private String modifyComment;
    /** 댓글 히스토리 변경일자*/
    private String modifyDate;
    /** 게시글 댓글번호(참조)*/
    private int articleCommentId;




}
