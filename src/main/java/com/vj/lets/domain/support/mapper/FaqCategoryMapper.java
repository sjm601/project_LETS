package com.vj.lets.domain.support.mapper;

import com.vj.lets.domain.support.dto.FaqCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * FAQ 카테고리 관련 매퍼 인터페이스
 *
 * @author VJ특공대 김종원
 * @version 1.0
 * @since 2023-09-10 (일)
 */
@Mapper
public interface FaqCategoryMapper {

    /**
     * FAQ 카테고리 등록
     *
     * @param category FAQ 카테고리 정보
     */
    public void create(FaqCategory category);

    /**
     * FAQ 카테고리 목록 조회
     *
     * @return FAQ 카테고리 목록
     */
    public List<FaqCategory> readAll();

    /**
     * 예약과 관련된 FAQ 카테고리 목록
     *
     * @return FAQ 카테고리 목록
     * @author VJ특공대 강소영
     */
    public List<FaqCategory> readByCafeCategory();

    /**
     * FAQ 카테고리 정보 수정
     *
     * @param category FAQ 카테고리 정보
     */
    public void update(FaqCategory category);

    /**
     * FAQ 카테고리 삭제
     *
     * @param id FAQ 카테고리 ID
     */
    public void delete(int id);


}
