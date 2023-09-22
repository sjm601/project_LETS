package com.vj.lets.domain.cafe.mapper;//import static org.junit.jupiter.api.Assertions.*;

import com.vj.lets.domain.cafe.dto.Cafe;
import com.vj.lets.domain.cafe.dto.CafeSearch;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Cafe Mapper Test
 *
 * @author VJ특공대 강소영
 * @version 1.0
 * @since 2023-09-12 (화)
 */
@SpringBootTest
@Slf4j
class CafeMapperTest {

    @Autowired
    private CafeMapper cafeMapper;

    @Test
    @DisplayName("카페 생성")
    @Transactional
    @Disabled
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
                .description("")
                .imagePath("/image/06홈카페")
                .businessNumber(1111111116)
                .siGunGuId(10010)
                .memberId(6)
                .build();
        //when
        cafeMapper.create(cafe);
        //then
        log.info("가입 카페 정보 : {}", cafe);
        assertThat(cafe).isNotNull();
    }

    @Test
    @DisplayName("카페 업데이트")
    @Disabled
    void updateTest() {
        //given
        Cafe cafe = Cafe.builder()
                .id(5)
                .email("homecafe@gmail.com")
//                .name("홈카페")
                .phoneNumber("010-1111-22220")
                .roadAddress("동일로 300")
                .detailAddress("401호")
                .latitude(37.12422)
                .longitude(127.112322)
                .startTime(8)
                .endTime(16)
//                .description("홈카페는 집처럼 좋습니다")
//                .imagePath("/image/06홈카페")
//                .businessNumber(1111111116)
                .siGunGuId(10010)
                .memberId(6)
                .build();
        //when
       cafeMapper.update(cafe);
        //then
        log.info("수정 카페 정보 : {}", cafe);
        assertThat(cafe).isNotNull();
    }

    @Test
    @DisplayName("id로 카페 검색")
    void findByIdTest() {
        //given
        int id = 5;
        //when
        Map<String, Object> cafe = cafeMapper.findById(id);
        //then
        log.info("카페 정보 : {}", cafe);
        assertThat(cafe).isNotNull();
    }

    @Test
    @DisplayName("카페 전체 검색")
    @Disabled
    void findByAllTest() {
        //given
        //when
//        List<Map<String, Object>> list = cafeMapper.findByAll();
        //then
//        log.info("카페 리스트 : {}", list);
//        assertThat(list).isNotNull();
    }

    @Test
    @DisplayName("예약이 많은 카페 6개 검색")
    @Disabled
    void findByBestTest() {
        //given
        //when
        List<Map<String, Object>> list = cafeMapper.findByBest();
        //then
        log.info("예악많은 카페 리스트 : {}", list);
        assertThat(list).isNotNull();
    }

    @Test
    @DisplayName("카페 검색")
    @Disabled
    void findBySearchTest() {
        //given
        //카페검색쿼리 cafe, option, room 가격, review 별점 조인하기
        CafeSearch cafeSearch = CafeSearch.builder()
                .currentX(27.222222)
                .currentY(125.222222)
                .maxDuration(10000)
                .build();
        //when
//        List<Map<String, Object>> list = cafeMapper.findBySearch(cafeSearch);
        //then
//        log.info("카페 검색 정보 : {}", list);
//        assertThat(list).isNotNull();
    }

    @Test
    @DisplayName("카페 삭제")
    @Transactional
    void deleteTest() {
        //given
        int id = 1;
        //when
        cafeMapper.delete(id);
        Map<String, Object> cafe = cafeMapper.findById(id);
        //then
        log.info("카페 삭제 정보: {}", cafe);
        assertThat(cafe).isNull();
    }
}