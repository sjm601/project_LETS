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

    @Test
    void getReviewListByCafeTest() {
        // given
        int cafeId = 2;
        PageParams pageParams = PageParams.builder()
                .elementSize(5)
                .pageSize(5)
                .requestPage(1)
                .build();
        // when
        List<Map<String, Object>> list = reviewService.getReviewListByCafe(cafeId, pageParams);
        // then
        log.info("리뷰 목록 : {}", list);
        assertThat(list).isNotNull();
    }

    @Test
    void getCountReviewRatingByCafeTest() {
        // given
        int cafeId = 1;
        // when
        Map<Integer, Object> map = reviewService.getCountReviewRatingByCafe(cafeId);
        // then
        log.info("점수 별 개수 : {}", map);
        assertThat(map).isNotNull();
    }

    @Test
    void getCountReviewByMemberTest() {
        // given
        int memberId = 1;
        // when
        int count = reviewService.getCountReviewByMember(memberId);
        // then
        log.info("리뷰 작성 수 : {}", count);
        assertThat(count).isNotZero();
    }

    @Test
    void getReviewListByMemberTest() {
        // given
        int memberId = 27;
        // when
        List<Map<String, Object>> list = reviewService.getReviewListByMember(memberId, PageParams.builder().build());
        // then
        log.info("리뷰 목록 : {}", list);
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
    void getTodayReviewTest() {
        // given
        int cafeId = 2;
        // when
        int count = reviewService.getTodayReview(cafeId);
        // then
        log.info("오늘 등록된 리뷰 수 :{}", count);
    }

    @Test
    void getCountByHostTest() {
        // given
        int cafeId =2;
        // when
        int count = reviewService.getCountByHost(cafeId);
        // then
        log.info("호스트 카페에 대한 총 리뷰 수 (게스트만):{}", count);
        assertThat(count).isNotZero();
    }

    @Test
    void getByHostTest() {
        // given
        int cafeId =2;
        int count = reviewService.getCountByHost(cafeId);
        PageParams pageParams = PageParams.builder()
                .elementSize(2)
                .pageSize(2)
                .requestPage(1)
                .rowCount(count)
                .build();
        // when
        List<Map<String,Object>> list = reviewService.getByHost(cafeId,pageParams);
        // then
        log.info("카페 아이디로 카페 리뷰 조회 및 출력:{}",list);
        assertThat(list).isNotNull();
    }
}