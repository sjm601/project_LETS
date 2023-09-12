package com.vj.lets.domain.group.mapper;//import static org.junit.jupiter.api.Assertions.*;

import com.vj.lets.domain.group.dto.GroupMemberList;
import com.vj.lets.domain.group.dto.StudyGroup;
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
class GroupMemberListMapperTest {

    @Autowired
    private GroupMemberListMapper groupMemberListMapper;
    @Autowired
    private StudyGroupMapper studyGroupMapper;

    @Test
    @Transactional
    @Disabled
    void createTest() {
        // given
        int id = 30;

        StudyGroup studyGroup = StudyGroup.builder()
                .id(1)
                .name("생성 테스트")
                .totalCount(10)
                .subject("주제")
                .siGunGuId(10020)
                .build();
        studyGroupMapper.create(studyGroup);

        // when
        groupMemberListMapper.create(id);

        // then
        log.info("스터디 그룹 멤버 리스트 생성");
    }

    @Test
    @Transactional
    @Disabled
    void findByAllTest() {
        // given
        int studyGroupId = 1;

        // when
        List<GroupMemberList> list = groupMemberListMapper.findByAll(studyGroupId);

        // then
        log.info("스터디 멤버 리스트 : {}", list);
        assertThat(list).isNotNull();
    }

    @Test
    @Transactional
    @Disabled
    void addMemberTest() {
        // given
        int id = 30;
        int studyGroupId = 1;

        // when
        groupMemberListMapper.addMember(id, studyGroupId);

        // then
        log.info("스터디 멤버 추가");
    }

    @Test
    @Transactional
    @Disabled
    void removeMemberTest() {
        // given
        int id = 30;
        int studyGroupId = 1;

        // when
        groupMemberListMapper.removeMember(id, studyGroupId);

        // then
        log.info("스터디 멤버 삭제");
    }
}