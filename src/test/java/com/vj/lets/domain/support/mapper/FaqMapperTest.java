package com.vj.lets.domain.support.mapper;

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
        assertThat(faq).isNotNull();
    }

    @Test
    void readAllTest() {
        // given
        // when
        List<Map<String, Object>> list = faqMapper.readAll();
        // then
        assertThat(list).isNotNull();
    }

    @Test
    void readByCategoryTest() {
        // given
        int categoryId = 1;
        // when
        List<Faq> list = faqMapper.readByCategory(1);
        // then
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
        assertThat(id).isNotZero();
    }
}