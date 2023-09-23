package com.vj.lets.domain.article.mapper;

import com.vj.lets.domain.article.dto.ArticleComment;
import org.apache.ibatis.annotations.Mapper;

/**
 * 게시글 댓글 관련 매퍼 인터페이스
 *
 * @author VJ특공대 이한솔
 * @version 1.0
 * @since 2023-09-14 (목)
 */

@Mapper
public interface ArticleCommentMapper {
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
     * 댓글 아이디로 해당 댓글 찾기
     *
     * @param id : 댓글 아이디
     * @return : 해당 댓글
     */
    public ArticleComment findById(int id);

}
