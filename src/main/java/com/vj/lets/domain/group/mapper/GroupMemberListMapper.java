package com.vj.lets.domain.group.mapper;

import com.vj.lets.domain.member.dto.Member;
import org.apache.ibatis.annotations.Mapper;

/**
 * 스터디 멤버 리스트 매퍼
 * 
 * @author 이희영
 * @version 1.0
 * @since 2023-09-08 (금)
 */
@Mapper
public interface GroupMemberListMapper {

    /**
     * 스터디 그룹 멤버 리스트 생성
     */
    public void create(int id);

    /**
     * 스터디 그룹 가입 신청
     * @param member
     */
    public void register(Member member);

    /**
     * 스터드 그룹 가입 신청 승인
     * @param id
     */
    public void approve(int id);

    /**
     * 스터디 그룹 가입 신청 거절
     * @param id
     */
    public  void refuse(int id);
}
