package com.vj.lets.domain.article.service;


import com.vj.lets.domain.article.dto.ArticleComment;

/**
 * 댓글 관련 비즈니스 로직 처리 및 트랜잭션 관리 서비스 인터페이스
 *
 * @author VJ특공대 이한솔
 * @version 1.0
 * @since 2023-09-14 (목)
 */
public interface ArticleCommentService {
    /**
     * 댓글 생성
     *
     * @param articleComment : 댓글 
     */
    public void create (ArticleComment articleComment);

    /**
     * 댓글 삭제
     *
     * @param id : 댓글 아이디
     */
    public void delete (int id);

    /**
     * 댓글 번호로 해당 댓글 찾기
     *
     * @param id : 댓글 아이디
     */
    public ArticleComment findById(int id);

}

