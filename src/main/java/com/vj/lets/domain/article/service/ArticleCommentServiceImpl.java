package com.vj.lets.domain.article.service;

import com.vj.lets.domain.article.dto.ArticleComment;
import com.vj.lets.domain.article.mapper.ArticleCommentHistoryMapper;
import com.vj.lets.domain.article.mapper.ArticleCommentMapper;
import com.vj.lets.domain.article.util.ArticleCommentHistoryComment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 댓글 관련 비즈니스 로직 처리 및 트랜잭션 관리 서비스 구현체
 *
 * @author VJ특공대 이한솔
 * @version 1.0
 * @since 2023-09-08 (금)
 */
@Service
@RequiredArgsConstructor
public class ArticleCommentServiceImpl implements ArticleCommentService {
    private final ArticleCommentMapper articleCommentMapper;
    private final ArticleCommentHistoryMapper articleCommentHistoryMapper;

    /**
     * 댓글 생성
     *
     * @param articleComment 댓글
     */
    @Override
    @Transactional
    public void create(ArticleComment articleComment) {
        articleCommentMapper.create(articleComment);
        articleCommentHistoryMapper.create();
    }

    /**
     * 댓글 삭제
     *
     * @param id : 댓글 아이디
     */
    @Override
    @Transactional
    public void delete(int id) {
        articleCommentMapper.delete(id);
        articleCommentHistoryMapper.createByUpdate(id, ArticleCommentHistoryComment.DELETE.getComment());
    }

    /**
     * 댓글 아이디로 해당 댓글 찾기
     *
     * @param id : 댓글 아이디
     */
    @Override
    public ArticleComment findById(int id) {
        return articleCommentMapper.findById(id);
    }
}
