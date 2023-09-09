package com.vj.lets.domain.group.mapper;//import static org.junit.jupiter.api.Assertions.*;

import com.vj.lets.domain.group.dto.StudyGroup;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class StudyGroupMapperTest {

    @Autowired
    private StudyGroupMapper studyGroupMapper;

    @Test
    @Transactional
    void createTest() {
        // given
        StudyGroup studyGroup = StudyGroup.builder()
                .id(1)
                .name("스터디")
                .headCount(10)
                .subject("주식")
                .siGunGuId(1)
                .build();
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

        // then

    }

    @Test
    @Transactional
    @Disabled
    void readTest() {
        // given

        // when

        // then

    }

    @Test
    @Transactional
    @Disabled
    void updateTest() {
        // given

        // when

        // then

    }

    @Test
    @Transactional
    @Disabled
    void deleteTest() {
        // given

        // when

        // then

    }
}