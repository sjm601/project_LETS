package com.vj.lets.domain.group.mapper;//import static org.junit.jupiter.api.Assertions.*;

import com.vj.lets.domain.group.dto.ParticipationList;
import com.vj.lets.domain.group.dto.StudyPlan;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class ParticipationListMapperTest {

    @Autowired
    private ParticipationListMapper participationListMapper;
    @Autowired
    private StudyPlanMapper studyPlanMapper;

    @Test
    @Transactional
    void createParticipationListTest() {
        // given
        int memberId = 30;

        StudyPlan studyPlan = StudyPlan.builder()
                .totalCount(10)
                .studyGroupId(1)
                .build();
        studyPlanMapper.createStudyPlan(studyPlan);

        ParticipationList participationList = ParticipationList.builder()
                .memberId(memberId)
                .memberType("주최자")
                .build();


        // when
        participationListMapper.createParticipationList(participationList);

        // then
        log.info("스터디 참여 리스트 생성 : {}", participationList);
    }

    @Test
    @Transactional
    void findAllParticipationListTest() {
        // given
        int studyPlanId = 1;

        // when
        List<Map<String, Object>> participationList = participationListMapper.findAllParticipationList(studyPlanId);

        // then
        log.info("스터디 일정 참여자 리스트 : {}", participationList);
        assertThat(participationList).isNotNull();
    }

    @Test
    @Transactional
    void participateStudyTest() {
        // given
        int memberId = 50;
        int studyPlanId = 1;

        // when
        participationListMapper.participateStudy(memberId, studyPlanId);

        // then
        log.info("스터디 일정 참여");
    }
}