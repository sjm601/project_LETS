package com.vj.lets.domain.support.service;

import com.vj.lets.domain.support.dto.Contact;
import com.vj.lets.domain.support.dto.ContactForm;
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
                .phoneNumber("010-8888-8888")
                .address("서울시 노원구")
                .message("입점 부탁드립니다.")
                .businessNumber(1112233334)
                .build();
        // when
        contactService.register(contact);
        // then
        assertThat(contact).isNotNull();
    }

    @Test
    void getContactListTest() {
        // given
        // when
        List<Contact> list = contactService.getContactList();
        // then
        assertThat(list).isNotNull();
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
        assertThat(contact).isNotNull();
    }

    @Test
    @Transactional
    void editContactApproveTest() {
        // given
        int id = 1;
        // when
        contactService.editContactApprove(id);
        // then
        assertThat(id).isNotZero();
    }

    @Test
    @Transactional
    void editContactRefuseTest() {
        // given
        int id = 1;
        // when
        contactService.editContactRefuse(id);
        // then
        assertThat(id).isNotZero();
    }
}