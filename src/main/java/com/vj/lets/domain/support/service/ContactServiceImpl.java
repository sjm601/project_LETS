package com.vj.lets.domain.support.service;

import com.vj.lets.domain.support.dto.Contact;
import com.vj.lets.domain.support.mapper.ContactMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    private final ContactMapper contactMapper;

    /**
     * 입점 신청 등록
     *
     * @param contact 입점 신청 정보
     */
    @Override
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
     * @param contact 입점 신청 정보
     * @return 입점 신청 조회 정보
     */
    @Override
    public Contact checkContact(Contact contact) {
        return contactMapper.readByMailBNumDate(contact);
    }

    /**
     * 입점 승인 시 상태 정보 수정
     *
     * @param id 입점 신청 ID
     */
    @Override
    public void editContactApprove(int id) {
        contactMapper.update(id, "approve");
    }

    /**
     * 입점 거절 시 상태 정보 수정
     *
     * @param id 입점 신청 ID
     */
    @Override
    public void editContactRefuse(int id) {
        contactMapper.update(id, "refuse");
    }
}
