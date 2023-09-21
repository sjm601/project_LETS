package com.vj.lets.domain.group.service;

import com.vj.lets.domain.group.dto.*;
import com.vj.lets.domain.group.util.PageParams;
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
     * 스터디 그룹 생성
     *
     * @author VJ특공대 이희영
     * @param studyGroup 생성할 스터디 그룹 정보
     * @param id 회원 아이디
     * @param siGunGuName 시,군,구 이름
     * @param siDoName 시,도 이름
     * @return 생성된 스터디 그룹 아이디
     */
    public int generateStudy(StudyGroup studyGroup, int id, String siGunGuName, String siDoName);

    /**
     * 스터디 그룹 전체 리스트 조회
     *
     * @author VJ특공대 이희영
     * @param  pageParams 페이징 정보
     * @return 스터디 그룹 리스트
     */
    public List<Map<String, Object>> getStudyList(PageParams pageParams);

    /**
     * 스터디 그룹 화면 출력
     *
     * @author VJ특공대 이희영
     * @param studyGroupId 스터디 그룹 아이디
     * @return 조회된 스터디 그룹 정보
     */
    public Map<String, Object> viewStudy(int studyGroupId);

    /**
     * 스터디 그룹 수정
     *
     * @author VJ특공대 이희영
     * @param studyGroup 수정할 스터디 그룹 정보
     */
    public void editStudy(StudyGroup studyGroup);

    /**
     * 스터디 그룹 삭제
     *
     * @author VJ특공대 이희영
     * @param id 삭제할 스터디 그룹 아이디
     */
    public void removeStudy(int id);

    /**
     * 스터디 그룹에 가입된 회원 리스트 조회
     *
     * @author VJ특공대 이희영
     * @param studyGroupId 스터디 그룹 아이디
     * @return 조회된 회원 리스트
     */
    public List<Map<String, Object>> getStudyMemberList(int studyGroupId);

    /**
     * 스터디 그룹 회원 추가
     *
     * @author VJ특공대 이희영
     * @param id 회원 아이디
     * @param studyGroupId 스터디 그룹 아이디
     */
    public void studyAddMember(int id, int studyGroupId);

    /**
     * 스터디 그룹 회원 제거
     *
     * @author VJ특공대 이희영
     * @param id 회원 아이디
     * @param studyGroupId 스터디 그룹 아이디
     */
    public void studySubtractMember(int id, int studyGroupId);

    /**
     * 스터디 그룹 가입 신청
     *
     * @author VJ특공대 이희영
     * @param id 회원 아이디
     * @param studyGroupId 스터디 그룹 아이디
     */
    public GroupContact contactStudy(int id, int studyGroupId);

    /**
     * 스터디 그룹 가입 신청 리스트 조회
     *
     * @author VJ특공대 이희영
     * @param studyGroupId 스터디 그룹 아이디
     * @return 가입 신청한 회원 리스트
     */
    public List<Map<String, Object>> getStudyContactList(int studyGroupId);

    /**
     * 스터디 그룹 가입 신청 승인
     *
     * @author VJ특공대 이희영
     * @param id 회원 아이디
     * @param studyGroupId 스터디 그룹 아이디
     */
    public void approveStudyContact(int id, int studyGroupId);

    /**
     * 스터디 그룹 가입 신청 거절
     *
     * @author VJ특공대 이희영
     * @param id 회원 아이디
     * @param studyGroupId 스터디 그룹 아이디
     */
    public void refuseStudyContact(int id, int studyGroupId);

    /**
     * 스터디 그룹에 가입된 회원인지 조회
     *
     * @author VJ특공대 이희영
     * @param memberId 회원 아이디
     * @param studyGroupId 스터디 그룹 아이디
     * @return 조회된 스터디 그룹 멤버 리스트
     */
    public GroupMemberList isGroupMember(int memberId, int studyGroupId);

    /**
     * 가입한 스터디 그룹 리스트 조회
     *
     * @author VJ특공대 이희영
     * @param  memberId 회원 아이디
     * @return 가입한 스터디 그룹 리스트
     */
    public List<Map<String, Object>> getMyStudyList(int memberId);

    /**
     * 신규 스터디 그룹 리스트 3개 조회
     *
     * @author VJ특공대 이희영
     * @return 신규 스터디 그룹 리스트
     */
    public List<StudyGroup> getNewStudyList();

    /**
     * 스터디 그룹 검색 수 조회
     *
     * @author VJ특공대 이희영
     * @param keyword 검색 키워드
     * @return 검색 결과 수
     */
    public int getSearchCount(String keyword);

    /**
     * 가입된 스터디 그룹 수 조회
     * 
     * @author VJ특공대 이희영
     * @param id 회원 아이디
     * @return 가입한 스터디 수
     */
    public int getMyStudyCount(int id);

    /**
     * 페이징 정보를 포함한 내가 가입한 스터디 그룹 조회
     *
     * @author VJ특공대 이희영
     * @param id 회원 아이디
     * @param pageParams 페이징 정보
     * @return 가입한 리스트
     */
    public List<Map<String, Object>> getMyStudyListAndPageParams(int id, PageParams pageParams);

    /**
     * 스터디 일정 생성
     * 
     * @author VJ특공대 이희영
     * @param studyPlan 스터디 일정 정보
     * @param memberId 멤버 아이디
     */
    public void generateStudyPlan(StudyPlan studyPlan, int memberId);

    /**
     * 스터이 일정에 예약 정보 등록
     *
     * @author VJ특공대 이희영
     * @param studyPlanId 스터디 일정 아이디
     * @param reservationId 예약 아이디
     */
    public void registerReservation(int studyPlanId, int reservationId);

    /**
     * 스터디 일정 참여
     * 
     * @author VJ특공대 이희영
     * @param memberId 멤버 아이디
     * @param studyPlanId 스터디 일정 아이디
     */
    public void participateStudyPlan(int memberId, int studyPlanId);

    /**
     * 스터디 일정 삭제
     * 
     * @author VJ특공대 이희영
     * @param studyPlanId 스터디 일정 아이디
     */
    public void removeStudyPlan(int studyPlanId);
}