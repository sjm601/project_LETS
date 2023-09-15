package com.vj.lets.domain.review.mapper;

import com.vj.lets.domain.review.dto.Review;
import jakarta.annotation.Priority;
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
     * @return 리뷰 목록
     */
    public List<Map<String, Object>> readByCafe(int cafeId);

    /**
     * 회원 ID로 리뷰 목록 조회
     *
     * @param memberId 회원 ID
     * @return 리뷰 목록
     */
    public List<Map<String, Object>> readByMember(int memberId);

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

}
