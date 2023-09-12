package com.vj.lets.domain.review.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class ReviewHistoryMapperTest {

    @Autowired
    private ReviewHistoryMapper reviewHistoryMapper;

    @Test
    @Transactional
    void createTest() {
        // given
        // when
        reviewHistoryMapper.create();
        // then
    }

    @Test
    @Transactional
    void createByUpdateTest() {
        // given
        int reviewId = 1;
        String comment = "update";
        // when
        reviewHistoryMapper.createByUpdate(reviewId, comment);
        // then
    }
}