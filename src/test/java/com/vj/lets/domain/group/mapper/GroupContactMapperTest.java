package com.vj.lets.domain.group.mapper;

import com.vj.lets.domain.group.dto.GroupContact;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 그룹 신청 매퍼 테스트
 * 
 * @author VJ특공대 이희영
 * @version 1.0
 * @since 2023-09-11 (월)
 */
@SpringBootTest
@Slf4j
class GroupContactMapperTest {

    @Autowired
    private GroupContactMapper groupContactMapper;

    @Test
    @Transactional
    void registerTest() {
        // given
        int id = 30;
        int studyGroupId = 1;

        // when
        groupContactMapper.contactGroup(id, studyGroupId);

        // then
        log.info("가입 신청 성공");
    }

    @Test
    @Transactional
    void findAllTest() {
        // given
        int studyGroupId = 1;

        // when
        List<Map<String, Object>> list = groupContactMapper.findAllGroupContactList(studyGroupId);

        // then
        log.info("가입 신청 리스트 : {}", list);
        assertThat(list).isNotNull();
    }

    @Test
    @Transactional
    void approveTest() {
        // given
        int id = 30;
        int studyGroupId = 1;

        // when
        groupContactMapper.approveGroupContact(id, studyGroupId);

        // then
        log.info("가입 승인");
    }

    @Test
    @Transactional
    void refuseTest() {
        // given
        int id = 30;
        int studyGroupId = 1;

        // when
        groupContactMapper.refuseGroupContact(id, studyGroupId);

        // then
        log.info("가입 거절");
    }

    @Test
    @Transactional
    void deleteTest() {
        // given
        int id = 30;
        int studyGroupId = 1;

        // when
        groupContactMapper.deleteContact(id, studyGroupId);

        // then
        log.info("가입 신청 삭제");
    }

    @Test
    @Transactional
    void isAlreadyContactTest() {
        // given
        int id = 30;
        int studyGroupId = 1;

        // when
        GroupContact groupContact = groupContactMapper.isAlreadyContact(id, studyGroupId);

        // then
        log.info("스터디 가입 신청 내역 존재 여부 확인 : {}", groupContact);
        assertThat(groupContact).isNull();
    }
}