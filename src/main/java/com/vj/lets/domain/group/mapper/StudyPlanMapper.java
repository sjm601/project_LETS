package com.vj.lets.domain.group.mapper;

import com.vj.lets.domain.group.dto.StudyPlan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 스터디 일정 매퍼
 *
 * @author VJ특공대 이희영
 * @version 1.0
 * @since 2023-09-20 (수)
 */
@Mapper
public interface StudyPlanMapper {

    /**
     * 스터디 일정 생성
     * 
     * @author VJ특공대 이희영
     * @param studyPlan 스터디 일정
     */
    public void createStudyPlan(StudyPlan studyPlan);

    /**
     * 스터디 그룹에 스터디 일정 리스트 조회
     * 
     * @author VJ특공대 이희영
     * @param studyGroupId 스터디 그룹 아이디
     * @return 스터디 일정 리스트
     */
    public List<StudyPlan> findAllStudyPlan(int studyGroupId);

    /**
     * 스터디 일정 수정
     * 
     * @author VJ특공대 이희영
     * @param studyPlanId 스터디 일정 아이디
     * @param reservationId 예약 아이디
     */
    public void updateStudyPlan(@Param("studyPlanId") int studyPlanId, @Param("reservationId") int reservationId);

    /**
     * 스터디 일정 삭제
     *  
     * @author VJ특공대 이희영
     * @param studyPlanId 스터디 일정 아이디
     */
    public void deleteStudyPlan(int studyPlanId);

    /**
     * 스터디 일정 현재 인원 1 증가
     *
     * @author VJ특공대 이희영
     * @param studyPlanId 스터디 일정 아이디
     */
    public void plusCurrentCount(int studyPlanId);
}
