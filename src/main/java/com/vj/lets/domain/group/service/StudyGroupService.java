package com.vj.lets.domain.group.service;

import com.vj.lets.domain.group.dto.StudyGroup;

import java.util.List;

/**
 * 스터디 그룹 서비스 규약
 *
 * @author 작성자
 * @version 1.0
 * @since 2023-09-08 (금)
 */
public interface StudyGroupService {

    /**
     * 스터디 그룹 등록
     * @param studyGroup
     */
    public void register(StudyGroup studyGroup, int id);

    /**
     * 스터디 그룹 전체 리스트 조회
     * @return 스터디 그룹 리스트
     */
    public List<StudyGroup> getStudyGroupList();

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
}
