package com.vj.lets.domain.group.service;

import com.vj.lets.domain.group.dto.GroupMemberList;
import com.vj.lets.domain.group.dto.Search;
import com.vj.lets.domain.group.dto.StudyGroup;
import com.vj.lets.domain.group.mapper.GroupContactMapper;
import com.vj.lets.domain.group.mapper.GroupHistoryMapper;
import com.vj.lets.domain.group.mapper.GroupMemberListMapper;
import com.vj.lets.domain.group.mapper.StudyGroupMapper;
import com.vj.lets.domain.location.dto.SiGunGu;
import com.vj.lets.domain.location.mapper.SiGunGuMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 스터디 그룹 서비스 구현체
 *
 * @author VJ특공대 이희영
 * @version 1.0
 * @since 2023-09-08 (금)
 */
@RequiredArgsConstructor
@Service
public class StudyGroupServiceImpl implements StudyGroupService{

    private final SiGunGuMapper siGunGuMapper;
    private final StudyGroupMapper studyGroupMapper;
    private final GroupHistoryMapper groupHistoryMapper;
    private final GroupContactMapper groupContactMapper;
    private final GroupMemberListMapper groupMemberListMapper;

    /**
     * 스터디 그룹 생성
     *
     * @author VJ특공대 이희영
     * @param studyGroup 생성할 스터디 그룹 정보
     * @param id 회원 아이디
     * @param siGunGuName 시,군,구 이름
     * @return 생성된 스터디 그룹 아이디
     */
    @Override
    @Transactional
    public int generateStudy(StudyGroup studyGroup, int id, String siGunGuName) {
        // 전달 받은 시,군,구 이름을 시,군,구 아이디로 변환
        SiGunGu siGunGu = siGunGuMapper.getSiGunGu(siGunGuName);
        studyGroup.setSiGunGuId(siGunGu.getId());

        // 스터디 그룹 생성
        studyGroupMapper.createStudy(studyGroup);
        // 현재 생성된 스터디 그룹 아이디 조회
        int studyGroupId = studyGroupMapper.findByStudyId();
        // 스터디 그룹 멤버 리스트에 전달 받은 회원을 팀장으로 추가
        groupMemberListMapper.createGroupMemberList(id);
        // 스터디 그룹 히스토리 추가
        groupHistoryMapper.createGroupHistory();

        return studyGroupId;
    }

    /**
     * 스터디 그룹 전체 리스트 조회
     *
     * @author VJ특공대 이희영
     * @return 스터디 그룹 리스트
     */
    @Override
    public List<Map<String, Object>> getStudyList(Search search) {
        List<Map<String, Object>> list = null;

        list = studyGroupMapper.findAllStudyList(search);
        return list;
    }

    /**
     * 스터디 그룹 조회
     *
     * @author VJ특공대 이희영
     * @param studyGroupId 스터디 그룹 아이디
     * @return 조회된 스터디 그룹 정보
     */
    @Override
    public Map<String, Object> viewStudy(int studyGroupId) {
        Map<String, Object> studyGroup = null;

        studyGroup = studyGroupMapper.readStudy(studyGroupId);
        return studyGroup;
    }

    /**
     * 스터디 그룹 정보 수정
     *
     * @author VJ특공대 이희영
     * @param studyGroup 수정할 스터디 그룹 정보
     */
    @Override
    @Transactional
    public void editStudy(StudyGroup studyGroup) {
        // 스터디 그룹 정보 수정
        studyGroupMapper.updateStudy(studyGroup);
        // 스터디 그룹 히스토리 추가
        groupHistoryMapper.updateGroupHistory(studyGroup.getId());
    }

    /**
     * 스터디 그룹 삭제
     *
     * @author VJ특공대 이희영
     * @param id 삭제할 스터디 그룹 아이디
     */
    @Override
    @Transactional
    public void removeStudy(int id) {
        // 스터디 그룹 정보 삭제 -> DB에 정보는 남기고 상태를 disabled로 변경
        studyGroupMapper.deleteStudy(id);
        // 스터디 그룹 히스토리 추가
        groupHistoryMapper.deleteGroupHistory(id);
    }

    /**
     * 스터디 그룹에 가입된 회원 리스트 조회
     *
     * @author VJ특공대 이희영
     * @param studyGroupId 스터디 그룹 아이디
     * @return 조회된 회원 리스트
     */
    @Override
    public List<Map<String, Object>> getStudyMemberList(int studyGroupId) {
        List<Map<String, Object>> list = null;

        list = groupMemberListMapper.findAllGroupMemberList(studyGroupId);
        return list;
    }

