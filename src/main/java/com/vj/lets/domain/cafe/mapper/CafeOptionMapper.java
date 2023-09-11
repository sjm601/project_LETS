package com.vj.lets.domain.cafe.mapper;

import com.vj.lets.domain.cafe.dto.CafeOption;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * CafeOption 테이블 관련 명세
 *
 * @author VJ특공대 강소영
 * @version 1.0
 * @since 2023-09-11 (월)
 */
@Mapper
public interface CafeOptionMapper {

    /**
     * 카페 옵션 생성
     *
     * @param cafeOption 카페옵션 정보
     */
    public void create(CafeOption cafeOption);

    /**
     * 카페 옵션 수정
     *
     * @param cafeOption 카페 옵션 정보
     */
    public void update(CafeOption cafeOption);

    /**
     * 전체 카페 옴션 목록 조회
     *
     * @return 카페 옵션 목록
     */
    public List<CafeOption> readAll();

    /**
     * 카페 옵션 삭제
     *
     * @param id 카페 옵션 ID
     */
    public void delete(int id);
}
