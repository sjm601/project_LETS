package com.vj.lets.domain.support.service;

import com.vj.lets.domain.support.dto.Faq;
import com.vj.lets.domain.support.dto.FaqCategory;
import com.vj.lets.domain.support.mapper.FaqCategoryMapper;
import com.vj.lets.domain.support.mapper.FaqMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * FAQ 관련 비즈니스 로직 처리 및 트랜잭션 관리 서비스 구현체
 *
 * @author VJ특공대 김종원
 * @version 1.0
 * @since 2023-09-10 (일)
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class FaqServiceImpl implements FaqService {

    private final FaqMapper faqMapper;
    private final FaqCategoryMapper faqCategoryMapper;

    /**
     * FAQ 등록
     *
     * @param faq FAQ 정보
     */
    @Override
    public void register(Faq faq) {
        faqMapper.create(faq);
    }

    /**
     * 전체 FAQ 카테고리 목록 조회
     *
     * @return FAQ 카테고리 목록
     */
    @Override
    public List<FaqCategory> getFaqCategoryList() {
        return faqCategoryMapper.readAll();
    }

    /**
     * 카테고리 별로 정렬하여 FAQ 목록 조회
     *
     * @return FAQ 목록
     */
    @Override
    public Map<String, List<Faq>> getFaqList() {
        Map<String, List<Faq>> faqListMap = null;

        faqListMap = new LinkedHashMap<>();
        List<FaqCategory> categoryList = faqCategoryMapper.readAll();
        for (FaqCategory category : categoryList) {
            List<Faq> faqList = faqMapper.readByCategory(category.getId());
            faqListMap.put(category.getName(), faqList);
        }

        return faqListMap;
    }

    /**
     * FAQ 수정
     *
     * @param faq FAQ 정보
     */
    @Override
    public void edit(Faq faq) {
        faqMapper.update(faq);
    }

    /**
     * FAQ 삭제
     *
     * @param id FAQ ID
     */
    @Override
    public void remove(int id) {
        faqMapper.delete(id);
    }
}