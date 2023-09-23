package com.vj.lets.domain.support.mapper;

import com.vj.lets.domain.common.web.PageParams;
import com.vj.lets.domain.support.dto.Faq;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * FAQ 관련 매퍼 테스트 클래스
 *
 * @author VJ특공대 김종원
 * @version 1.0
 * @since 2023-09-10 (일)
 */
@SpringBootTest
@Slf4j
class FaqMapperTest {

    @Autowired
    private FaqMapper faqMapper;

    @Test
    @Transactional
    void createTest() {
        // given
        Faq faq = Faq.builder()
                .title("신규 타이틀")
                .content("신규 컨텐츠")
                .categoryId(1)
                .build();
        // when
        faqMapper.create(faq);
        // then
        log.info("등록 FAQ 정보 : {}", faq);
        assertThat(faq).isNotNull();
    }

    @Test
    void readAllTest() {
        // given
        PageParams pageParams = PageParams.builder()
                .elementSize(5)
                .pageSize(5)
                .requestPage(1)
                .build();
        // when
        List<Map<String, Object>> list = faqMapper.readAll(pageParams);
        // then
        log.info("전체 FAQ 목록 : {}", list);
        assertThat(list).isNotNull();
    }

    @Test
    void readByCategoryTest() {
        // given
        int categoryId = 1;
        // when
        List<Faq> list = faqMapper.readByCategory(1);
        // then
        log.info("전체 FAQ 카테고리 목록 : {}", list);
        assertThat(list).isNotNull();
    }

    @Test
    @Transactional
    void updateTest() {
        // given
        Faq faq = Faq.builder()
                .id(1)
                .title("업데이트 타이틀")
                .content("업데이트 컨텐츠")
                .categoryId(3)
                .build();
        // when
        faqMapper.update(faq);
        // then
        log.info("FAQ 수정 정보 : {}", faq);
        assertThat(faq).isNotNull();
    }

    @Test
    @Transactional
    void deleteTest() {
        // given
        int id = 1;
        // when
        faqMapper.delete(id);
        // then
        log.info("삭제 FAQ : {}", id);
        assertThat(id).isNotZero();
    }
}