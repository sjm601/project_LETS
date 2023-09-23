package com.vj.lets.domain.review.service;

import com.vj.lets.domain.common.web.PageParams;
import com.vj.lets.domain.review.dto.Review;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 리뷰 관련 비즈니스 로직 처리 및 트랜잭션 관리 서비스 인터페이스
 *
 * @author VJ특공대 김종원
 * @version 1.0
 * @since 2023-09-11 (월)
 */
public interface ReviewService {

    /**
     * 리뷰 등록
     *
     * @param review 리뷰 정보
     */
    public void register(Review review);

    /**
     * 전체 리뷰 목록 조회
     *
     * @return 리뷰 목록
     */
    public List<Map<String, Object>> getReviewList();

    /**
     * 카페에 등록된 전체 리뷰 목록 조회
     *
     * @param cafeId 카페 ID
     * @return 리뷰 목록
     */
    public List<Map<String, Object>> getReviewListByCafe(int cafeId, PageParams pageParams);

    /**
     * 카페에 등록한 리뷰 점수별 개수 조회
     *
     * @param cafeId 카페 ID
     * @return 점수별 개수
     */
    public Map<Integer, Object> getCountReviewRatingByCafe(int cafeId);

    /**
     * 특정 회원이 작성한 리뷰 갯수 조회
     *
     * @param memberId 회원 ID
     * @return 리뷰 갯수
     * @see com.vj.lets.web.dashboard.controller.MypageController
     */
    public int getCountReviewByMember(int memberId);

    /**
     * 특정 회원이 작성한 전체 리뷰 목록 조회
     *
     * @param memberId 회원 ID
     * @return 리뷰 목록
     */
    public List<Map<String, Object>> getReviewListByMember(int memberId, PageParams pageParams);

    /**
     * 리뷰 수정
     *
     * @param review 리뷰 정보
     */
    public void editReview(Review review);

    /**
     * 리뷰 삭제
     *
     * @param id 리뷰 ID
     */
    public void removeReview(int id);

    /**
     * 호스트의 카페에 대한 리뷰 수 출력
     * @param cafeId 카페ID
     * @return 리뷰 갯수
     */
    public int getCountByHost(int cafeId);

    /**
     * 카페 ID로 호스트의 카페 리뷰 목록 조회
     *
     * @param cafeId   카페 ID
     * @param pageParams 페이징 객체
     * @return 리뷰 목록
     */
    public List<Map<String, Object>> getByHost(@Param("cafeId") int cafeId, @Param("pageParams") PageParams pageParams);

    /**
     * 오늘 호스트의 카페에 등록된 리뷰를 불러오기 위해 사용
     * @param cafeId 카페ID
     * @return 오늘 호스트 카페에 등록된 리뷰 수
     */
    public int getTodayReview(int cafeId);


}
