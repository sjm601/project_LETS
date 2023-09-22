package com.vj.lets.domain.review.mapper;

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
 * 리뷰 관련 매퍼 테스트 클래스
 *
 * @author VJ특공대 김종원
 * @version 1.0
 * @since 2023-09-11 (월)
 */
@SpringBootTest
@Slf4j
class ReviewMapperTest {

    @Autowired
    private ReviewMapper reviewMapper;

    @Test
    @Transactional
    void createTest() {
        // given
        Review review = Review.builder()
                .content("테스트 내용")
                .rating(5)
                .reservationId(1)
                .memberId(1)
                .build();
        // when
        reviewMapper.create(review);
        // then
        assertThat(review).isNotNull();
    }

    @Test
    void readAllTest() {
        // given
        // when
        List<Map<String, Object>> list = reviewMapper.readAll();
        // then
        assertThat(list).isNotNull();
    }

    /**
    @Test
    void readByCafeTest() {
        // given
        int cafeId = 1;
        PageParams pageParams = PageParams.builder()
                .elementSize(5)
                .pageSize(5)
                .requestPage(1)
                .build();
        // when
        List<Map<String, Object>> list = reviewMapper.readByCafe(cafeId, pageParams);
        // then
        assertThat(list).isNotNull();
    }
**/
    @Test
    void readByMemberTest() {
        // given
        int memberId = 1;
        // when
        List<Map<String, Object>> list = reviewMapper.readByMember(memberId, PageParams.builder().build());
        // then
        assertThat(list).isNotNull();
    }

    @Test
    void readHostCommentByResIdTest() {
        // given
        int reservationId = 1;
        // when
        Map<String, String> map = reviewMapper.readHostCommentByResId(reservationId);
        // then
        assertThat(map).isNotNull();
    }

    @Test
    @Transactional
    void updateTest() {
        // given
        Review review = Review.builder()
                .id(1)
                .content("수정 테스트")
                .rating(3)
                .build();
        // when
        reviewMapper.update(review);
        // then
        assertThat(review).isNotNull();
    }

    @Test
    @Transactional
    void disabledTest() {
        // given
        int id = 1;
        // when
        reviewMapper.disabled(id);
        // then
        assertThat(id).isNotZero();
    }

    @Test
    void readCountByReviewTest() {
        // given
        int resId =2;
        // when
        int count = reviewMapper.readCountByReview(resId);
        // then
        log.info("예약 아이디에 대한 리뷰 갯수 :{}",count);
        assertThat(count).isNotZero();
    }

    @Test
    void readTodayReviewTest() {
        // given
        int cafeId=2;
        // when
        int count = reviewMapper.readTodayReview(cafeId);
        // then
        log.info("오늘 등록된 리뷰 수 :{}",count);
    }

    @Test
    void callReviewCommentTest() {
        // given
        int resId = 8;
        int memberId =7;
        // when
        String  review = reviewMapper.callReviewComment(resId,memberId);
        // then
        log.info("리뷰 답변:{}", review);
        assertThat(review).isNotNull();
    }
}