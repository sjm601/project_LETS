package com.vj.lets.domain.article.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* 클래스 설명 : ArticleHistoryMapper 파일
* 작성일 : 2023-09-09
* @author : 이한솔
*/

@Mapper
public interface ArticleHistoryMapper {
    /** 게시글 히스토리 생성*/
    public void create ();

    /** 게시글 히스토리 수정*/
    public void createByUpdate(@Param("articleId") int articleId, @Param("comment") String comment);

}
