package com.vj.lets.domain.group.mapper;

import com.vj.lets.domain.group.dto.GroupMemberList;
import com.vj.lets.domain.group.util.PageParams;
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
     * @author VJ특공대 이희영
     * @param id 회원 아이디
     */
    public void createGroupMemberList(int id);

    /**
     * 스터디 그룹 멤버 리스트 조회
     *
     * @author VJ특공대 이희영
     * @param studyGroupId 스터디 그룹 아이디
     * @return 스터디 그룹 멤버 리스트
     */
    public List<Map<String, Object>> findAllGroupMemberList(int studyGroupId);

    /**
     * 스터디 그룹 가입 승인 시 멤버 리스트에 회원 정보 추가
     *
     * @author VJ특공대 이희영
     * @param id 회원 아이디
     */
    public void addMember(@Param("id") int id, @Param("studyGroupId") int studyGroupId);

    /**
     * 스터디 그룹 탈퇴 시 멤버 리스트에서 회원 정보 삭제
     *
     * @author VJ특공대 이희영
     * @param id 회원 아이디
     */
    public void removeMember(@Param("id") int id, @Param("studyGroupId") int studyGroupId);

    /**
     * 스터디 그룹 삭제 시 멤버 리스트 전체 삭제
     *
     * @author VJ특공대 이희영
     * @param studyGroupId 스터디 그룹 아이디
     */
    public void removeAllMember(int studyGroupId);

    /**
     * 스터디 그룹에 가입된 회원인지 조회
     *
     * @author VJ특공대 이희영
     * @param memberId     회원 아이디
     * @param studyGroupId 스터디 그룹 아이디
     * @return 조회된 스터디 그룹 멤버 리스트
     */
    public GroupMemberList isGroupMember(@Param("memberId") int memberId, @Param("studyGroupId") int studyGroupId);

    /**
     * 가입된 스터디 그룹 리스트 조회
     *
     * @author VJ특공대 이희영
     * @param memberId 회원 아이디
     * @return 스터디 그룹 리스트
     */
    public List<Map<String, Object>> findMyGroupList(int memberId);

    /**
     * 특정 스터디 그룹에 팀원 있는지 여부 조회
     *
     * @author VJ특공대 김종원
     * @param studyGroupId 스터디 그룹 아이디
     * @return 팀원 유무
     */
    public boolean readGroupMemberCount(int studyGroupId);

    /**
     * 특정 스터디 그룹에 가장 오래된 팀원 ID 조회
     *
     * @author VJ특공대 김종원
     * @param studyGroupId 스터디 그룹 아이디
     * @return 팀원 아이디
     */
    public int readOldestMemberByGroupId(int studyGroupId);

    /**
     * 특정 스터디 그룹의 특정 멤버 포지션 팀원에서 팀장으로 업데이트
     *
     * @author VJ특공대 김종원
     * @param memberId     멤버 아이디
     * @param studyGroupId 스터디 그룹 아이디
     */
    public void updateMemberPosition(@Param("memberId") int memberId, @Param("studyGroupId") int studyGroupId);

    /**
     * 가입한 스터디 그룹 수 조회
     * 
     * @author VJ특공대 이희영
     * @param id 멤버 아이디
     * @return 가입한 그룹 수
     */
    public int myStudyCount(int id);

    /**
     * 페이징 정보를 포함한 내가 가입한 스터디 그룹 조회
     *
     * @author VJ특공대 이희영
     * @param id 멤버 아이디
     * @param pageParams 페이징 정보
     * @return 가입한 리스트
     */
    public List<Map<String, Object>> findMyGroupListAndPageParams(@Param("id") int id, @Param("pageParams") PageParams pageParams);
}