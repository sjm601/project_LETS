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
public class ArticleCreateForm {
    /** 게시글 제목 */
    private String title;
    /** 게시글 내용 */
    private String content;
    /** 그룹 아이디 */
    private int studyGroupId;

}
