package com.vj.lets.domain.article.service;


import com.vj.lets.domain.article.dto.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* 클래스 설명 : Article 관련 로직 처리 및 관리
* 작성일 : 2023-09-08
* @author : 이한솔
*/
public interface ArticleService {
    /** 게시글 생성*/
    public void creat (Article article);

    /** 게시글 검색*/
    public Article search (@Param("keyword") String keyword);

    /** 게시글 수정*/
    public void update (Article article);

    /** 게시글 삭제*/
    public void delete (int id);

    /** 게시글 목록 (추후 페이징 처리 까지 해야함).*/
//    public List<Article> findAll ();

}
