package com.vj.lets.domain.group.mapper;

import com.vj.lets.domain.group.dto.GroupMemberList;
import com.vj.lets.domain.member.dto.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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
     * @param id 회원 아이디
     */
    public void create(int id);

    /**
     * 스터디 그룹 멤버 리스트 조회
     * @param studyGroupId 스터디 그룹 아이디
     * @return 멤버 리스트
     */
    public List<GroupMemberList> findByAll(int studyGroupId);

    /**
     * 스터디 그룹 회원 추가
     * @param id 회원 아이디
     */
    public void addMember(@Param("id") int id, @Param("studyGroupId") int studyGroupId);

    /**
     * 스터디 그룹 멤버 탈퇴
     * @param id 회원 아이디
     */
    public  void removeMember(@Param("id") int id, @Param("studyGroupId") int studyGroupId);

    /**
     * 스터디 회원이 맞는지 조회
     * @param memberId 회원 아이디
     * @param studyGroupId 스터디 그룹 아이디
     * @return 스터디 그룹 멤버 리스트 정보
     */
    public GroupMemberList isMember(@Param("memberId") int memberId, @Param("studyGroupId") int studyGroupId);

    /**
     * 가입한 스터디 목록 조회
     * @param memberId 회원 아이디
     * @return 그룹 리스트
     */
    public List<Map<String, Object>> myGroupList(int memberId);
}