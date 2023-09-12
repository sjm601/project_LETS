package com.vj.lets.domain.group.service;

import com.vj.lets.domain.group.dto.GroupContact;
import com.vj.lets.domain.group.dto.GroupMemberList;
import com.vj.lets.domain.group.dto.StudyGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 스터디 그룹 서비스 규약
 *
 * @author VJ특공대 이희영
 * @version 1.0
 * @since 2023-09-08 (금)
 */
public interface StudyGroupService {

    /**
     * 스터디 그룹 등록
     * @param studyGroup
     */
    public void register(StudyGroup studyGroup, int id, String siGunGuName);

    /**
     * 스터디 그룹 전체 리스트 조회
     * @return 스터디 그룹 리스트
     */
    public List<Map<String, Object>> getStudyGroupList();

    /**
     * 스터디 그룹 조회
     * @param studyGroupId 스터디 그룹 아이디
     * @return 스터디 그룹 정보
     */
    public List<Map<String, Object>> findStudyGroup(int studyGroupId);

    /**
     * 스터디 그룹 수정
     * @param studyGroup
     */
    public void editStudyGroup(StudyGroup studyGroup);

    /**
     * 스터디 그룹 삭제
     * @param id
     */
    public void deleteStudyGroup(int id);

    /**
     * 내 스터디 멤버 리스트 조회
     * @param studyGroupId 스터디 그룹 아이디
     * @return 멤버 리스트
     */
    public List<GroupMemberList> findByAllMember(int studyGroupId);

    /**
     * 내 스터디 회원 추가
     * @param id 회원 아이디
     * @param studyGroupId 스터디 그룹 아이디
     */
    public void addMember(@Param("id") int id, @Param("studyGroupId") int studyGroupId);

    /**
     * 내 스터디 회원 탈퇴
     * @param id 회원 아이디
     * @param studyGroupId 스터디 그룹 아이디
     */
    public void removeMember(@Param("id") int id, @Param("studyGroupId") int studyGroupId);

    /**
     * 스터디 가입 신청
     * @param id 회원 아이디
     * @param studyGroupId 스터디 그룹 아이디
     */
    public void register(@Param("id") int id, @Param("studyGroupId") int studyGroupId);

    /**
     * 내 스터디 가입 신청 리스트
     * @param studyGroupId 스터디 그룹 아이디
     * @return 가입 신청 리스트
     */
    public List<GroupContact> findByAllRegist(int studyGroupId);

    /**
     * 스터디 가입 신청 승인
     * @param id 회원 아이디
     * @param studyGroupId 스터디 그룹 아이디
     */
    public void approve(@Param("id") int id, @Param("studyGroupId") int studyGroupId);

    /**
     * 스터디 가입 신청 거절
     * @param id 회원 아이디
     * @param studyGroupId 스터디 그룹 아이디
     */
    public void refuse(@Param("id") int id, @Param("studyGroupId") int studyGroupId);
}