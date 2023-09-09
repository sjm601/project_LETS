package com.vj.lets.domain.member.mapper;

import com.vj.lets.domain.member.dto.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * MemberHistory 매퍼 테스트 클래스
 *
 * @author 김종원
 * @version 1.0
 * @since 2023-09-08 (금)
 */
@SpringBootTest
@Slf4j
class MemberMapperTest {

    @Autowired
    private MemberMapper memberMapper;

    @Test
    @Transactional
    void createTest() {
        // given
        Member member = Member.builder()
                .email("aaa@aaa.aaa")
                .name("가가가")
                .password("1111")
                .build();
        // when
        memberMapper.create(member);
        // then
        log.info("가입 회원 정보 : {}", member);
        assertThat(member).isNotNull();
    }

    @Test
    void findByEmailAndPasswdTest() {
        // given
        String email = "lhy@gmail.com";
        String password = "1111";
        // when
        Member member = memberMapper.findByEmailAndPasswd(email, password);
        // then
        log.info("회원 정보 : {}", member);
        assertThat(member).isNotNull();
    }

    @Test
    void findByAllTest() {
        // given
        // when
        List<Member> list = memberMapper.findByAll();
        // then
        log.info("회원 리스트 : {}", list);
        assertThat(list).isNotNull();
    }

    @Test
    void findByIdTest() {
        // given
        int id = 1;
        // when
        Member member = memberMapper.findById(id);
        // then
        log.info("회원 정보 : {}", member);
        assertThat(member).isNotNull();
    }

    @Test
    @Transactional
    void updateTest() {
        // given
        Member member = Member.builder()
                .id(1)
                .name("이이이")
                .password("1234")
                .imagePath("aaa.jpg")
                .build();
        // when
        memberMapper.update(member);
        // then
        log.info("수정 회원 정보 : {}", member);
        assertThat(member).isNotNull();
    }

    @Test
    @Transactional
    void deleteTest() {
        // given
        int id = 1;
        // when
        memberMapper.delete(id);
        // then
        log.info("삭제된 회원 아이디 : {}", id);
        assertThat(id).isNotZero();
    }
}