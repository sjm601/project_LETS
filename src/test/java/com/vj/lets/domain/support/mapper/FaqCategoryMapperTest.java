package com.vj.lets.domain.support.mapper;

import com.vj.lets.domain.support.dto.FaqCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * FAQ 카테고리 관련 매퍼 테스트 클래스
 *
 * @author VJ특공대 김종원
 * @version 1.0
 * @since 2023-09-10 (일)
 */
@SpringBootTest
@Slf4j
class FaqCategoryMapperTest {

    @Autowired
    private FaqCategoryMapper faqCategoryMapper;

    @Test
    @Transactional
    void createTest() {
        // given
        FaqCategory category = FaqCategory.builder()
                .name("support")
                .description("고객 지원")
                .build();
        // when
        faqCategoryMapper.create(category);
        // then
        log.info("생성한 FAQ 카테고리 정보 : {}", category);
        assertThat(category).isNotNull();
    }

    @Test
    void readAllTest() {
        // given
        // when
        List<FaqCategory> list = faqCategoryMapper.readAll();
        // then
        log.info("FAQ 카테고리 목록 : {}", list);
        assertThat(list).isNotNull();
    }

    @Test
    void readByCafeCategoryTest() {
        // given
        // when
        List<FaqCategory> list = faqCategoryMapper.readByCafeCategory();
        // then
        log.info("카페용 FAQ 카테고리 목록 : {}", list);
        assertThat(list).isNotNull();
    }

    @Test
    @Transactional
    void updateTest() {
        // given
        FaqCategory category = FaqCategory.builder()
                .id(1)
                .name("support")
                .description("고객 지원")
                .build();
        // when
        faqCategoryMapper.update(category);
        // then
        log.info("FAQ 카테고리 수정 정보 : {}", category);
        assertThat(category).isNotNull();
    }

    @Test
    @Transactional
    void deleteTest() {
        // given
        int id = 7;
        // when
        faqCategoryMapper.delete(id);
        // then
        log.info("삭제 FAQ 카테고리 : {}", id);
        assertThat(id).isNotZero();
    }
}