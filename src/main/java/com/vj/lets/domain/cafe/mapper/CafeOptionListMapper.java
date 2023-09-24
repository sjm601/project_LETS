package com.vj.lets.domain.cafe.mapper;

import com.vj.lets.domain.cafe.dto.CafeOption;
import com.vj.lets.domain.cafe.dto.CafeOptionList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Cafe Option List 관련 매퍼 인터페이스
 *
 * @author VJ특공대 강소영
 * @version 1.0
 * @since 2023-09-11 (월)
 */
@Mapper
public interface CafeOptionListMapper {

    /**
     * 카페 옵션 목록 생성
     *
     * @param cafeOptionList 카페 옵션 목록 정보
     */
    public void create(CafeOptionList cafeOptionList);

    /**
     * 카페 옵션 목록 전체 목록 조회
     *
     * @return 카페 옵션 목록 목록
     */
    public List<CafeOptionList> readAll();

    /**
     * 카페 옵션 목록 ID로 검색
     *
     * @param listId 옵션 목록 ID
     * @return 카페 옵션 목록
     */
    public CafeOptionList findByOptionListId(int listId);

    /**
     * 카페 ID로 옵션 목록 조회
     *
     * @param cafeId 카페 ID
     * @return 카페 옵션 목록 목록
     */
    public List<CafeOption> findByOptionCafeId(int cafeId);

    /**
     * 카페 ID로 옵션 체크 여부
     *
     * @param cafeId   카페 ID
     * @param optionId 옵션 ID
     * @return 체크 여부
     */
    public boolean findByOptionCheckCafeId(@Param("cafeId") int cafeId, @Param("optionId") int optionId);

    /**
     * 카페 옵션 목록 삭제
     *
     * @param cafeId 카페 ID
     */
    public void delete(int cafeId);
}
