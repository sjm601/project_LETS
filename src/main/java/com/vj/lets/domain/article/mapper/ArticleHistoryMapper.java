package com.vj.lets.domain.article.mapper;

import com.vj.lets.domain.article.dto.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* 클래스 설명 : ArticleHistoryMapper 파일
* 작성일 : 2023-09-09
* @author : 이한솔
*/

@Mapper
public interface ArticleHistoryMapper {
    /** 게시글 생성*/
    public void creat ();

    /** 게시글 수정*/
    public void update (int articleId);

    /** 게시글 삭제*/
    public void delete (int articleId);
}
