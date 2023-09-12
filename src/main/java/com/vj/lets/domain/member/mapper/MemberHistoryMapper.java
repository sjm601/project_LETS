package com.vj.lets.domain.member.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 회원 히스토리 관련 매퍼 인터페이스
 *
 * @author VJ특공대 김종원
 * @version 1.0
 * @since 2023-09-08 (금)
 */
@Mapper
public interface MemberHistoryMapper {

    /**
     * 멤버 히스토리 초기 생성
     */
    public void create();

    /**
     * 회원 정보 수정 시 히스토리 생성
     *
     * @param memberId 회원 ID
     * @param comment  변경 정보
     */
    public void createByUpdate(@Param("memberId") int memberId, @Param("comment") String comment);

}







