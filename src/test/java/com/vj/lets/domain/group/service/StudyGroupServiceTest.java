package com.vj.lets.domain.group.service;//import static org.junit.jupiter.api.Assertions.*;

import com.vj.lets.domain.group.dto.GroupContact;
import com.vj.lets.domain.group.dto.GroupMemberList;
import com.vj.lets.domain.group.dto.Search;
import com.vj.lets.domain.group.dto.StudyGroup;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 스터디 그룹 서비스 테스트
 *
 * @author VJ특공대 이희영
 * @version 1.0
 * @since 2023-09-11 (월)
 */
@SpringBootTest
@Slf4j
class StudyGroupServiceTest {

    @Autowired
    private StudyGroupService studyGroupService;


    @Test
    @Transactional
    void createStudyGroupTest() {
        // given
        String siGunGuName = "노원구";

        StudyGroup studyGroup = StudyGroup.builder()
                .name("스터디이름")
                .totalCount(10)
                .subject("스터디주제")
                .build();

        int id = 30;

        // when
        int studyGroupId = studyGroupService.createStudyGroup(studyGroup, id, siGunGuName);

        // then
        log.info("생성한 스터디 그룹 아이디 : {}", studyGroupId);
    }

    @Test
    @Transactional
    void getStudyGroupListTest() {
        // given
        Search search = Search.builder().build();

        // when
        List<Map<String, Object>> list = studyGroupService.getStudyGroupList(search);

        // then
        log.info("스터디 그룹 리스트 : {}", list);
        assertThat(list).isNotNull();
    }

    @Test
    @Transactional
    void viewStudyGroupTest() {
        // given
        int studyGroupId = 1;

        // when
        Map<String, Object> studyGroup = studyGroupService.viewStudyGroup(studyGroupId);

        // then
        log.info("스터디 그룹 조회 : {}", studyGroup);
        assertThat(studyGroup).isNotNull();
    }

    @Test
    @Transactional
    void editStudyGroupTest() {
        // given
        StudyGroup studyGroup = StudyGroup.builder()
                .id(1)
                .name("이름수정")
                .build();

        // when
        studyGroupService.editStudyGroup(studyGroup);

        // then
        log.info("스터디 그룹 정보 수정 : {}", studyGroup);
    }

    @Test
    @Transactional
    void deleteStudyGroupTest() {
        // given
        int studyGroupId = 1;

        // when
        studyGroupService.deleteStudyGroup(studyGroupId);

        // then
        log.info("스터디 그룹 삭제");
    }

    @Test
    @Transactional
    void findByAllMemberTest() {
        // given
        int StudyGroupId = 1;

        // when
        List<GroupMemberList> list = studyGroupService.findByAllMember(StudyGroupId);

        // then
        log.info("스터디 그룹 회원 리스트 조회 : {}", list);
        assertThat(list).isNotNull();
    }

    @Test
    @Transactional
    void addMemberTest() {
        // given
        int id = 30;
        int studyGroupId = 1;

        // when
        studyGroupService.addMember(id, studyGroupId);

        // then
        log.info("스터디 그룹 회원 추가");
    }

    @Test
    @Transactional
    void removeMemberTest() {
        // given
        int id = 30;
        int studyGroupId = 1;

        // when
        studyGroupService.removeMember(id, studyGroupId);

        // then
        log.info("스터디 그룹 회원 제거");
    }

    @Test
    @Transactional
    void registerStudyTest() {
        // given
        int id = 30;
        int studyGroupId = 1;

        // when
        studyGroupService.registerStudy(id, studyGroupId);

        // then
        log.info("스터디 가입 신청");
    }

    @Test
    @Transactional
    void findByAllRegistTest() {
        // given
        int studyGroupId = 1;

        // when
        List<GroupContact> list = studyGroupService.findByAllRegist(studyGroupId);

        // then
        log.info("스터디 가입 신청 회원 리스트 : {}", list);
        assertThat(list).isNotNull();
    }

    @Test
    @Transactional
    void approveTest() {
        // given
        int id = 30;
        int studyGroupId = 1;

        // when
        studyGroupService.approve(id, studyGroupId);

        // then
        log.info("스터디 가입 승인");
    }

    @Test
    @Transactional
    void refuseTest() {
        // given
        int id = 30;
        int studyGroupId = 1;

        // when
        studyGroupService.refuse(id, studyGroupId);

        // then
        log.info("스터디 가입 승인");
    }

    @Test
    @Transactional
    void isGroupMemberTest() {
        // given
        int id = 32;
        int studyGroupId = 1;

        // when
        GroupMemberList member = studyGroupService.isGroupMember(id, studyGroupId);

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
        List<Map<String, Object>> list = studyGroupService.myGroupList(id);

        // then
        log.info("가입한 스터디 그룹 리스트 조회 : {}", list);
        assertThat(list).isNotNull();
    }
}