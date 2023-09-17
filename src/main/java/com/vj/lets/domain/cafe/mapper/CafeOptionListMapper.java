package com.vj.lets.domain.cafe.mapper;

import com.vj.lets.domain.cafe.dto.CafeOption;
import com.vj.lets.domain.cafe.dto.CafeOptionList;
import org.apache.ibatis.annotations.Mapper;

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
     * 카페 옵션리스트 생성
     *
     * @param cafeOptionList 카페옵션리스트 정보
     */
    public void create(CafeOptionList cafeOptionList);

    /**
     * 카페 옵션리스트 전체 목록 조회
     *
     * @return 카페 옵션리스트 목록
     */
    public List<CafeOptionList> readAll();

    /**
     * 카페 옵션리스트 아이디로 검색
     *
     * @param listId
     * @return 카페 옵션 리스트
     */
    public CafeOptionList findByOptionListId(int listId);

    /**
     * 카페 id로 옵션리스트 조회
     *
     * @param cafeId 카페 아이디
     * @return 카페 옵션리스트 목록
     */
    public List<CafeOption> findByOptionCafeId(int cafeId);

    /**
     * 카페 옵션리스트 삭제
     *
     * @param cafeId 카페 ID
     */
    public void delete(int cafeId);
}
