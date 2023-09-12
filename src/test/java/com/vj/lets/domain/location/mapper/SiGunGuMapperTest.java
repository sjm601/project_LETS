package com.vj.lets.domain.location.mapper;//import static org.junit.jupiter.api.Assertions.*;
import com.vj.lets.domain.location.dto.SiGunGu;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class SiGunGuMapperTest {

    @Autowired
    private SiGunGuMapper siGunGuMapper;

    @Test
    @Transactional
    @Disabled
    void getSiGunGuTest() {
        // given
        String siGunGuName = "노원구";

        // when
        SiGunGu siGunGu = siGunGuMapper.getSiGunGu(siGunGuName);

        // then
        log.info("시,군,구 id : {}", siGunGu.getId());
        assertThat(siGunGu.getId()).isNotNull();
    }
}