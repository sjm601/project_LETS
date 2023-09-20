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

    /**
     * 게시글 등록
     * @param article : 게시글
     */
    @Transactional
    @Override
    public void create(Article article) {
        articleMapper.create(article);
        articleHistoryMapper.create();
    }

    /**
     * 게시글 검색
     * @param keyword : 검색어
     * @return : 검색된 게시글 목록
     */
    @Override
    public Article search(String keyword) {
        return articleMapper.search(keyword);
    }

    /**
     * 게시글 수정
     * @param article : 게시글
     */
    @Transactional
    @Override
    public void update(Article article) {
        articleMapper.update(article);
        articleHistoryMapper.createByUpdate(article.getId(), ArticleHistoryComment.UPDATE.getComment());
    }

    /**
     * 게시글 삭제
     * @param articleId : 게시글 번호
     */
    @Transactional
    @Override
    public void delete(int articleId) {
        articleMapper.delete(articleId);
        articleHistoryMapper.createByUpdate(articleId, ArticleHistoryComment.DELETE.getComment());
    }

    /**
     * 게시글 목록 (검색값 , 페이지처리 포함)
     * @param pageParams : 페이지
     * @return
     */
    @Override
    public List<Map<String, Object>> findByPage(PageParams pageParams) {
        return articleMapper.findByPage(pageParams);
    }

    /**
     * 페이지 처리(검색 값 포함)에 필요한 게시글의 갯수
     * @param keyword : 검색어
     * @return : 검색된 게시글 갯수
     */
    @Override
    public int getCountAll(String keyword) {
        return articleMapper.getCountAll(keyword);
    }

    /**
     * 게시글 번호로 해당 게시글 찾기
     * @param id : 게시글 번호
     * @return : 해당 게시글
     */
    @Override
    public Article findById(int id) {
        return articleMapper.findById(id);
    }

    /**
     * 해당 게시글의 번호로 댓글들 검색
     * @param articleIds : 해당 페이지에 나오는 게시글 번호들
     * @return : 댓글 목록
     */
    @Override
    public List<Map<String, Object>> findComment(List<Integer> articleIds) {
        return articleMapper.findComment(articleIds);
    }

    /**
     * 최근 게시글 목록
     * @return : 최근 게시글 목록 (3개까지)
     */
    @Override
    public List<Article> getRecentArticles() {
        return articleMapper.getRecentArticles();
    }
}
