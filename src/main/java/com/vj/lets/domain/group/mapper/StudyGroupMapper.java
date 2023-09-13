package com.vj.lets.domain.group.mapper;

import com.vj.lets.domain.group.dto.Search;
import com.vj.lets.domain.group.dto.StudyGroup;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 스터디 그룹 매퍼
 *
 * @author VJ특공대 이희영
 * @version 1.0
 * @since 2023-09-08 (금)
 */
@Mapper
public interface StudyGroupMapper {

    /**
     * 스터디 그룹 생성
     *
     * @author VJ특공대 이희영
     * @param studyGroup 생성할 스터디 그룹 정보
     * @return 생성된 스터디 그룹
     */
    public void create(StudyGroup studyGroup);

    /**
     * 스터디 그룹 아이디 조회
     * 
     * @return 스터디 그룹 아이디
     */
    public int findId();

    /**
     * 스터디 그룹 전체 리스트 조회
     *
     * @return 스터디 그룹 리스트
     */
    public List<Map<String, Object>> findAll(Search search);

    /**
     * 스터디 그룹 조회
     * 
     * @param studyGroupId 스터디 그룹 아이디
     * @return 조회된 스터디 그룹 정보
     */
    public Map<String, Object> read(int studyGroupId);

    /**
     * 스터디 그룹 변경
     * 
     * @param studyGroup 변경할 스터디 그룹 정보
     */
    public void update(StudyGroup studyGroup);

    /**
     * 스터디 그룹 삭제
     * 
     * @param studyGroupId 스터디 그룹 아이디
     */
    public void delete(int studyGroupId);

    /**
     * 스터디 그룹 현재 회원수 1 증가
     *
     * @param studyGroupId 스터디 그룹 아이디
     */
    public void add(int studyGroupId);

    /**
     * 스터디 그룹 현재 회원수 1 감소
     *
     * @param studyGroupId 스터디 그룹 아이디
     */
    public void subtract(int studyGroupId);
}
