package com.vj.lets.domain.location.service;//import static org.junit.jupiter.api.Assertions.*;

import com.vj.lets.domain.location.dto.SiGunGu;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 시,군,구 서비스 테스트
 * @author VJ특공대 이희영
 * @version 1.0
 * @since 2023-09-13 (수)
 */
@SpringBootTest
@Slf4j
class SiGunGuServiceTest {

    @Autowired
    private SiGunGuService siGunGuService;

    @Test
    @Transactional
    @Disabled
    void findByIdTest() {
        // given
        String siGunGuName = "제주시";

        // when
        SiGunGu siGunGu = siGunGuService.findById(siGunGuName);

        // then
        log.info("시,군,구 id : {}", siGunGu.getId());
        assertThat(siGunGu.getId()).isNotNull();
    }
}