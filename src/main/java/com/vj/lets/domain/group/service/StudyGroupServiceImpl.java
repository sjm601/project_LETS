package com.vj.lets.domain.group.service;

import com.vj.lets.domain.group.dto.StudyGroup;
import com.vj.lets.domain.group.mapper.GroupHistoryMapper;
import com.vj.lets.domain.group.mapper.GroupMemberListMapper;
import com.vj.lets.domain.group.mapper.StudyGroupMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 스터디 그룹 서비스 구현체
 *
 * @author 이희영
 * @version 1.0
 * @since 2023-09-08 (금)
 */
@RequiredArgsConstructor
@Service
public class StudyGroupServiceImpl implements StudyGroupService {

    private final StudyGroupMapper studyGroupMapper;
    private final GroupHistoryMapper groupHistoryMapper;
    private final GroupMemberListMapper groupMemberListMapper;

    /**
     *스터디 그룹 등록
     * @param studyGroup
     */
    @Override
    @Transactional
    public void register(StudyGroup studyGroup, int id) {
        studyGroupMapper.create(studyGroup);
        groupHistoryMapper.create();
        groupMemberListMapper.create(id);
    }

    /**
     * 스터디 그룹 전체 리스트 조회
     * @return 스터디 그룹 리스트
     */
    @Override
    public List<StudyGroup> getStudyGroupList() {
        return null;
    }

    /**
     * 스터디 그룹 수정
     * @param studyGroup
     */
    @Override
    public void editStudyGroup(StudyGroup studyGroup) {

    }

    /**
     * 스터디 그룹 삭제
     * @param id
     */
    @Override
    public void deleteStudyGroup(int id) {

    }
}
