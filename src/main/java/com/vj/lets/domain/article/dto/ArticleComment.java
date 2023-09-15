package com.vj.lets.domain.article.dto;

import lombok.*;

/**
* 클래스 설명 : ArticleComment DTO
* 작성일 : 2023-09-011
* @author : 이한솔
*/

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ArticleComment {
    /** 게시글 댓글 번호*/
    private int id;
    /** 댓글 내용 */
    private String content;
    /** 댓글 작성일자*/
    private String regdate;
    /** 댓글 상태*/
    private String status;
    /** 회원번호 (참조)*/
    private int memberId;
    /** 게시글 번호(참조)*/
    private int articleId;




}
