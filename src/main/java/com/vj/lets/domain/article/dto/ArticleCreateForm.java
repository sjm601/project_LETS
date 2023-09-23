package com.vj.lets.domain.article.dto;

import lombok.*;

/**
 * 게시글 생성 용 폼 객체
 *
 * @author VJ특공대 이한솔
 * @version 1.0
 * @since 2023-09-22 (금)
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
