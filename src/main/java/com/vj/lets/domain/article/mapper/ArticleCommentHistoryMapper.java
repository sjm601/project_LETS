package com.vj.lets.domain.article.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 댓글 히스토리 관련 매퍼 인터페이스
 *
 * @author VJ특공대 이한솔
 * @version 1.0
 * @since 2023-09-14 (목)
 */

@Mapper
public interface ArticleCommentHistoryMapper {
    /**
     * 댓글 히스토리 생성
     *
     */
    public void create ();

    /**
     * 댓글 정보 수정(삭제) 시 히스토리 생성
     *
     * @param articleCommentId : 댓글 아이디
     * @param comment  변경 정보
     */
    public void createByUpdate(@Param("articleCommentId") int articleCommentId, @Param("comment") String comment);

}
