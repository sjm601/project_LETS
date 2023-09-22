package com.vj.lets.domain.review.service;

import com.vj.lets.domain.common.web.PageParams;
import com.vj.lets.domain.review.dto.Review;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 리뷰 관련 서비스 테스트 클래스
 *
 * @author VJ특공대 김종원
 * @version 1.0
 * @since 2023-09-11 (월)
 */
@SpringBootTest
@Slf4j
class ReviewServiceTest {

    @Autowired
    private ReviewService reviewService;

    @Test
    @Transactional
    void registerTest() {
        // given
        Review review = Review.builder()
                .content("테스트 내용")
                .rating(5)
                .reservationId(1)
                .memberId(1)
                .build();
        // when
        reviewService.register(review);
        // then
        assertThat(review).isNotNull();
    }

    @Test
    void getReviewListTest() {
        // given
        // when
        List<Map<String, Object>> list = reviewService.getReviewList();
        // then
        assertThat(list).isNotNull();
    }

    /**
     * @Test void getReviewListByCafeTest() {
     * // given
     * int cafeId = 2;
     * // when
     * List<Map<String, Object>> list = reviewService.getReviewListByCafe(cafeId);
     * // then
     * log.info("====================={}", list);
     * assertThat(list).isNotNull();
     * }
     **/
    @Test
    void getReviewListByMemberTest() {
        // given
        int memberId = 27;
        // when
        List<Map<String, Object>> list = reviewService.getReviewListByMember(memberId, PageParams.builder().build());
        // then
        log.info("====================={}", list);
        assertThat(list).isNotNull();
    }

    @Test
    @Transactional
    void editReviewTest() {
        // given
        Review review = Review.builder()
                .id(1)
                .content("수정 테스트")
                .rating(3)
                .build();
        // when
        reviewService.editReview(review);
        // then
        assertThat(review).isNotNull();
    }

    @Test
    @Transactional
    void removeReviewTest() {
        // given
        int id = 1;
        // when
        reviewService.removeReview(id);
        // then
        assertThat(id).isNotZero();
    }

    @Test
    void getCountByReviewTest() {
        // given
        int resId = 2;
        // when
        int count = reviewService.getCountByReview(resId);
        // then
        log.info("예약 아이디에 대한 리뷰 갯수 :{}", count);
    }

    @Test
    void getTodayReviewTest() {
        // given
        int cafeId = 2;
        // when
        int count = reviewService.getTodayReview(cafeId);
        // then
        log.info("오늘 등록된 리뷰 수 :{}", count);
    }

    @Test
    void callReviewCommentTest() {
        // given
        int resId = 8;
        int memberId = 7;
        // when
        String review = reviewService.callReviewComment(resId, memberId);
        // then
        log.info("리뷰 답변:{}", review);
        assertThat(review).isNotNull();
    }
}