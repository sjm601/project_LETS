package com.vj.lets.domain.article.mapper;

import com.vj.lets.domain.article.dto.Article;
import com.vj.lets.domain.article.dto.ArticleUpdateForm;
import com.vj.lets.domain.group.util.PageParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 게시글 관련 매퍼 인터페이스
 *
 * @author VJ특공대 이한솔
 * @version 1.0
 * @since 2023-09-08 (금)
 */

@Mapper
public interface ArticleMapper {
    /**
     * 게시글 등록
     * @param article : 게시글
     */
    public void create (Article article);

    /**
     * 게시글 수정
     *
     * @param article : 게시글
     */
    public void update (Article article);

    /**
     * 게시글 정보 수정 시 수정 정보
     *
     * @param articleId 게시글 id
     * @return 체크 용 폼 객체
     */
    public ArticleUpdateForm readUpdateForm(int articleId);

    /**
     * 게시글 삭제
     *
     * @param id : 게시글 번호
     */
    public void delete (int id);

    /**
     * 게시글 검색
     * @param keyword : 검색어
     *
     * @return : 검색된 게시글 목록
     */
    public Article search (@Param("keyword") String keyword);


    /**
     * 게시글 목록 (검색값 , 페이지처리 포함)
     *
     * @param pageParams : 페이지
     * @return : 페이징 처리된 게시글 목록들
     */
    public List<Map<String, Object>> findByPage (@Param("pageParams") PageParams pageParams, @Param("groupId")int groupId);

    /**
     * 페이지 처리(검색 값 포함)에 필요한 게시글의 갯수
     *
     * @param keyword : 검색어
     * @return : 검색된 게시글 갯수
     */
    public int getCountAll(@Param("keyword") String keyword, @Param("groupId")int groupId);

    /**
     * 게시글 번호로 해당 게시글 찾기 (삭제, 수정 시 필요)
     *
     * @param id : 게시글 번호
     * @return : 해당 게시글
     */
    public Article findById(int id);

    /**
     * 해당 게시글의 번호로 댓글들 검색
     *
     * @param articleIds : 해당 페이지에 나오는 게시글 번호들
     * @return : 댓글 목록
     */
    public List<Map<String, Object>> findComment (List<Integer> articleIds);

    /**
     * 최근 게시글 목록 검색
     *
     * @param id : 그룹 아이디
     * @return  : 최근 게시글
     */
    public List<Article> getRecentArticles (@Param("groupId")int id);

    /**
     * 게시글 수정을 위한 게시글 번호로 게시글 찾기
     *
     * @param articleId : 게시글 ID
     * @return : 해당 게시글
     */
    public ArticleUpdateForm findArticle(int articleId);

}
