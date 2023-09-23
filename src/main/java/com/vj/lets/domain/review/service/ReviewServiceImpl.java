package com.vj.lets.domain.review.service;

import com.vj.lets.domain.common.web.PageParams;
import com.vj.lets.domain.review.dto.Review;
import com.vj.lets.domain.review.mapper.ReviewHistoryMapper;
import com.vj.lets.domain.review.mapper.ReviewMapper;
import com.vj.lets.domain.review.util.ReviewHistoryComment;
import lombok.RequiredArgsConstructor;
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
public class ReviewServiceImpl implements ReviewService {

    private final ReviewMapper reviewMapper;
    private final ReviewHistoryMapper reviewHistoryMapper;

    /**
     * 리뷰 신규 등록
     *
     * @param review 등록 리뷰 정보
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
     * @return 전체 리뷰 목록
     */
    @Override
    public List<Map<String, Object>> getReviewList() {
        return reviewMapper.readAll();
    }

    /**
     * 카페에 등록된 전체 리뷰 목록 조회
     *
     * @param cafeId 카페 ID
     * @return 등록된 리뷰 목록
     * @see com.vj.lets.web.cafe.controller.CafeController
     */
    @Override
    public List<Map<String, Object>> getReviewListByCafe(int cafeId, PageParams pageParams) {
        List<Map<String, Object>> guestReviewList = reviewMapper.readByCafe(cafeId, pageParams);
        return addMaps(guestReviewList);
    }

    /**
     * 카페에 등록한 리뷰 점수별 개수 조회
     *
     * @param cafeId 카페 ID
     * @return 평점별 리뷰 개수
     * @see com.vj.lets.web.cafe.controller.CafeController
     */
    @Override
    public Map<Integer, Object> getCountReviewRatingByCafe(int cafeId) {
        return reviewMapper.countByReviewRating(cafeId);
    }

    /**
     * 특정 회원이 작성한 리뷰 갯수 조회
     *
     * @param memberId 회원 ID
     * @return 작성한 리뷰 갯수
     * @see com.vj.lets.web.dashboard.controller.MypageController
     */
    @Override
    public int getCountReviewByMember(int memberId) {
        return reviewMapper.readCountByMember(memberId);
    }

    /**
     * 특정 회원이 작성한 전체 리뷰 목록 조회
     *
     * @param memberId 회원 ID
     * @return 작성한 리뷰 목록
     * @see com.vj.lets.web.dashboard.controller.MypageController
     */
    @Override
    public List<Map<String, Object>> getReviewListByMember(int memberId, PageParams pageParams) {
        List<Map<String, Object>> guestReviewList = reviewMapper.readByMember(memberId, pageParams);
        return addMaps(guestReviewList);
    }

    /**
     * 리뷰 목록에 호스트 댓글 추가용 헬퍼 메소드
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
     * 호스트의 카페에 대한 리뷰 수 출력
     *
     * @param cafeId 카페 ID
     * @return 리뷰 갯수
     * @see com.vj.lets.web.dashboard.controller.HostController
     */
    @Override
    public int getCountByHost(int cafeId) {
        return reviewMapper.readCountByHost(cafeId);
    }

    /**
     * 카페 ID로 호스트의 카페 리뷰 목록 조회
     *
     * @param cafeId     카페 ID
     * @param pageParams 페이징 객체
     * @return 리뷰 목록
     * @see com.vj.lets.web.dashboard.controller.HostController
     */
    @Override
    public List<Map<String, Object>> getByHost(int cafeId, PageParams pageParams) {
        List<Map<String, Object>> guestReviewList = reviewMapper.readByHost(cafeId, pageParams);
        return addMaps(guestReviewList);
    }

    /**
     * 오늘 호스트의 카페에 등록된 리뷰를 불러오기 위해 사용
     *
     * @param cafeId 카페 ID
     * @return 오늘 호스트 카페에 등록된 리뷰 수
     * @see com.vj.lets.web.dashboard.controller.HostController
     */
    @Override
    public int getTodayReview(int cafeId) {
        return reviewMapper.readTodayReview(cafeId);
    }

    /**
     * 리뷰 수정
     *
     * @param review 수정 리뷰 정보
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
     * @param id 삭제할 리뷰 ID
     * @see com.vj.lets.web.dashboard.controller.MypageController
     */
    @Override
    @Transactional
    public void removeReview(int id) {
        reviewMapper.disabled(id);
        reviewHistoryMapper.createByUpdate(id, ReviewHistoryComment.DELETE.getComment());
    }

}