    /**
     * 스터디 그룹 회원 추가
     *
     * @author VJ특공대 이희영
     * @param id 회원 아이디
     * @param studyGroupId 스터디 그룹 아이디
     */
    @Override
    @Transactional
    public void studyAddMember(int id, int studyGroupId) {
        // 스터디 그룹 멤버 리스트에 전달 받은 회원 id '팀원'으로 추가
        groupMemberListMapper.addMember(id, studyGroupId);
        // 스터디 그룹 현재 회원 수 1 증가
        studyGroupMapper.plusCurrentCount(id);
        // 스터디 그룹 히스토리 추가
        groupHistoryMapper.updateGroupHistory(studyGroupId);
    }

    /**
     * 스터디 그룹 회원 제거
     *
     * @author VJ특공대 이희영
     * @param id 회원 아이디
     * @param studyGroupId 스터디 그룹 아이디
     */
    @Override
    @Transactional
    public void studySubtractMember(int id, int studyGroupId) {
        // 스터디 그룹 멤버 리스트에 전달 받은 회원 id 삭제
        groupMemberListMapper.removeMember(id, studyGroupId);
        // 스터디 그룹 현재 회원 수 1 감소
        studyGroupMapper.minusCurrentCount(studyGroupId);
        // 스터디 그룹 히스토리 추가
        groupHistoryMapper.updateGroupHistory(studyGroupId);
    }

    /**
     * 스터디 그룹 가입 신청
     *
     * @author VJ특공대 이희영
     * @param id 회원 아이디
     * @param studyGroupId 스터디 그룹 아이디
     */
    @Override
    public void contactStudy(int id, int studyGroupId) {
        groupContactMapper.contactGroup(id, studyGroupId);
    }

    /**
     * 스터디 그룹 가입 신청 리스트 조회
     *
     * @author VJ특공대 이희영
     * @param studyGroupId 스터디 그룹 아이디
     * @return 가입 신청한 회원 리스트
     */
    @Override
    public List<Map<String, Object>> getStudyContactList(int studyGroupId) {
        List<Map<String, Object>> list = null;

        list = groupContactMapper.findAllGroupContactList(studyGroupId);
        return list;
    }

    /**
     * 스터디 그룹 가입 신청 승인
     *
     * @author VJ특공대 이희영
     * @param id 회원 아이디
     * @param studyGroupId 스터디 그룹 아이디
     */
    @Override
    @Transactional
    public void approveStudyContact(int id, int studyGroupId) {
        // 스터디 그룹 가입 신청 승인
        groupContactMapper.approveGroupContact(id, studyGroupId);
        // 스터디 그룹 가입 신청 리스트에서 신청 내역 삭제
        groupContactMapper.deleteContact(id, studyGroupId);
        // 스터디 그룹 멤버 리스트에 전달 받은 회원 id '팀원'으로 추가
        groupMemberListMapper.addMember(id, studyGroupId);
        // 스터디 그룹 현재 회원 수 1증가
        studyGroupMapper.plusCurrentCount(studyGroupId);
        // 스터디 그룹 히스토리 추가
        groupHistoryMapper.updateGroupHistory(studyGroupId);
    }

    /**
     * 스터디 그룹 가입 신청 거절
     *
     * @author VJ특공대 이희영
     * @param id 회원 아이디
     * @param studyGroupId 스터디 그룹 아이디
     */
    @Override
    @Transactional
    public void refuseStudyContact(int id, int studyGroupId) {
        // 스터디 그룹 가입 거절
        groupContactMapper.refuseGroupContact(id, studyGroupId);
        // 스터디 그룹 가입 신청 리스트에서 신청 내역 삭제
        groupContactMapper.deleteContact(id, studyGroupId);
    }

    /**
     * 스터디 그룹에 가입된 회원인지 조회
     *
     * @author VJ특공대 이희영
     * @param memberId 회원 아이디
     * @param studyGroupId 스터디 그룹 아이디
     * @return 조회된 스터디 그룹 멤버 리스트
     */
    @Override
    public GroupMemberList isGroupMember(int memberId, int studyGroupId) {
        GroupMemberList groupMemberList = null;

        groupMemberList = groupMemberListMapper.isGroupMember(memberId, studyGroupId);
        return groupMemberList;
    }

    /**
     * 가입한 스터디 그룹 리스트 조회
     *
     * @author VJ특공대 이희영
     * @param memberId 회원 아이디
     * @return 가입한 스터디 그룹 리스트
     */
    @Override
    public List<Map<String, Object>> getMyStudyList(int memberId) {
        List<Map<String, Object>> studyList = null;

        studyList = groupMemberListMapper.findMyGroupList(memberId);
        return studyList;
    }

    /**
     * 신규 스터디 그룹 리스트 3개 조회
     *
     * @author VJ특공대 이희영
     * @return 신규 스터디 그룹 리스트
     */
    @Override
    public List<StudyGroup> getNewStudyList() {
        List<StudyGroup> newStudyList = null;

        newStudyList = studyGroupMapper.findNewStudyList();
        return newStudyList;
    }
}