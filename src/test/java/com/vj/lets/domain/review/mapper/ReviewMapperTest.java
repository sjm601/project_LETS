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
        log.info("생성한 리뷰 정보 : {}", review);
        assertThat(review).isNotNull();
    }

    @Test
    void readAllTest() {
        // given
        // when
        List<Map<String, Object>> list = reviewMapper.readAll();
        // then
        log.info("리뷰 목록 : {}", list);
        assertThat(list).isNotNull();
    }


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
        log.info("리뷰 목록 : {}", list);
        assertThat(list).isNotNull();
    }

    @Test
    void countByReviewRating() {
        // given
        int cafeId = 1;
        // when
        Map<Integer, Object> map = reviewMapper.countByReviewRating(cafeId);
        // then
        log.info("점수 별 개수 : {}", map);
        assertThat(map).isNotNull();
    }

    @Test
    void readCountByMember() {
        // given
        int memberId = 1;
        // when
        int count = reviewMapper.readCountByMember(memberId);
        // then
        log.info("리뷰 작성 수 : {}", count);
        assertThat(count).isNotZero();
    }

    @Test
    void readByMemberTest() {
        // given
        int memberId = 1;
        // when
        List<Map<String, Object>> list = reviewMapper.readByMember(memberId, PageParams.builder().build());
        // then
        log.info("리뷰 목록 : {}", list);
        assertThat(list).isNotNull();
    }

    @Test
    void readHostCommentByResIdTest() {
        // given
        int reservationId = 1;
        // when
        Map<String, String> map = reviewMapper.readHostCommentByResId(reservationId);
        // then
        log.info("댓글 정보 : {}", map);
        assertThat(map).isNotNull();
    }

    @Test
    void readCountByResIdTest() {
        // given
        int reservationId = 1;
        // when
        boolean tf = reviewMapper.readCountByResId(reservationId);
        // then
        log.info("리뷰 유무 : {}", tf);
        assertThat(tf).isTrue();
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
        log.info("수정 정보 : {}", review);
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
        log.info("삭제 리뷰 : {}", id);
        assertThat(id).isNotZero();
    }

    @Test
    void readTodayReviewTest() {
        // given
        int cafeId = 2;
        // when
        int count = reviewMapper.readTodayReview(cafeId);
        // then
        log.info("오늘 등록된 리뷰 수 :{}", count);
        assertThat(count).isNotZero();
    }


    @Test
    void readCountByHostTest() {
        // given
        int cafeId = 2;
        // when
        int count = reviewMapper.readCountByHost(cafeId);
        // then
        log.info("호스트 카페에 대한 총 리뷰 수 (게스트만):{}", count);
        assertThat(count).isNotZero();
    }

    @Test
    void readByHostTest() {
        // given
        int cafeId = 2;
        int count = reviewMapper.readCountByHost(cafeId);
        PageParams pageParams = PageParams.builder()
                .elementSize(2)
                .pageSize(2)
                .requestPage(1)
                .rowCount(count)
                .build();
        // when
        List<Map<String, Object>> list = reviewMapper.readByHost(cafeId, pageParams);
        // then
        log.info("카페 아이디로 카페 리뷰 조회 및 출력:{}", list);
        assertThat(list).isNotNull();
    }
}