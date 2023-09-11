package com.vj.lets.domain.group.mapper;//import static org.junit.jupiter.api.Assertions.*;

import com.vj.lets.domain.group.dto.GroupContact;
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
class GroupContactMapperTest {

    @Autowired
    private GroupContactMapper groupContactMapper;

    @Test
    @Transactional
    @Disabled
    void registerTest() {
        // given
        int id = 30;
        int studyGroupId = 1;

        // when
        groupContactMapper.register(id, studyGroupId);

        // then
        log.info("가입 신청 성공");
    }

    @Test
    @Transactional
    @Disabled
    void findByAllTest() {
        // given
        int studyGroupId = 1;

        // when
        List<GroupContact> list = groupContactMapper.findByAll(studyGroupId);

        // then
        log.info("가입 신청 리스트 : {}", list);
        assertThat(list).isNotNull();
    }

    @Test
    @Transactional
    @Disabled
    void approveTest() {
        // given
        int id = 30;
        int studyGroupId = 1;

        // when
        groupContactMapper.approve(id, studyGroupId);

        // then
        log.info("가입 승인");
    }

    @Test
    @Transactional
    @Disabled
    void refuseTest() {
        // given
        int id = 30;
        int studyGroupId = 1;

        // when
        groupContactMapper.refuse(id, studyGroupId);

        // then
        log.info("가입 거절");
    }

    @Test
    @Transactional
    @Disabled
    void deleteTest() {
        // given
        int id = 30;
        int studyGroupId = 1;

        // when
        groupContactMapper.delete(id, studyGroupId);

        // then
        log.info("가입 신청 삭제");
    }
}