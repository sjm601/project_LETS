package com.vj.lets.domain.group.mapper;//import static org.junit.jupiter.api.Assertions.*;

import com.vj.lets.domain.group.dto.StudyGroup;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 스터디 그룹 히스토리 매퍼 테스트
 *
 * @author VJ특공대 이희영
 * @version 1.0
 * @since 2023-09-08 (금)
 */
@SpringBootTest
@Slf4j
class GroupHistoryMapperTest {

    @Autowired
    private GroupHistoryMapper groupHistoryMapper;
    @Autowired
    private StudyGroupMapper studyGroupMapper;

    @Test
    @Transactional
    void createTest() {
        // given
        StudyGroup studyGroup = StudyGroup.builder()
                .name("테스트")
                .totalCount(10)
                .subject("테스트주제")
                .siGunGuId(10020)
                .build();

        // when
        studyGroupMapper.create(studyGroup);
        groupHistoryMapper.create();

        // then
        log.info("스터디 그룹 히스토리 생성");
    }

    @Test
    @Transactional
    void updateTest() {
        // given
        int studyGroupId = 1;

        // when
        groupHistoryMapper.update(studyGroupId);

        // then
        log.info("스터디 그룹 히스토리 update로 생성");
    }

    @Test
    @Transactional
    void deleteTest() {
        // given
        int studyGroupId = 1;

        // when
        groupHistoryMapper.delete(studyGroupId);

        // then
        log.info("스터디 그룹 히스토리 delete로 생성");
    }
}