package com.vj.lets.domain.support.service;

import com.vj.lets.domain.cafe.dto.Cafe;
import com.vj.lets.domain.common.web.PageParams;
import com.vj.lets.domain.member.dto.Member;
import com.vj.lets.domain.member.util.DefaultPassword;
import com.vj.lets.domain.member.util.MemberType;
import com.vj.lets.domain.support.dto.Contact;
import com.vj.lets.domain.support.dto.ContactForm;
import com.vj.lets.domain.support.util.ContactStatus;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 입점 신청 관련 서비스 테스트 클래스
 *
 * @author VJ특공대 김종원
 * @version 1.0
 * @since 2023-09-10 (일)
 */
@SpringBootTest
@Slf4j
class ContactServiceTest {

    @Autowired
    private ContactService contactService;

    @Test
    @Transactional
    void registerTest() {
        // given
        Contact contact = Contact.builder()
                .email("contact2@gmail.com")
                .name("신청자2")
                .cafeName("테스트 카페")
                .phoneNumber("010-8888-8888")
                .address("서울시 노원구")
                .message("입점 부탁드립니다.")
                .businessNumber(1112233334)
                .build();
        // when
        contactService.register(contact);
        // then
        log.info("신규 입점 신청 정보 : {}", contact);
        assertThat(contact).isNotNull();
    }

    @Test
    void getCountContactTest() {
        // given
        String type = ContactStatus.HOLD.getStatus();
        // when
        int count = contactService.getCountContact(type);
        // then
        log.info("입점 신청 수 : {}", count);
        assertThat(count).isNotZero();
    }

    @Test
    void getContactListTest() {
        // given
        PageParams pageParams = PageParams.builder()
                .elementSize(5)
                .pageSize(5)
                .requestPage(1)
                .build();
        // when
        List<Contact> list = contactService.getContactList(pageParams);
        // then
        log.info("입점 신청 목록 : {}", list);
        assertThat(list).isNotNull();
    }

    @Test
    void getContactTest() {
        // given
        int id = 1;
        // when
        Contact contact = contactService.getContact(id);
        // then
        log.info("입점 신청 정보 : {}", contact);
        assertThat(contact).isNotNull();
    }

    @Test
    void checkContactTest() {
        // given
        ContactForm checkContact = ContactForm.builder()
                .email("contact2@gmail.com")
                .businessNumber(1112233334)
                .build();
        // when
        List<Contact> contact = contactService.checkContact(checkContact);
        // then
        log.info("FAQ 목록 : {}", contact);
        assertThat(contact).isNotNull();
    }

    @Test
    @Transactional
    void approveContactTest() {
        // given
        int id = 1;
        Member member = Member.builder()
                .email("contact2@naver.com")
                .name("신청자")
                .password(DefaultPassword.DEFAULT.getPassword())
                .type(MemberType.HOST.getType())
                .build();
        Cafe cafe = Cafe.builder()
                .email("contact2@naver.com")
                .name("테스트 카페")
                .businessNumber(1112233334)
                .build();
        // when
        contactService.approveContact(id, member, cafe);
        // then
        log.info("승인 입점 신청 : {}", id);
        assertThat(id).isNotZero();
    }

    @Test
    @Transactional
    void refuseContactTest() {
        // given
        int id = 1;
        // when
        contactService.refuseContact(id);
        // then
        log.info("거부 입점 신청 : {}", id);
        assertThat(id).isNotZero();
    }
}