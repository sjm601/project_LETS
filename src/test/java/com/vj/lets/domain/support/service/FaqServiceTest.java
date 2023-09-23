package com.vj.lets.domain.support.service;

import com.vj.lets.domain.common.web.PageParams;
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
        log.info("FAQ 등록 정보 : {}", faq);
        assertThat(faq).isNotNull();
    }

    @Test
    void getFaqCategoryListTest() {
        // given
        // when
        List<FaqCategory> list = faqService.getFaqCategoryList();
        // then
        log.info("FAQ 카테고리 목록 : {}", list);
        assertThat(list).isNotNull();
    }

    @Test
    void getCountFaqTest() {
        // given
        String type = "회원";
        // when
        int count = faqService.getCountFaq(type);
        // then
        log.info("해당 카테고리의 FAQ 수 : {}", count);
        assertThat(count).isNotZero();
    }

    @Test
    void getFaqListForAdminTest() {
        // given
        PageParams pageParams = PageParams.builder()
                .elementSize(5)
                .pageSize(5)
                .requestPage(1)
                .build();
        // when
        List<Map<String, Object>> list = faqService.getFaqListForAdmin(pageParams);
        // then
        log.info("FAQ 목록 : {}", list);
        assertThat(list).isNotNull();
    }

    @Test
    void getFaqListTest() {
        // given
        // when
        Map<String, List<Faq>> map = faqService.getFaqList();
        // then
        log.info("FAQ 카테고리 목록 : {}", map);
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