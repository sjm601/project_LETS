package com.vj.lets.domain.member.service;

import com.vj.lets.domain.member.dto.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 회원 관련 서비스 테스트 클래스
 *
 * @author 김종원
 * @version 1.0
 * @since 2023-09-08 (금)
 */
@SpringBootTest
@Slf4j
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    @Transactional
    void registerTest() {
        // given
        Member member = Member.builder()
                .email("aaa@aaa.aaa")
                .name("가가가")
                .password("1111")
                .build();
        // when
        memberService.register(member);
        // then
        log.info("가입 회원 정보 : {}", member);
        assertThat(member).isNotNull();
    }

    @Test
    void isMemberTest() {
        // given
        String email = "lhy@gmail.com";
        String password = "1111";
        // when
        Member member = memberService.isMember(email, password);
        // then
        log.info("회원 정보 : {}", member);
        assertThat(member).isNotNull();
    }

    @Test
    void getMemberListTest() {
        // given
        // when
        List<Member> list = memberService.getMemberList();
        // then
        log.info("회원 리스트 : {}", list);
        assertThat(list).isNotNull();
    }

    @Test
    void getMemberTest() {
        // given
        int id = 1;
        // when
        Member member = memberService.getMember(id);
        // then
        log.info("회원 정보 : {}", member);
        assertThat(member).isNotNull();
    }

    @Test
    @Transactional
    void editMemberTest() {
        // given
        Member member = Member.builder()
                .id(1)
                .name("이이이")
                .password("1234")
                .imagePath("aaa.jpg")
                .build();
        // when
        memberService.editMember(member);
        // then
        log.info("수정 회원 정보 : {}", member);
        assertThat(member).isNotNull();
    }

    @Test
    @Transactional
    void deleteMemberTest() {
        // given
        int id = 1;
        // when
        memberService.deleteMember(id);
        // then
        log.info("삭제한 회원 아이디 : {}", id);
        assertThat(id).isNotZero();
    }
}