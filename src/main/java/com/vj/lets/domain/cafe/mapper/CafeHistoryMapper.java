package com.vj.lets.domain.cafe.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Cafe History 테이블 관련 명세
 *
 * @author 강소영
 * @version 1.0
 * @since 2023-09-08 (금)
 */
@Mapper
public interface CafeHistoryMapper {

    /**
     * 카페 생성
     */
    public void create();

    /**
     * 카페 업데이트
     *
     * @param comment 변경 사유
     * @param cafeId  카페 ID
     */
    public void update(@Param("comment") String comment, @Param("cafeId") int cafeId);

    /**
     * 카페 삭제
     *
     * @param cafeId 카페 ID
     */
    public void delete(int cafeId);
}
