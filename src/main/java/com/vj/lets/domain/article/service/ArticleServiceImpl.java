package com.vj.lets.domain.article.service;

import com.vj.lets.domain.article.dto.Article;
import com.vj.lets.domain.article.mapper.ArticleHistoryMapper;
import com.vj.lets.domain.article.mapper.ArticleMapper;
import com.vj.lets.domain.article.util.ArticleHistoryComment;
import com.vj.lets.domain.common.web.PageParams;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
* 클래스 설명 : Article 서비스 구현체
* 작성일 : 2023-09-08
* @author : 이한솔
*/
@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleMapper articleMapper;
    private final ArticleHistoryMapper articleHistoryMapper;

    @Transactional
    @Override
    public void create(Article article) {
        articleMapper.create(article);
        articleHistoryMapper.create();
    }

    @Override
    public Article search(String keyword) {
        return articleMapper.search(keyword);
    }

    @Transactional
    @Override
    public void update(Article article) {
        articleMapper.update(article);
        articleHistoryMapper.createByUpdate(article.getId(), ArticleHistoryComment.UPDATE.getComment());
    }

    @Transactional
    @Override
    public void delete(int id) {
        articleMapper.delete(id);
        articleHistoryMapper.createByUpdate(id, ArticleHistoryComment.DELETE.getComment());
    }

    @Override
    public List<Map<String, Object>> findByPage(PageParams pageParams) {
        return articleMapper.findByPage(pageParams);
    }

    @Override
    public int getCountAll(String keyword) {
        return articleMapper.getCountAll(keyword);
    }

    @Override
    public Article findById(int id) {
        return articleMapper.findById(id);
    }

    @Override
    public List<Map<String, Object>> findComment(List<Integer> articleIds) {
        return articleMapper.findComment(articleIds);
    }


}
