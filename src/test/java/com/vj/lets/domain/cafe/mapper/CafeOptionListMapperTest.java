package com.vj.lets.domain.cafe.mapper;//import static org.junit.jupiter.api.Assertions.*;

import com.vj.lets.domain.cafe.dto.CafeOption;
import com.vj.lets.domain.cafe.dto.CafeOptionList;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Cafe Option List Test
 *
 * @author VJ특공대 강소영
 * @version 1.0
 * @since 2023-09-13 (수)
 */
@SpringBootTest
@Slf4j
class CafeOptionListMapperTest {

    @Autowired
    private CafeOptionListMapper cafeOptionListMapper;

    @Test
    @DisplayName("카페 옵션 리스트 생성")
    @Disabled
    @Transactional
    void createTest() {
        //given
        CafeOptionList cafeOptionList = CafeOptionList.builder()
                .cafeId(5)
                .optionId(10)
                .build();
        //when
        cafeOptionListMapper.create(cafeOptionList);
        //then
        log.info("카페 옵션 리스트 생성 : {}", cafeOptionList);
        assertThat(cafeOptionList).isNotNull();
    }

    @Test
    @DisplayName("전체 옵션 리스트 검색")
    @Disabled
    void readAllTest() {
        //given
        //when
        List<CafeOptionList> list = cafeOptionListMapper.readAll();
        //then
        log.info("전체 옵션 리스트 : {}", list);
        assertThat(list).isNotNull();
    }

    @Test
    @DisplayName("옵션리스트 아이디로 검색")
    @Disabled
    void findByOptionListIdTest(){
        //given
        int id = 1;
        //when
        CafeOptionList cafeOptionList = cafeOptionListMapper.findByOptionListId(id);
        //then
        log.info("옵션 아이디로 검색한 결과 : {}", cafeOptionList);
        assertThat(cafeOptionList).isNotNull();
    }

    @Test
    @DisplayName("카페아이디로 옵션 리스트 검색")
    @Disabled
    void findByOptionCafeIdTest() {
        //given
        int id = 1;
        //when
        List<CafeOption> list = cafeOptionListMapper.findByOptionCafeId(id);
        //then
        log.info("카페 아이디로 검색한 옵션 리스트 : {}", list);
        assertThat(list).isNotNull();
    }

    @Test
    @DisplayName("카페아이디로 옵션 리스트 삭제")
    @Disabled
    void delete() {
        //given
        int id = 2;
        //when
        cafeOptionListMapper.delete(id);
        //then
    }
}