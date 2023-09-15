package com.vj.lets.domain.group.mapper;

import com.vj.lets.domain.group.dto.GroupMemberList;
import com.vj.lets.domain.group.dto.StudyGroup;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 스터디 멤버 리스트 매퍼
 *
 * @author VJ특공대 이희영
 * @version 1.0
 * @since 2023-09-08 (금)
 */
@SpringBootTest
@Slf4j
class GroupMemberListMapperTest {

    @Autowired
    private GroupMemberListMapper groupMemberListMapper;
    @Autowired
    private StudyGroupMapper studyGroupMapper;

    @Test
    @Transactional
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
    void findAllTest() {
        // given
        int studyGroupId = 1;

        // when
        List<Map<String,Object>> list = groupMemberListMapper.findAll(studyGroupId);

        // then
        log.info("스터디 멤버 리스트 : {}", list);
        assertThat(list).isNotNull();
    }

    @Test
    @Transactional
    void addTest() {
        // given
        int id = 30;
        int studyGroupId = 1;

        // when
        groupMemberListMapper.add(id, studyGroupId);

        // then
        log.info("스터디 멤버 추가");
    }

    @Test
    @Transactional
    void removeMemberTest() {
        // given
        int id = 30;
        int studyGroupId = 1;

        // when
        groupMemberListMapper.remove(id, studyGroupId);

        // then
        log.info("스터디 멤버 삭제");
    }

    @Test
    @Transactional
    void isGroupMemberTest() {
        // given
        int id = 32;
        int studyGroupId = 1;

        // when
        GroupMemberList member = groupMemberListMapper.isGroupMember(id, studyGroupId);

        // then
        log.info("스터디 그룹에 가입된 회원인지 조회 : {}", member);
        assertThat(member).isNotNull();
    }

    @Test
    @Transactional
    void myGroupListTest() {
        // given
        int id = 32;

        // when
        List<Map<String, Object>> list = groupMemberListMapper.myGroupList(id);

        // then
        log.info("가입된 스터디 그룹 리스트 : {}", list);
        assertThat(list).isNotNull();
    }
}