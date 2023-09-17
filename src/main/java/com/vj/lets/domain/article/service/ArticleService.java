package com.vj.lets.domain.article.service;


import com.vj.lets.domain.article.dto.Article;
import com.vj.lets.domain.common.web.PageParams;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* 클래스 설명 : Article 관련 로직 처리 및 관리
* 작성일 : 2023-09-08
* @author : 이한솔
*/
public interface ArticleService {
    /** 게시글 생성*/
    public void create (Article article);

    /** 게시글 검색*/
    public Article search (@Param("keyword") String keyword);

    /** 게시글 수정*/
    public void update (Article article);

    /** 게시글 삭제*/
    @Transactional
    public void delete(int id);

    /** 게시글 목록 (추후 페이징 처리 까지 해야함).*/
    public List<Article> findAll ();

    /** 게시글 목록 (페이지에 따라 보이는 목록임)*/
    public List<Article> findByPage (PageParams pageParams);

    /** 페이징 계산(검색값 포함)에 필요한 게시글 전체 갯수 반환 */
    public int getCountAll(@Param("keyword") String keyword);

    /** id로 게시글 찾기 (수정 ,삭제할 때 필요?)*/
    public Article findById(int id);


}
