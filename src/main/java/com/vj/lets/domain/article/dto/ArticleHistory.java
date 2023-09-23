package com.vj.lets.domain.article.dto;

import lombok.*;

/**
 * 게시글 히스토리 객체
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
public class ArticleHistory {
    /** 게시글 히스토리 번호*/
    private int id;
    /** 게시글 수정멘트 */
    private String modifyComment;
    /** 게시글 수정날짜 */
    private String modifyDate;
    /** 게시글 번호 (참조값)*/
    private int articleId;




}
