package com.vj.lets.domain.cafe.service;//import static org.junit.jupiter.api.Assertions.*;

import com.vj.lets.domain.cafe.dto.Cafe;
import com.vj.lets.domain.cafe.dto.CafeOption;
import com.vj.lets.domain.cafe.dto.CafeOptionList;
import com.vj.lets.domain.cafe.dto.CafeSearch;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Cafe Service Test
 *
 * @author VJ특공대 강소영
 * @version 1.0
 * @since 2023-09-13 (수)
 */
@SpringBootTest
@Slf4j
class CafeServiceTest {

    @Autowired
    private CafeService cafeService;

    @Test
    @DisplayName("카페옵션 등록")
    @Disabled
    @Transactional
    void cafeOptionRegisterTest() {
        //given
        List<CafeOptionList> cafeOptionLists = new ArrayList<>();
        CafeOptionList cafeOptionList1 = CafeOptionList.builder()
                .cafeId(5)
                .optionId(1)
                .build();
        CafeOptionList cafeOptionList2 = CafeOptionList.builder()
                .cafeId(5)
                .optionId(2)
                .build();
        cafeOptionLists.add(cafeOptionList1);
        cafeOptionLists.add(cafeOptionList2);
        log.info("카페 옵션 리스트 객체 : {}", cafeOptionLists);
        //when
        cafeService.cafeOptionRegister(cafeOptionLists);
        //then
    }

    @Test
    @DisplayName("카페 옵션 등록")
    @Disabled
    @Transactional
    void optionRegisterTest() {
        //given
        CafeOption cafeOption = CafeOption.builder()
                .name("옵션테스트")
                .imagePath("fa-sollid fa-test")
                .description("")
                .build();
        //when
        cafeService.optionRegister(cafeOption);
        //then
        log.info("등록된 카페 옵션 : {}", cafeOption);
        assertThat(cafeOption).isNotNull();
    }

    @Test
    @DisplayName("카페 옵션 삭제")
    @Disabled
    @Transactional
    void optionDeleteTest() {
        //given
        int id = 17;
        //when
        cafeService.optionDelete(id);
        //then
    }

    @Test
    @DisplayName("카페 리스트 전체 검색")
    @Disabled
    void getCafeListTest() {
        //given
        //when
        List<Map<String, Object>> cafes = cafeService.getCafeList();
        //then
        log.info("전체 카페 리스트 :{}", cafes);
        assertThat(cafes).isNotNull();
    }

    @Test
    @DisplayName("카페 아이디로 카페 검색")
    @Disabled
    void getCafeTest() {
        //given
        int id = 2;
        //when
        Map<String, Object> cafe = cafeService.getCafe(id);
        //then
        log.info("카페 id로 검색 결과 : {}", cafe);
        assertThat(cafe).isNotNull();
    }

    @Test
    @DisplayName("예약이 많은 카페 리스트 검색")
    @Disabled
    void getBestCafeTest() {
        //given
        //when
        List<Map<String, Object>> list = cafeService.getBestCafe();
        //then
        log.info("예약 많은 카페 번호 리스트 : {}", list);
        assertThat(list).isNotNull();
    }

    @Test
    @DisplayName("카페 검색")
    @Disabled
    void getSearchCafeTest() {
        //given
        CafeSearch cafeSearch = CafeSearch.builder()
                .name("카페")
                .currentY(127.5555)
                .currentX(27.22222)
                .minDuration(0)
                .maxDuration(700)
                .build();
        //when
        List<Map<String, Object>> list = cafeService.getSearchCafe(cafeSearch);
        //then
        log.info("카페 검색 결과 리스트 : {}", list);
//        assertThat(list).isNotNull();
    }

    @Test
    @DisplayName("카페 정보 수정")
    @Disabled
    @Transactional
    void editCafeTest() {
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
        log.info("업데이트 할 카페 정보 : {}", cafe);
        String comment = "test";
        log.info("업데이트 사유 : {}", comment);
        List<Integer> optionLists = new ArrayList<>();
        optionLists.add(1);
        optionLists.add(2);
        optionLists.add(3);
        log.info("카페 옵션 리스트 : {}", optionLists);
        //when
        cafeService.editCafe(5,cafe, comment, optionLists);
        //then
    }

    @Test
    @DisplayName("카페 삭제")
    @Disabled
    @Transactional
    void deleteCafeTest() {
        //given
        int id = 5;
        //when
        cafeService.deleteCafe(id);
        //then
    }


}