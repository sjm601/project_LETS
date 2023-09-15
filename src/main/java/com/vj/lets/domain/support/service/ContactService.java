package com.vj.lets.domain.support.service;

import com.vj.lets.domain.support.dto.Contact;
import com.vj.lets.domain.support.dto.ContactForm;

import java.util.List;

/**
 * 입점 신청 관련 비즈니스 로직 처리 및 트랜잭션 관리 서비스 인터페이스
 *
 * @author VJ특공대 김종원
 * @version 1.0
 * @since 2023-09-10 (일)
 */
public interface ContactService {

    /**
     * 입점 신청 등록
     *
     * @param contact 입점 신청 정보
     */
    public void register(Contact contact);

    /**
     * 전체 입점 신청 목록 조회
     *
     * @return 입점 신청 목록
     */
    public List<Contact> getContactList();

    /**
     * 입점 신청 시 이메일, 사업자 번호, 신청 날짜로 중복 신청 조회
     *
     * @param contactForm 입점 신청 입력 폼
     * @return 입점 신청 정보
     */
    public List<Contact> checkContact(ContactForm contactForm);

    /**
     * 입점 승인 시 상태 정보 수정
     *
     * @param id 입점 신청 ID
     */
    public void editContactApprove(int id);

    /**
     * 입점 거절 시 상태 정보 수정
     *
     * @param id 입점 신청 ID
     */
    public void editContactRefuse(int id);

}
