package com.vj.lets.domain.cafe.mapper;//import static org.junit.jupiter.api.Assertions.*;

import com.vj.lets.domain.cafe.dto.Cafe;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Cafe History Mapper Test
 *
 * @author VJ특공대 강소영
 * @version 1.0
 * @since 2023-09-12 (화)
 */
@SpringBootTest
@Slf4j
class CafeHistoryMapperTest {

    @Autowired
    private CafeHistoryMapper cafeHistoryMapper;

    @Autowired
    private CafeMapper cafeMapper;

    @Test
    @DisplayName("카페히스토리 생성")
    @Disabled
    @Transactional
    void createTest() {
        //given
        Cafe cafe = Cafe.builder()
                .email("homecafe@gmail.com")
                .name("홈카페")
                .phoneNumber("010-1111-22220")
                .roadAddress("동일로 300")
                .detailAddress("401호")
                .latitude(37.12422)
                .longitude(127.112322)
                .startTime(10)
                .endTime(24)
                .description("홈카페는 집처럼 좋습니다")
                .imagePath("/image/06홈카페")
                .businessNumber(1111111116)
                .siGunGuId(10010)
                .memberId(6)
                .build();
        //when
        cafeMapper.create(cafe);
        log.info("가입 카페 정보 : {}", cafe);
        assertThat(cafe).isNotNull();
        //가입완료 확인
        cafeHistoryMapper.create();
        //then
    }

    @Test
    @DisplayName("카페 히스토리 업데이트")
    @Disabled
    @Transactional
    void updateTest() {
        //given
        String comment = "update";
        int cafeId = 1;
        //when
        cafeHistoryMapper.update(comment, cafeId);
        //then
    }

    @Test
    @DisplayName("카페 히스토리 삭제")
    @Disabled
    @Transactional
    void deleteTest() {
        //given
        int id = 1;
        //when
        cafeHistoryMapper.delete(id);
        //then
    }
}