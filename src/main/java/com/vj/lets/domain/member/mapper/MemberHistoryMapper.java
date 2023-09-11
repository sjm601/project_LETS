package com.vj.lets.domain.member.mapper;

import org.apache.ibatis.annotations.Mapper;

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
     * 히스토리 생성
     */
    public void create();

    /**
     * 회원 상태 update로 업데이트
     * @param memberId 정보 수정 할 회원 ID
     */
    public void update(int memberId);

    /**
     * 회원 상태 delete로 업데이트
     * @param memberId 탈퇴할 회원 ID
     */
    public void delete(int memberId);

}







