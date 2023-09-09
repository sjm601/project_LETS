package com.vj.lets.domain.group.mapper;

import com.vj.lets.domain.group.dto.StudyGroup;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 스터디 그룹 매퍼
 * 
 * @author 이희영
 * @version 1.0
 * @since 2023-09-08 (금)
 */
@Mapper
public interface StudyGroupMapper {

    /**
     * 스터디 그룹 생성
     * @param studyGroup
     */
    public void create(StudyGroup studyGroup);

    /**
     * 스터디 그룹 전체 리스트 조회
     * @return 스터디 그룹 리스트
     */
    public List<StudyGroup> findByAll();
    /**
     * 스터디 그룹 조회
     * @param StudyGroupId
     * @return 스터디 그룹
     */
    public StudyGroup read(int StudyGroupId);

    /**
     * 스터디 그룹 변경
     * @param studyGroup
     */
    public void update(StudyGroup studyGroup);

    /**
     * 스터디 그룹 삭제
     * @param studyGroupId
     */
    public void delete(int studyGroupId);
}
