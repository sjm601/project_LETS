package com.vj.lets.domain.article.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 게시글 히스토리 관련 매퍼 인터페이스
 *
 * @author VJ특공대 이한솔
 * @version 1.0
 * @since 2023-09-09 (토)
 */
@Mapper
public interface ArticleHistoryMapper {

    /**
     * 게시글 히스토리 등록
     */
    public void create();

    /**
     * 게시글 수정으로 인한 히스토리 업데이트
     *
     * @param articleId 게시글 번호
     * @param comment   변경 정보
     */
    public void createByUpdate(@Param("articleId") int articleId, @Param("comment") String comment);

}
