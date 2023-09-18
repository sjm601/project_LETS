package com.vj.lets.domain.article.mapper;

import com.vj.lets.domain.article.dto.ArticleComment;
import org.apache.ibatis.annotations.Mapper;

/**
* 클래스 설명 : ArticleCommentMapper 답글 매퍼 파일
* 작성일 : 2023-09-14
* @author : 이한솔
*/

@Mapper
public interface ArticleCommentMapper {
    /** 댓글 생성*/
    public void create (ArticleComment articleComment);

    /** 댓글 삭제*/
    public void delete (int id);

    /** id로 해당 댓글 찾기 (수정 ,삭제할 때 필요)*/
    public ArticleComment findById(int id);

}
