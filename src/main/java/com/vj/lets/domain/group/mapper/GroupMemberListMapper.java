package com.vj.lets.domain.group.mapper;

import com.vj.lets.domain.group.dto.GroupMemberList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 스터디 멤버 리스트 매퍼
 *
 * @author VJ특공대 이희영
 * @version 1.0
 * @since 2023-09-08 (금)
 */
@Mapper
public interface GroupMemberListMapper {

    /**
     * 스터디 그룹 멤버 리스트 생성
     *
     * @param id 회원 아이디
     * @author VJ특공대 이희영
     */
    public void createGroupMemberList(int id);

    /**
     * 스터디 그룹 멤버 리스트 조회
     *
     * @param studyGroupId 스터디 그룹 아이디
     * @return 스터디 그룹 멤버 리스트
     * @author VJ특공대 이희영
     */
    public List<Map<String, Object>> findAllGroupMemberList(int studyGroupId);

    /**
     * 스터디 그룹 가입 승인 시 멤버 리스트에 회원 정보 추가
     *
     * @param id 회원 아이디
     * @author VJ특공대 이희영
     */
    public void addMember(@Param("id") int id, @Param("studyGroupId") int studyGroupId);

    /**
     * 스터디 그룹 탈퇴 시 멤버 리스트에서 회원 정보 삭제
     *
     * @param id 회원 아이디
     * @author VJ특공대 이희영
     */
    public void removeMember(@Param("id") int id, @Param("studyGroupId") int studyGroupId);

    /**
     * 스터디 그룹에 가입된 회원인지 조회
     *
     * @param memberId     회원 아이디
     * @param studyGroupId 스터디 그룹 아이디
     * @return 조회된 스터디 그룹 멤버 리스트
     * @author VJ특공대 이희영
     */
    public GroupMemberList isGroupMember(@Param("memberId") int memberId, @Param("studyGroupId") int studyGroupId);

    /**
     * 가입된 스터디 그룹 리스트 조회
     *
     * @param memberId 회원 아이디
     * @return 스터디 그룹 리스트
     * @author VJ특공대 이희영
     */
    public List<Map<String, Object>> findMyGroupList(int memberId);

    /**
     * 특정 스터디 그룹에 팀원 있는지 여부 조회
     *
     * @param studyGroupId 스터디 그룹 아이디
     * @return 팀원 유무
     */
    public boolean readGroupMemberCount(int studyGroupId);

    /**
     * 특정 스터디 그룹에 가장 오래된 팀원 ID 조회
     *
     * @param studyGroupId 스터디 그룹 아이디
     * @return 팀원 아이디
     */
    public int readOldestMemberByGroupId(int studyGroupId);

    /**
     * 특정 스터디 그룹의 특정 멤버 포지션 팀원에서 팀장으로 업데이트
     *
     * @param memberId     멤버 아이디
     * @param studyGroupId 스터디 그룹 아이디
     */
    public void updateMemberPosition(@Param("memberId") int memberId, @Param("studyGroupId") int studyGroupId);
}