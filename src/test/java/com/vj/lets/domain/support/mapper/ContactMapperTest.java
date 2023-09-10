package com.vj.lets.domain.support.mapper;

import com.vj.lets.domain.support.dto.Contact;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 입점 신청 관련 매퍼 테스트 클래스
 *
 * @author VJ특공대 김종원
 * @version 1.0
 * @since 2023-09-10 (일)
 */
@SpringBootTest
@Slf4j
class ContactMapperTest {

    @Autowired
    private ContactMapper contactMapper;

    @Test
    @Transactional
    void createTest() {
        // given
        Contact contact = Contact.builder()
                .email("contact1@gmail.com")
                .name("신청자1")
                .phoneNumber("010-8888-8888")
                .address("서울시 노원구")
                .message("입점 부탁드립니다.")
                .businessNumber(1112233333)
                .build();
        // when
        contactMapper.create(contact);
        // then
        assertThat(contact).isNotNull();
    }

    @Test
    void readAllTest() {
        // given
        // when
        List<Contact> list = contactMapper.readAll();
        // then
        assertThat(list).isNotNull();
    }

    @Test
    void readByMailBNumDateTest() {
        // given
        Contact searchContact = Contact.builder()
                .email("contact1@gmail.com")
                .businessNumber(1112233333)
                .build();
        // when
        Contact contact = contactMapper.readByMailBNumDate(searchContact);
        // then
        assertThat(contact).isNotNull();
    }

    @Test
    @Transactional
    void updateTest() {
        // given
        int id = 1;
        String status = "approve";
        // when
        contactMapper.update(id, status);
        // then
        assertThat(id).isNotZero();
    }
}