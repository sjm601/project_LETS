package com.vj.lets.domain.article.dto;

import lombok.*;

/**
 * 게시글 객체
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
public class Article {

    /**
     * 게시글 번호
     */
    private int id;

    /**
     * 게시글 제목
     */
    private String title;

    /**
     * 게시글 내용
     */
    private String content;

    /**
     * 게시글 작성일자
     */
    private String regdate;

    /**
     * 게시글 사진
     */
    private String imagePath;

    /**
     * 게시글 상태
     */
    private String status;

    /**
     * 회원번호 (참조)
     */
    private int memberId;

    /**
     * 그룹 아이디 (참조)
     */
    private int studyGroupId;

}
