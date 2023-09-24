package com.vj.lets.domain.group.mapper;

import com.vj.lets.domain.group.dto.GroupMemberList;
import com.vj.lets.domain.group.util.PageParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 스터디 멤버 목록 매퍼
 *
 * @author VJ특공대 이희영
 * @version 1.0
 * @since 2023-09-08 (금)
 */
@Mapper
public interface GroupMemberListMapper {

    /**
     * 스터디 그룹 멤버 목록 생성
     *
     * @param id 회원 ID
     * @author VJ특공대 이희영
     */
    public void createGroupMemberList(int id);

    /**
     * 스터디 그룹 멤버 목록 조회
     *
     * @param studyGroupId 스터디 그룹 ID
     * @return 스터디 그룹 멤버 목록
     * @author VJ특공대 이희영
     */
    public List<Map<String, Object>> findAllGroupMemberList(int studyGroupId);

    /**
     * 스터디 그룹 가입 승인 시 멤버 목록에 회원 정보 추가
     *
     * @param id 회원 ID
     * @author VJ특공대 이희영
     */
    public void addMember(@Param("id") int id, @Param("studyGroupId") int studyGroupId);

    /**
     * 스터디 그룹 탈퇴 시 멤버 목록에서 회원 정보 삭제
     *
     * @param id 회원 ID
     * @author VJ특공대 이희영
     */
    public void removeMember(@Param("id") int id, @Param("studyGroupId") int studyGroupId);

    /**
     * 스터디 그룹에 가입된 회원인지 조회
     *
     * @param memberId     회원 ID
     * @param studyGroupId 스터디 그룹 ID
     * @return 조회된 스터디 그룹 멤버 목록
     * @author VJ특공대 이희영
     */
    public GroupMemberList isGroupMember(@Param("memberId") int memberId, @Param("studyGroupId") int studyGroupId);

    /**
     * 가입된 스터디 그룹 목록 조회
     *
     * @param memberId 회원 ID
     * @return 스터디 그룹 목록
     * @author VJ특공대 이희영
     */
    public List<Map<String, Object>> findMyGroupList(int memberId);

    /**
     * 특정 스터디 그룹에 팀원 있는지 여부 조회
     *
     * @param studyGroupId 스터디 그룹 ID
     * @return 팀원 유무
     * @author VJ특공대 김종원
     */
    public boolean readGroupMemberCount(int studyGroupId);

    /**
     * 특정 스터디 그룹에 가장 오래된 팀원 ID 조회
     *
     * @param studyGroupId 스터디 그룹 ID
     * @return 팀원 ID
     * @author VJ특공대 김종원
     */
    public int readOldestMemberByGroupId(int studyGroupId);

    /**
     * 특정 스터디 그룹의 특정 멤버 포지션 팀원에서 팀장으로 업데이트
     *
     * @param memberId     멤버 ID
     * @param studyGroupId 스터디 그룹 ID
     * @author VJ특공대 김종원
     */
    public void updateMemberPosition(@Param("memberId") int memberId, @Param("studyGroupId") int studyGroupId);

    /**
     * 가입한 스터디 그룹 수 조회
     *
     * @param id 멤버 ID
     * @return 가입한 그룹 수
     * @author VJ특공대 이희영
     */
    public int myStudyCount(int id);

    /**
     * 페이징 정보를 포함한 내가 가입한 스터디 그룹 조회
     *
     * @param id         멤버 ID
     * @param pageParams 페이징 정보
     * @return 가입한 목록
     * @author VJ특공대 이희영
     */
    public List<Map<String, Object>> findMyGroupListAndPageParams(@Param("id") int id, @Param("pageParams") PageParams pageParams);
}