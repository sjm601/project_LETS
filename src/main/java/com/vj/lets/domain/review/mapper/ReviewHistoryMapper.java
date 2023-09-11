package com.vj.lets.domain.review.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 리뷰 히스토리 관련 매퍼 인터페이스
 *
 * @author VJ특공대 김종원
 * @version 1.0
 * @since 2023-09-11 (월)
 */
@Mapper
public interface ReviewHistoryMapper {

    /**
     * 리뷰 히스토리 초기 생성
     */
    public void create();

    /**
     * 리뷰 정보 수정 시 히스토리 생성
     *
     * @param reviewId 리뷰 ID
     * @param comment  변경 정보
     */
    public void createByUpdate(@Param("reviewId") int reviewId, @Param("comment") String comment);

}
