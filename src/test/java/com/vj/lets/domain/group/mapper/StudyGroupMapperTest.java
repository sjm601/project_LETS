package com.vj.lets.domain.group.mapper;//import static org.junit.jupiter.api.Assertions.*;

import com.vj.lets.domain.group.dto.StudyGroup;
import com.vj.lets.domain.location.dto.SiGunGu;
import com.vj.lets.domain.location.mapper.SiGunGuMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class StudyGroupMapperTest {

    @Autowired
    private StudyGroupMapper studyGroupMapper;
    @Autowired
    private SiGunGuMapper siGunGuMapper;

    @Test
    @Transactional
    @Disabled
    void createTest() {
        // given
        StudyGroup studyGroup = StudyGroup.builder()
                .name("스터디")
                .headCount(10)
                .subject("주식")
                .build();

        String siGunGuName = "노원구";
        SiGunGu siGunGu = siGunGuMapper.getSiGunGu(siGunGuName);

        studyGroup.setSiGunGuId(siGunGu.getId());

        // when
        studyGroupMapper.create(studyGroup);

        // then
        log.info("스터디그룹 정보 : {}", studyGroup);
        assertThat(studyGroup).isNotNull();
    }

    @Test
    @Transactional
    @Disabled
    void findByAllTest() {
        // given

        // when
        List<StudyGroup> studyGroupList = studyGroupMapper.findByAll();

        // then
        log.info("스터디 그룹 전체 리스트 : {}", studyGroupList);
        assertThat(studyGroupList).isNotNull();
    }

    @Test
    @Transactional
    @Disabled
    void getStudyGroupTest() {
        // given
        int studyGroupId = 1;

        // when
        StudyGroup studyGroup = studyGroupMapper.getStudyGroup(studyGroupId);

        // then
        log.info("스터디 그룹 정보 : {}", studyGroup);
        assertThat(studyGroup).isNotNull();
    }

    @Test
    @Transactional
    @Disabled
    void updateTest() {
        // given
        StudyGroup studyGroup = StudyGroup.builder()
                                          .id(1)
                                          .name("이름업데이트")
                                          .headCount(10)
                                          .subject("주제")
                                          .build();
        log.info("테스트 : {}", studyGroup);

        // when
        studyGroupMapper.update(studyGroup);
        StudyGroup updateStudyGroup = studyGroupMapper.getStudyGroup(1);

        // then
        log.info("스터디 그룹 업데이트 : {}", updateStudyGroup);
        assertThat(updateStudyGroup).isNotNull();
    }

    @Test
    @Transactional
    @Disabled
    void deleteTest() {
        // given
        int studyGroupId = 1;

        // when
        studyGroupMapper.delete(studyGroupId);
        StudyGroup studyGroup = studyGroupMapper.getStudyGroup(studyGroupId);

        // then
        log.info("삭제 완료");
        assertThat(studyGroup.getStatus()).isEqualTo("disabled");
    }
}