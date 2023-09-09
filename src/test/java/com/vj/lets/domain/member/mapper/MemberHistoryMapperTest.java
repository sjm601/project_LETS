package com.vj.lets.domain.member.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * MemberHistory 매퍼 테스트 클래스
 *
 * @author 김종원
 * @version 1.0
 * @since 2023-09-08 (금)
 */
@SpringBootTest
@Slf4j
class MemberHistoryMapperTest {

    @Autowired
    private MemberHistoryMapper memberHistoryMapper;

    @Test
    @Transactional
    void createTest() {
        // given
        // when
        memberHistoryMapper.create();
        // then
    }

    @Test
    @Transactional
    void updateTest() {
        // given
        int memberId = 1;
        // when
        memberHistoryMapper.update(memberId);
        // then
    }

    @Test
    @Transactional
    void deleteTest() {
        // given
        int memberId = 1;
        // when
        memberHistoryMapper.delete(memberId);
        // then
    }
}