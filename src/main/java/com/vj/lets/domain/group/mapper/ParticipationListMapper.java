package com.vj.lets.domain.group.mapper;

import com.vj.lets.domain.group.dto.ParticipationList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 스터디 참여 리스트 매퍼
 *
 * @author VJ특공대 이희영
 * @version 1.0
 * @since 2023-09-20 (수)
 */
@Mapper
public interface ParticipationListMapper {

    /**
     * 스터디 참여 리스트 생성
     *
     * @author VJ특공대 이희영
     * @param memberId 멤버 아이디
     */
    public void createParticipationList(int memberId);

    /**
     * 스터디 일정 참여자 리스트 조회
     * 
     * @author VJ특공대 이희영
     * @param studyPlanId 스터디 일정 아이디
     * @return 참여자 리스트
     */
    public List<Map<String, Object>> findAllParticipationList(int studyPlanId);

    /**
     * 스터디 참여
     *
     * @author VJ특공대 이희영
     * @param memberId 멤버 아이디
     * @param studyPlanId 스터디 일정 아이디
     */
    public void participateStudy(@Param("memberId") int memberId, @Param("studyPlanId") int studyPlanId);

    /**
     * 참여자 리스트 삭제
     * 
     * @author VJ특공대 이희영
     * @param studyPlanId 스터디 일정 아이디
     */
    public void deleteParticipationList(int studyPlanId);
}
