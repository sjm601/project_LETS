package com.vj.lets.domain.member.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

/**
 * 회원 히스토리 관련 매퍼 테스트 클래스
 *
 * @author VJ특공대 김종원
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
    void createByUpdateTest() {
        // given
        int memberId = 1;
        String comment = "update";
        // when
        memberHistoryMapper.createByUpdate(memberId, comment);
        // then
    }

}