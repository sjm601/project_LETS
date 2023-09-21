package com.vj.lets.domain.review.service;

import com.vj.lets.domain.common.web.PageParams;
import com.vj.lets.domain.review.dto.Review;
import com.vj.lets.domain.review.mapper.ReviewHistoryMapper;
import com.vj.lets.domain.review.mapper.ReviewMapper;
import com.vj.lets.domain.review.util.ReviewHistoryComment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 리뷰 관련 비즈니스 로직 처리 및 트랜잭션 관리 서비스 구현체
 *
 * @author VJ특공대 김종원
 * @version 1.0
 * @since 2023-09-11 (월)
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class ReviewServiceImpl implements ReviewService {

    private final ReviewMapper reviewMapper;
    private final ReviewHistoryMapper reviewHistoryMapper;

    /**
     * 리뷰 등록
     *
     * @param review 리뷰 정보
     */
    @Override
    @Transactional
    public void register(Review review) {
        reviewMapper.create(review);
        reviewHistoryMapper.create();
    }

    /**
     * 전체 리뷰 목록 조회
     *
     * @return 리뷰 목록
     */
    @Override
    public List<Map<String, Object>> getReviewList() {
        return reviewMapper.readAll();
    }

    /**
     * 카페에 등록된 전체 리뷰 목록 조회
     *
     * @param cafeId 카페 ID
     * @return 리뷰 목록
     */
    @Override
    public List<Map<String, Object>> getReviewListByCafe(int cafeId, PageParams pageParams) {
        log.info("====={}", pageParams);
        List<Map<String, Object>> guestReviewList = reviewMapper.readByCafe(cafeId, pageParams);
        return addMaps(guestReviewList);
    }

    /**
     * 카페에 등록한 리뷰 점수별 개수 조회
     *
     * @param cafeId 카페 ID
     * @return 별점별 통계 목록
     */
    @Override
    public Map<Integer, Object> getCountReviewRatingByCafe(int cafeId) {
        return reviewMapper.countByReviewRating(cafeId);
    }

    @Override
    public int getCountReviewByMember(int memberId) {
        return reviewMapper.readCountByMember(memberId);
    }

    /**
     * 특정 회원이 작성한 전체 리뷰 목록 조회
     *
     * @param memberId 회원 ID
     * @return 리뷰 목록
     */
    @Override
    public List<Map<String, Object>> getReviewListByMember(int memberId, PageParams pageParams) {
        List<Map<String, Object>> guestReviewList = reviewMapper.readByMember(memberId, pageParams);
        return addMaps(guestReviewList);
    }

    /**
     * 리뷰 목록에 호스트 댓글 추가 메소드
     *
     * @param guestReviewList 게스트 작성 리뷰 목록
     * @return 호스트 작성 댓글 추가된 리뷰 목록
     */
    private List<Map<String, Object>> addMaps(List<Map<String, Object>> guestReviewList) {
        List<Map<String, Object>> reviewList = new ArrayList<>();
        if (guestReviewList == null) {
            return null;
        }

        for (Map<String, Object> map : guestReviewList) {
            int reservationId = Integer.parseInt(map.get("reservationId").toString());
            Map<String, String> hostComment = reviewMapper.readHostCommentByResId(reservationId);
            if (hostComment != null) {
                map.putAll(hostComment);
            }
            reviewList.add(map);
        }
        return reviewList;
    }

    /**
     * 리뷰 수정
     *
     * @param review 리뷰 정보
     */
    @Override
    @Transactional
    public void editReview(Review review) {
        reviewMapper.update(review);
        reviewHistoryMapper.createByUpdate(review.getId(), ReviewHistoryComment.UPDATE.getComment());
    }

    /**
     * 리뷰 삭제
     *
     * @param id 리뷰 ID
     */
    @Override
    @Transactional
    public void removeReview(int id) {
        reviewMapper.disabled(id);
        reviewHistoryMapper.createByUpdate(id, ReviewHistoryComment.DELETE.getComment());
    }

    /**
     * 리뷰 출력을 위한 호스트 카페에 총 리뷰수 출력
     * @param cafeId
     * @return 리뷰의 총 개수
     */
    @Override
    public int getCountByHost(int cafeId) {
        return reviewMapper.readCountByHost(cafeId);
    }

    /**
     * 리뷰 목록 출력
     * @param cafeId   카페 ID
     * @param pageParams 페이징 객체
     * @return 호스트 카페에 대한 고객들의 리뷰 리스트
     */
    @Override
    public List<Map<String, Object>> getByHost(int cafeId, PageParams pageParams) {
        return reviewMapper.readByHost(cafeId, pageParams);
    }

    /**
     * 예약 아이디로 리뷰 개수 가져오기
     * @param reservationId
     * @return 리뷰 개수
     */
    @Override
    public int getCountByReview(int reservationId) {
        return reviewMapper.readCountByReview(reservationId);
    }

    /**
     * 호스트 해당 카페의 오늘 등록된 리뷰 수 출력 (호스트 대시 보드 사용)
     * @param cafeId
     * @return 오늘 등록된 리뷰 수
     */
    @Override
    public int getTodayReview(int cafeId) {
        return reviewMapper.readTodayReview(cafeId);
    }
}
