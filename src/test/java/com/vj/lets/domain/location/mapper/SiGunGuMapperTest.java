package com.vj.lets.domain.location.mapper;//import static org.junit.jupiter.api.Assertions.*;

import com.vj.lets.domain.location.dto.SiGunGu;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

/**
 * 시,군,구 매퍼 테스트
 *
 * @author VJ특공대 이희영
 * @version 1.0
 * @since 2023-09-11 (월)
 */
@SpringBootTest
@Slf4j
class SiGunGuMapperTest {

    @Autowired
    private SiGunGuMapper siGunGuMapper;

    @Test
    @Transactional
    void getSiGunGuTest() {
        // given
        String siGunGuName = "노원구";

        // when
        SiGunGu siGunGu = siGunGuMapper.getSiGunGu(siGunGuName);

        // then
        log.info("시,군,구 id : {}", siGunGu.getId());
    }

    @Test
    void getSiGunGuDoTest(){
        //given
        String siGunGuName = "중구";
        String siDoName = "서울특별시";
        //when
        int siGunGuId=siGunGuMapper.getSiGunGuDo(siGunGuName, siDoName);
        //then
        log.info("시, 군, 구 id :{}", siGunGuId);
    }
}