package com.vj.lets.domain.review.mapper;

import com.vj.lets.domain.common.web.PageParams;
import com.vj.lets.domain.review.dto.Review;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 리뷰 관련 매퍼 인터페이스
 *
 * @author VJ특공대 김종원
 * @version 1.0
 * @since 2023-09-11 (월)
 */
@Mapper
public interface ReviewMapper {

    /**
     * 리뷰 생성
     *
     * @param review 리뷰 정보
     */
    public void create(Review review);

    /**
     * 전체 리뷰 목록 조회
     *
     * @return 리뷰 목록
     */
    public List<Map<String, Object>> readAll();

    /**
     * 카페 ID로 리뷰 목록 조회
     *
     * @param cafeId 카페 ID
     * @param pageParams 페이징
     * @return 리뷰 목록
     */
    public List<Map<String, Object>> readByCafe(@Param("cafeId") int cafeId,
                                                @Param("pageParams") PageParams pageParams);

    /**
     * 카페별 리뷰점수 개수 통계 조회
     *
     * @param cafeId 카페 ID
     * @return 별점별 통계 목록
     */
    public Map<Integer, Object> countByReviewRating(int cafeId);

    /**
     * 회원 ID로 리뷰 작성 수 조회
     *
     * @param memberId 회원 ID
     * @return 리뷰 작성 수
     * @see com.vj.lets.web.dashboard.controller.MypageController
     */
    public int readCountByMember(int memberId);

    /**
     * 회원 ID로 리뷰 목록 조회
     *
     * @param memberId   회원 ID
     * @param pageParams 페이징 객체
     * @return 리뷰 목록
     * @see com.vj.lets.web.dashboard.controller.MypageController
     */
    public List<Map<String, Object>> readByMember(@Param("memberId") int memberId, @Param("pageParams") PageParams pageParams);

    /**
     * 예약 ID로 리뷰 댓글 조회
     *
     * @param reservationId 예약 ID
     * @return 리뷰 정보
     */
    public Map<String, String> readHostCommentByResId(int reservationId);

    /**
     * 예약 ID로 리뷰 유무 조회
     *
     * @param reservationId 예약 ID
     * @return 리뷰 유무
     */
    public boolean readCountByReservationId(int reservationId);

    /**
     * 리뷰 수정
     *
     * @param review 리뷰 정보
     */
    public void update(Review review);

    /**
     * 리뷰 비활성화
     *
     * @param id 리뷰 ID
     */
    public void disabled(int id);

    /**
     * 호스트의 카페에 대한 리뷰 정보 출력
     * @param cafeId
     * @return 리뷰 수
     */
    public int readCountByHost(int cafeId);

    /**
     * 카페 ID로 호스트의 카페 리뷰 목록 조회
     *
     * @param cafeId   카페 ID
     * @param pageParams 페이징 객체
     * @return 리뷰 목록
     */
    public List<Map<String, Object>> readByHost(@Param("cafeId") int cafeId, @Param("pageParams") PageParams pageParams);

    /**
     * 예약 아이디로 리뷰 답변 완료 유무 판단 위해 사용
     * @param reservationId
     * @return 예약 아이디로 작성된 리뷰가 2개일 시 답변 완료로 판단
     */
    public int readCountByReview(int reservationId);

    /**
     * 오늘 호스트의 카페에 등록된 리뷰를 불러오기 위해 사용
     * @param cafeId
     * @return 오늘 호스트 카페에 등록된 리뷰 수
     */
    public int readTodayReview(int cafeId);

    /**
     * 답변 수정을 위해 답변 호출
     * @param reservationId
     * @param memberId
     * @return 작성한 답변 id, content
     */
    public String callReviewComment(@Param("reservationId") int reservationId,@Param("memberId") int memberId);


}
