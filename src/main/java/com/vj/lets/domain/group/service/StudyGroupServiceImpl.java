package com.vj.lets.domain.group.service;

import com.vj.lets.domain.group.dto.GroupContact;
import com.vj.lets.domain.group.dto.GroupMemberList;
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

    private final StudyGroupMapper studyGroupMapper;
    private final GroupHistoryMapper groupHistoryMapper;
    private final GroupContactMapper groupContactMapper;
    private final GroupMemberListMapper groupMemberListMapper;
    private final SiGunGuMapper siGunGuMapper;

    /**
     * 스터디 그룹 생성
     * @param studyGroup 스터디 그룹 정보
     * @param id 회원 아이디
     * @param siGunGuName 시,군,구 이름
     */
    @Override
    public void createStudy(StudyGroup studyGroup, int id, String siGunGuName) {
        SiGunGu siGunGu = siGunGuMapper.getSiGunGu(siGunGuName);
        studyGroup.setSiGunGuId(siGunGu.getId());
        studyGroupMapper.create(studyGroup);
        groupMemberListMapper.create(id);
        groupHistoryMapper.create();
    }

    /**
     * 스터디 그룹 전체 리스트 조회
     * @return 스터디 그룹 리스트
     */
    @Override
    public List<Map<String, Object>> getStudyGroupList() {
        List<Map<String, Object>> list = null;
        list = studyGroupMapper.findByAll();
        return list;
    }

    /**
     *  스터디 그룹 조회
     * @return 스터디 그룹 정보
     */
    @Override
    public Map<String, Object> findStudyGroup(int studyGroupId) {
        Map<String, Object> studyGroup = null;
        studyGroup = studyGroupMapper.getStudyGroup(studyGroupId);
        return studyGroup;
    }

    /**
     * 스터디 그룹 정보 수정
     * @param studyGroup 스터디 그룹 수정 정보
     */
    @Override
    public void editStudyGroup(StudyGroup studyGroup) {
        studyGroupMapper.update(studyGroup);
        groupHistoryMapper.update(studyGroup.getId());
    }

    /**
     * 스터디 그룹 삭제
     * @param id 스터디 그룹 아이디
     */
    @Override
    public void deleteStudyGroup(int id) {
        studyGroupMapper.delete(id);
        groupHistoryMapper.delete(id);
    }

    /**
     * 스터디 그룹 회원 리스트 조회
     * @param studyGroupId 스터디 그룹 아이디
     * @return 회원 리스트
     */
    @Override
    public List<GroupMemberList> findByAllMember(int studyGroupId) {
        List<GroupMemberList> list = null;
        list = groupMemberListMapper.findByAll(studyGroupId);
        return list;
    }

    /**
     * 스터디 그룹 회원 추가
     * @param id 회원 아이디
     * @param studyGroupId 스터디 그룹 아이디
     */
    @Override
    public void addMember(int id, int studyGroupId) {
        groupMemberListMapper.addMember(id, studyGroupId);
    }

    /**
     * 스터디 그룹 회원 제거
     * @param id 회원 아이디
     * @param studyGroupId 스터디 그룹 아이디
     */
    @Override
    public void removeMember(int id, int studyGroupId) {
        groupMemberListMapper.removeMember(id, studyGroupId);
        studyGroupMapper.decrease(id);
        groupHistoryMapper.update(studyGroupId);
    }

    /**
     * 스터디 가입 신청
     * @param id 회원 아이디
     * @param studyGroupId 스터디 그룹 아이디
     */
    @Override
    public void registerStudy(int id, int studyGroupId) {
        groupContactMapper.register(id, studyGroupId);
    }

    /**
     * 스터디 가입 신청 회원 리스트
     * @param studyGroupId 스터디 그룹 아이디
     * @return 회원 리스트
     */
    @Override
    public List<GroupContact> findByAllRegist(int studyGroupId) {
        List<GroupContact> list = null;
        list = groupContactMapper.findByAll(studyGroupId);
        return list;
    }

    /**
     * 스터디 가입 승인
     * @param id 회원 아이디
     * @param studyGroupId 스터디 그룹 아이디
     */
    @Override
    public void approve(int id, int studyGroupId) {
        groupContactMapper.approve(id, studyGroupId);
        groupContactMapper.delete(id, studyGroupId);
        groupMemberListMapper.addMember(id, studyGroupId);
        studyGroupMapper.increase(id);
        groupHistoryMapper.update(studyGroupId);
    }

    /**
     * 스터디 가입 거절
     * @param id 회원 아이디
     * @param studyGroupId 스터디 그룹 아이디
     */
    @Override
    public void refuse(int id, int studyGroupId) {
        groupContactMapper.register(id, studyGroupId);
        groupContactMapper.delete(id, studyGroupId);
    }

    /**
     * 스터디 회원인지 조회
     * @param memberId 회원 아이디
     * @param studyGroupId 스터디 그룹 아이디
     * @return 스터디 그룹 멤버 리스트 정보
     */
    @Override
    public GroupMemberList isMember(int memberId, int studyGroupId) {
        GroupMemberList groupMemberList = null;
        groupMemberList = groupMemberListMapper.isMember(memberId, studyGroupId);
        return groupMemberList;
    }

    @Override
    public List<Map<String, Object>> myGroupList(int memberId) {
        List<Map<String, Object>> studyList = null;

        studyList = groupMemberListMapper.myGroupList(memberId);
        return studyList;
    }
}