package com.vj.lets.domain.group.service;

import com.vj.lets.domain.group.dto.GroupContact;
import com.vj.lets.domain.group.dto.GroupMemberList;
import com.vj.lets.domain.group.dto.StudyGroup;
import com.vj.lets.domain.group.util.PageParams;

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
     * 스터디 그룹 생성
     *
     * @param studyGroup  생성할 스터디 그룹 정보
     * @param id          회원 ID
     * @param siGunGuName 시,군,구 이름
     * @param siDoName    시,도 이름
     * @return 생성된 스터디 그룹 ID
     * @author VJ특공대 이희영
     */
    public int generateStudy(StudyGroup studyGroup, int id, String siGunGuName, String siDoName);

    /**
     * 스터디 그룹 전체 목록 조회
     *
     * @param pageParams 페이징 정보
     * @return 스터디 그룹 목록
     * @author VJ특공대 이희영
     */
    public List<Map<String, Object>> getStudyList(PageParams pageParams);

    /**
     * 스터디 그룹 화면 출력
     *
     * @param studyGroupId 스터디 그룹 ID
     * @return 조회된 스터디 그룹 정보
     * @author VJ특공대 이희영
     */
    public Map<String, Object> viewStudy(int studyGroupId);

    /**
     * 스터디 그룹 수정
     *
     * @param studyGroup 수정할 스터디 그룹 정보
     * @author VJ특공대 이희영
     */
    public void editStudy(StudyGroup studyGroup);

    /**
     * 스터디 그룹 삭제
     *
     * @param id 삭제할 스터디 그룹 ID
     * @author VJ특공대 이희영
     */
    public void removeStudy(int id);

    /**
     * 스터디 그룹에 가입된 회원 목록 조회
     *
     * @param studyGroupId 스터디 그룹 ID
     * @return 조회된 회원 목록
     * @author VJ특공대 이희영
     */
    public List<Map<String, Object>> getStudyMemberList(int studyGroupId);

    /**
     * 스터디 그룹 회원 추가
     *
     * @param id           회원 ID
     * @param studyGroupId 스터디 그룹 ID
     * @author VJ특공대 이희영
     */
    public void studyAddMember(int id, int studyGroupId);

    /**
     * 스터디 그룹 회원 제거
     *
     * @param id           회원 ID
     * @param studyGroupId 스터디 그룹 ID
     * @author VJ특공대 이희영
     */
    public void studySubtractMember(int id, int studyGroupId);

    /**
     * 스터디 그룹 가입 신청
     *
     * @param id           회원 ID
     * @param studyGroupId 스터디 그룹 ID
     * @author VJ특공대 이희영
     */
    public GroupContact contactStudy(int id, int studyGroupId);

    /**
     * 스터디 그룹 가입 신청 목록 조회
     *
     * @param studyGroupId 스터디 그룹 ID
     * @return 가입 신청한 회원 목록
     * @author VJ특공대 이희영
     */
    public List<Map<String, Object>> getStudyContactList(int studyGroupId);

    /**
     * 스터디 그룹 가입 신청 승인
     *
     * @param id           회원 ID
     * @param studyGroupId 스터디 그룹 ID
     * @author VJ특공대 이희영
     */
    public void approveStudyContact(int id, int studyGroupId);

    /**
     * 스터디 그룹 가입 신청 거절
     *
     * @param id           회원 ID
     * @param studyGroupId 스터디 그룹 ID
     * @author VJ특공대 이희영
     */
    public void refuseStudyContact(int id, int studyGroupId);

    /**
     * 스터디 그룹에 가입된 회원인지 조회
     *
     * @param memberId     회원 ID
     * @param studyGroupId 스터디 그룹 ID
     * @return 조회된 스터디 그룹 멤버 목록
     * @author VJ특공대 이희영
     */
    public GroupMemberList isGroupMember(int memberId, int studyGroupId);

    /**
     * 가입한 스터디 그룹 목록 조회
     *
     * @param memberId 회원 ID
     * @return 가입한 스터디 그룹 목록
     * @author VJ특공대 이희영
     */
    public List<Map<String, Object>> getMyStudyList(int memberId);

    /**
     * 신규 스터디 그룹 목록 3개 조회
     *
     * @return 신규 스터디 그룹 목록
     * @author VJ특공대 이희영
     */
    public List<StudyGroup> getNewStudyList();

    /**
     * 스터디 그룹 검색 수 조회
     *
     * @param keyword 검색 키워드
     * @param subject 검색 주제
     * @return 검색 결과 수
     * @author VJ특공대 이희영
     */
    public int getSearchCount(String keyword, String subject);

    /**
     * 가입된 스터디 그룹 수 조회
     *
     * @param id 회원 ID
     * @return 가입한 스터디 수
     * @author VJ특공대 이희영
     */
    public int getMyStudyCount(int id);

    /**
     * 페이징 정보를 포함한 내가 가입한 스터디 그룹 조회
     *
     * @param id         회원 ID
     * @param pageParams 페이징 정보
     * @return 가입한 목록
     * @author VJ특공대 이희영
     */
    public List<Map<String, Object>> getMyStudyListAndPageParams(int id, PageParams pageParams);
}