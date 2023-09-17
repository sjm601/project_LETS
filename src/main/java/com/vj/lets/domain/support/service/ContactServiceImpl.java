package com.vj.lets.domain.support.service;

import com.vj.lets.domain.cafe.dto.Cafe;
import com.vj.lets.domain.cafe.mapper.CafeHistoryMapper;
import com.vj.lets.domain.cafe.mapper.CafeMapper;
import com.vj.lets.domain.member.dto.Member;
import com.vj.lets.domain.member.mapper.MemberHistoryMapper;
import com.vj.lets.domain.member.mapper.MemberMapper;
import com.vj.lets.domain.support.dto.Contact;
import com.vj.lets.domain.support.dto.ContactForm;
import com.vj.lets.domain.support.mapper.ContactMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 입점 신청 관련 비즈니스 로직 처리 및 트랜잭션 관리 서비스 구현체
 *
 * @author VJ특공대 김종원
 * @version 1.0
 * @since 2023-09-10 (일)
 */
@RequiredArgsConstructor
@Service
public class ContactServiceImpl implements ContactService {

    private final MemberMapper memberMapper;
    private final MemberHistoryMapper memberHistoryMapper;
    private final CafeMapper cafeMapper;
    private final CafeHistoryMapper cafeHistoryMapper;
    private final ContactMapper contactMapper;

    /**
     * 입점 신청 등록
     *
     * @param contact 입점 신청 정보
     */
    @Override
    @Transactional
    public void register(Contact contact) {
        contactMapper.create(contact);
    }

    /**
     * 전체 입점 신청 목록 조회
     *
     * @return 입점 신청 목록
     */
    @Override
    public List<Contact> getContactList() {
        return contactMapper.readAll();
    }

    /**
     * 입점 신청 시 이메일, 사업자 번호, 신청 날짜로 중복 신청 조회
     *
     * @param contactForm 입점 신청 입력 폼
     * @return 입점 신청 정보
     */
    @Override
    public List<Contact> checkContact(ContactForm contactForm) {
        return contactMapper.readByMailBNumDate(contactForm);
    }

    /**
     * 입점 승인 시 회원 등록, 카페 개설, 입점 신청 상태 정보 수정
     *
     * @param id 입점 신청 ID
     */
    @Override
    @Transactional
    public void approveContact(int id, Member member, Cafe cafe) {
        memberMapper.create(member);
        memberHistoryMapper.create();
        cafeMapper.create(cafe);
        cafeHistoryMapper.create();
        contactMapper.update(id, "approve");
    }

    /**
     * 입점 거절 시 상태 정보 수정
     *
     * @param id 입점 신청 ID
     */
    @Override
    @Transactional
    public void refuseContact(int id) {
        contactMapper.update(id, "refuse");
    }
}
