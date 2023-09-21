package com.vj.lets.domain.group.mapper;//import static org.junit.jupiter.api.Assertions.*;

import com.vj.lets.domain.group.dto.StudyPlan;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class StudyPlanMapperTest {

    @Autowired
    private StudyPlanMapper studyPlanMapper;
    @Autowired
    private ParticipationListMapper participationListMapper;

    @Test
    @Transactional
    void createStudyPlanTest() {
        // given
        StudyPlan studyPlan = StudyPlan.builder()
                .totalCount(10)
                .studyGroupId(1)
                .build();

        // when
        studyPlanMapper.createStudyPlan(studyPlan);

        // then
        log.info("스터디 일정 생성 : {}", studyPlan);
    }

    @Test
    @Transactional
    void findAllStudyPlanTest() {
        // given
        int studyGroupId = 1;

        // when
        List<StudyPlan> studyPlanList = studyPlanMapper.findAllStudyPlan(studyGroupId);

        // then
        log.info("스터디에 있는 스터디 일정 리스트 조회 : {}", studyPlanList);
        assertThat(studyPlanList).isNotNull();
    }

    @Test
    @Transactional
    void updateStudyPlanTest() {
        // given
        int studyPlanId = 1;
        int reservationId = 1;

        // when
        studyPlanMapper.updateStudyPlan(studyPlanId, reservationId);

        // then
        log.info("스터디 일정 예약 변경");
    }

    @Test
    @Transactional
    void deleteStudyPlanTest() {
        // given
        int studyPlanId = 1;
        int participationListId = 1;

        // when
        participationListMapper.deleteParticipationList(participationListId);
        studyPlanMapper.deleteStudyPlan(studyPlanId);

        // then
        log.info("스터디 일정 삭제");
    }

    @Test
    @Transactional
    void plusCurrentCountTest() {
        // given
        int studyPlanId = 1;

        // when
        studyPlanMapper.plusCurrentCount(studyPlanId);

        // then
        log.info("스터디 일정 현재 인원 1증가");
    }
}