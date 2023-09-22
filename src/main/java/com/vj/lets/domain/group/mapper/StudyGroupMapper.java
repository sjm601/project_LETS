package com.vj.lets.domain.group.mapper;

import com.vj.lets.domain.group.dto.Search;
import com.vj.lets.domain.group.dto.StudyGroup;
import com.vj.lets.domain.group.util.PageParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
     */
    public void createStudy(StudyGroup studyGroup);

    /**
     * 스터디 그룹 아이디 조회
     *
     * @author VJ특공대 이희영
     * @return 스터디 그룹 아이디
     */
    public int findByStudyId();

    /**
     * 스터디 그룹 전체 리스트 조회
     *
     * @author VJ특공대 이희영
     * @param  pageParams 페이징 정보
     * @return 스터디 그룹 리스트
     */
    public List<Map<String, Object>> findAllStudyList(PageParams pageParams);

    /**
     * 스터디 그룹 조회
     *
     * @author VJ특공대 이희영
     * @param studyGroupId 스터디 그룹 아이디
     * @return 조회된 스터디 그룹 정보
     */
    public Map<String, Object> readStudy(int studyGroupId);

    /**
     * 스터디 그룹 변경
     *
     * @author VJ특공대 이희영
     * @param studyGroup 변경할 스터디 그룹 정보
     */
    public void updateStudy(StudyGroup studyGroup);

    /**
     * 스터디 그룹 삭제
     *
     * @author VJ특공대 이희영
     * @param studyGroupId 스터디 그룹 아이디
     */
    public void deleteStudy(int studyGroupId);

    /**
     * 스터디 그룹 현재 회원수 1 증가
     *
     * @author VJ특공대 이희영
     * @param studyGroupId 스터디 그룹 아이디
     */
    public void plusCurrentCount(int studyGroupId);

    /**
     * 스터디 그룹 현재 회원수 1 감소
     *
     * @author VJ특공대 이희영
     * @param studyGroupId 스터디 그룹 아이디
     */
    public void minusCurrentCount(int studyGroupId);

    /**
     * 신규 스터디 그룹 리스트 3개 조회
     * 
     * @author VJ특공대 이희영
     * @return 신규 스터디 그룹 리스트
     */
    public List<StudyGroup> findNewStudyList();

    /**
     * 스터디 검색 결과 수 조회
     *
     * @author VJ특공대 이희영
     * @param keyword 키워드
     * @param subject 주제
     * @return 검색된 수
     */
    public int studySearchCount(@Param("keyword") String keyword, @Param("subject") String subject);
}
