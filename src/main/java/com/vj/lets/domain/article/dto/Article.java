package com.vj.lets.domain.article.dto;

import lombok.*;

/**
* 클래스 설명 : ArticleDTO
* 작성일 : 2023-09-08
* @author : 이한솔
*/

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Article {
    /** 게시글 번호*/
    private int id;
    /** 게시글 제목 */
    private String title;
    /** 게시글 내용 */
    private String content;
    /** 게시글 작성일자*/
    private String regdate;
    /** 게시글 사진*/
    private String imagePath;
    /** 게시글 상태*/
    private String status;
    /** 회원번호 (참조)*/
    private int memberId;

}
