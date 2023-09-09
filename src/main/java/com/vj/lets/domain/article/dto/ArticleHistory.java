package com.vj.lets.domain.article.dto;

import lombok.*;

/**
* 클래스 설명 : ArticleHistory DTO
* 작성일 : 2023-09-08
* @author : 이한솔
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
