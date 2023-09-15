package com.vj.lets.domain.article.service;

import com.vj.lets.domain.article.dto.ArticleComment;
import com.vj.lets.domain.article.mapper.ArticleCommentHistoryMapper;
import com.vj.lets.domain.article.mapper.ArticleCommentMapper;
import com.vj.lets.domain.article.util.ArticleCommentHistoryComment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* 클래스 설명 : ArticleComment 서비스 구현체
* 작성일 : 2023-09-08
* @author : 이한솔
*/
@Service
@RequiredArgsConstructor
public class ArticleCommentServiceImpl implements ArticleCommentService {
    private final ArticleCommentMapper articleCommentMapper;
    private final ArticleCommentHistoryMapper articleCommentHistoryMapper;

    @Override
    @Transactional
    public void create(ArticleComment articleComment, int id) {
        articleCommentMapper.create(articleComment, id);
        articleCommentHistoryMapper.create();
    }

    @Override
    @Transactional
    public void delete(int id) {
        articleCommentMapper.delete(id);
        articleCommentHistoryMapper.createByUpdate(id, ArticleCommentHistoryComment.DELETE.getComment());
    }

    @Override
    public List<ArticleComment> findCommentAll() {
        return articleCommentMapper.findCommentAll();
    }
}
