package com.vj.lets.domain.support.service;

import com.vj.lets.domain.support.dto.Faq;
import com.vj.lets.domain.support.dto.FaqCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * FAQ 관련 서비스 테스트 클래스
 *
 * @author VJ특공대 김종원
 * @version 1.0
 * @since 2023-09-10 (일)
 */
@SpringBootTest
@Slf4j
class FaqServiceTest {

    @Autowired
    private FaqService faqService;

    @Test
    @Transactional
    void registerTest() {
        // given
        Faq faq = Faq.builder()
                .title("test")
                .content("test 내용")
                .categoryId(1)
                .build();
        // when
        faqService.register(faq);
        // then
        assertThat(faq).isNotNull();
    }

    @Test
    void getFaqCategoryListTest() {
        // given
        // when
        List<FaqCategory> list = faqService.getFaqCategoryList();
        // then
        assertThat(list).isNotNull();
    }

    @Test
    void getFaqListTest() {
        // given
        // when
        Map<String, List<Faq>> map = faqService.getFaqList();
        // then
        assertThat(map).isNotNull();
    }

    @Test
    @Transactional
    void editTest() {
        // given
        Faq faq = Faq.builder()
                .id(1)
                .title("test")
                .content("test 내용")
                .categoryId(1)
                .build();
        // when
        faqService.edit(faq);
        // then
        assertThat(faq).isNotNull();
    }

    @Test
    @Transactional
    void removeTest() {
        // given
        int id = 1;
        // when
        faqService.remove(id);
        // then
        assertThat(id).isNotZero();
    }
}