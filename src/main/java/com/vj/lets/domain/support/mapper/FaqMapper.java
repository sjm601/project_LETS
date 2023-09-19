package com.vj.lets.domain.support.mapper;

import com.vj.lets.domain.common.web.PageParams;
import com.vj.lets.domain.support.dto.Faq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * FAQ 관련 매퍼 인터페이스
 *
 * @author VJ특공대 김종원
 * @version 1.0
 * @since 2023-09-10 (일)
 */
@Mapper
public interface FaqMapper {

    /**
     * FAQ 생성
     *
     * @param faq FAQ 정보
     */
    public void create(Faq faq);

    /**
     * 전체 FAQ 수 조회
     *
     * @return 전체 FAQ 갯수
     */
    public int readCountAll(String type);

    /**
     * 전체 FAQ 목록 조회
     *
     * @return FAQ 목록
     */
    public List<Map<String, Object>> readAll(PageParams pageParams);

    /**
     * 카테고리 별 FAQ 목록 조회
     *
     * @param categoryId 카테고리 ID
     * @return FAQ 목록
     */
    public List<Faq> readByCategory(int categoryId);

    /**
     * FAQ 수정
     *
     * @param faq FAQ 정보
     */
    public void update(Faq faq);

    /**
     * FAQ 삭제
     *
     * @param id FAQ ID
     */
    public void delete(int id);

}
