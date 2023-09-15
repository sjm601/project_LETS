package com.vj.lets.domain.article.mapper;

import com.vj.lets.domain.article.dto.ArticleComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* 클래스 설명 : ArticleCommentMapper 답글 매퍼 파일
* 작성일 : 2023-09-14
* @author : 이한솔
*/

@Mapper
public interface ArticleCommentMapper {
    /** 댓글 생성*/
    public void create (ArticleComment articleComment, @Param("articleId")int id);

    /** 댓글 삭제*/
    public void delete (int id);

    /** 댓글 목록*/
   public List<ArticleComment> findCommentAll ();
}
