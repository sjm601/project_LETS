package com.vj.lets.domain.support.service;

import com.vj.lets.domain.common.web.PageParams;
import com.vj.lets.domain.support.dto.Faq;
import com.vj.lets.domain.support.dto.FaqCategory;
import com.vj.lets.domain.support.mapper.FaqCategoryMapper;
import com.vj.lets.domain.support.mapper.FaqMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * FAQ 관련 비즈니스 로직 처리 및 트랜잭션 관리 서비스 구현체
 *
 * @author VJ특공대 김종원
 * @version 1.0
 * @since 2023-09-10 (일)
 */
@RequiredArgsConstructor
@Service
public class FaqServiceImpl implements FaqService {

    private final FaqMapper faqMapper;
    private final FaqCategoryMapper faqCategoryMapper;

    /**
     * FAQ 등록
     *
     * @param faq 등록한 FAQ 정보
     */
    @Override
    @Transactional
    public void register(Faq faq) {
        faqMapper.create(faq);
    }

    /**
     * 전체 FAQ 카테고리 목록 조회
     *
     * @return 전체 FAQ 카테고리 목록
     */
    @Override
    public List<FaqCategory> getFaqCategoryList() {
        return faqCategoryMapper.readAll();
    }

    /**
     * 전체 FAQ 수 조회
     *
     * @param type FAQ 타입
     * @return 해당 타입 FAQ 갯수
     */
    @Override
    public int getCountFaq(String type) {
        return faqMapper.readCountAll(type);
    }

    /**
     * 전체 FAQ 목록 조회 (관리자용)
     *
     * @param pageParams 페이징 정보
     * @return 전체 FAQ 목록
     */
    @Override
    public List<Map<String, Object>> getFaqListForAdmin(PageParams pageParams) {
        return faqMapper.readAll(pageParams);
    }

    /**
     * 카테고리 별로 정렬하여 전체 FAQ 목록 조회
     *
     * @return 정렬한 FAQ 목록
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
     * 카페와 관련된 FAQ 카테고리 조회
     *
     * @return FAQ 목록
     * @author VJ특공대 강소영
     * @see com.vj.lets.web.cafe.controller.CafeController
     */
    @Override
    public List<FaqCategory> getCafeFaqList() {
        return faqCategoryMapper.readByCafeCategory();
    }

    /**
     * FAQ 수정
     *
     * @param faq 수정 FAQ 정보
     */
    @Override
    @Transactional
    public void edit(Faq faq) {
        faqMapper.update(faq);
    }

    /**
     * FAQ 삭제
     *
     * @param id 삭제할 FAQ ID
     */
    @Override
    @Transactional
    public void remove(int id) {
        faqMapper.delete(id);
    }
}