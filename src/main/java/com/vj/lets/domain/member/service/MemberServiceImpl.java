package com.vj.lets.domain.member.service;

import java.util.List;

import com.vj.lets.domain.member.dto.Member;
import com.vj.lets.domain.member.mapper.MemberHistoryMapper;
import com.vj.lets.domain.member.mapper.MemberMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import lombok.RequiredArgsConstructor;

/**
 * 회원 관련 비즈니스 로직 처리 및 트랜잭션 관리 서비스 구현체
 *
 * @author VJ특공대 김종원
 * @version 1.0
 * @since 2023-09-08 (금)
 */
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;
    private final MemberHistoryMapper memberHistoryMapper;

    /**
     * 회원 가입
     *
     * @param member 회원 객체
     */
    @Override
    @Transactional
    public void register(Member member) {
        memberMapper.create(member);
        memberHistoryMapper.create();
    }

    /**
     * 로그인 시 회원 조회
     *
     * @param email    이메일
     * @param password 비밀번호
     * @return 회원 객체
     */
    @Override
    public Member isMember(String email, String password) {
        return memberMapper.findByEmailAndPasswd(email, password);
    }

    /**
     * 전체 회원 리스트 조회
     *
     * @return 회원 리스트
     */
    @Override
    public List<Member> getMemberList() {
        return memberMapper.findByAll();
    }

    /**
     * 특정 회원 정보 조회
     *
     * @param id 회원 아이디
     * @return 회원 객체
     */
    @Override
    public Member getMember(int id) {
        return memberMapper.findById(id);
    }

    /**
     * 회원 정보 수정
     *
     * @param member 회원 객체
     */
    @Override
    @Transactional
    public void editMember(Member member) {
        memberMapper.update(member);
        memberHistoryMapper.update(member.getId());
    }

    /**
     * 회원 탈퇴
     *
     * @param id 회원 ID
     */
    @Override
    @Transactional
    public void deleteMember(int id) {
        memberMapper.delete(id);
        memberHistoryMapper.delete(id);
    }
}
