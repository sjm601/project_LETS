package com.vj.lets.domain.cafe.mapper;//import static org.junit.jupiter.api.Assertions.*;

import com.vj.lets.domain.cafe.dto.CafeOption;
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
 * Cafe Option Test
 *
 * @author VJ특공대 강소영
 * @version 1.0
 * @since 2023-09-12 (화)
 */
@SpringBootTest
@Slf4j
class CafeOptionMapperTest {

    @Autowired
    private CafeOptionMapper cafeOptionMapper;

    @Test
    @DisplayName("카페옵션 생성")
    @Disabled
    @Transactional
    void createTest() {
        //given
        CafeOption cafeOption = CafeOption.builder()
                .name("백색소음기")
                .imagePath("fa-solid fa-sound")
//                .description("소리가 남")
                .build();
        //when
        cafeOptionMapper.create(cafeOption);
        //then
        log.info("카페 옵션 생성 : {}", cafeOption);
        assertThat(cafeOption).isNotNull();
    }

    @Test
    @DisplayName("카페 옵션 업데이트")
    @Disabled
    @Transactional
    void updateTest() {
        //given
        CafeOption cafeOption = CafeOption.builder()
                .name("백색소음기")
                .imagePath("fa-solid fa-sound")
                .id(18)
                .build();
        //when
        cafeOptionMapper.update(cafeOption);
        //then
    }

    @Test
    @DisplayName("카페 옵션 전체리스트 출력")
    @Disabled
    void readAllTest() {
        //given
        //when
        List<CafeOption> list = cafeOptionMapper.readAll();
        //then
        log.info("카페 옵션 전체 리스트 : {}", list);
        assertThat(list).isNotNull();
    }

    @Test
    @DisplayName("옵션 아이디로 검색")
    @Disabled
    void findByOptionIdTest(){
        //given
        int id = 1;
        //when
        CafeOption cafeOption= cafeOptionMapper.findByOptionId(id);
        //then
        log.info("아이디로 검색된 카페 옵션 : {}", cafeOption);
        assertThat(cafeOption).isNotNull();
    }
    @Test
    @DisplayName("아이디로 카페 옵션 삭제")
    @Disabled
    @Transactional
    void deleteTest() {
        //given
        int id = 18;
        //when
        cafeOptionMapper.delete(id);
        //then
    }
}