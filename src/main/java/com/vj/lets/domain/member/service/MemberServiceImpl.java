package com.vj.lets.domain.member.service;

import com.vj.lets.domain.cafe.mapper.CafeHistoryMapper;
import com.vj.lets.domain.cafe.mapper.CafeMapper;
import com.vj.lets.domain.group.mapper.GroupContactMapper;
import com.vj.lets.domain.group.mapper.GroupHistoryMapper;
import com.vj.lets.domain.group.mapper.GroupMemberListMapper;
import com.vj.lets.domain.group.mapper.StudyGroupMapper;
import com.vj.lets.domain.member.dto.Member;
import com.vj.lets.domain.member.mapper.MemberHistoryMapper;
import com.vj.lets.domain.member.mapper.MemberMapper;
import com.vj.lets.domain.member.util.MemberHistoryComment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    private final CafeMapper cafeMapper;
    private final CafeHistoryMapper cafeHistoryMapper;
    private final StudyGroupMapper studyGroupMapper;
    private final GroupHistoryMapper groupHistoryMapper;
    private final GroupMemberListMapper groupMemberListMapper;
    private final GroupContactMapper groupContactMapper;

    /**
     * 회원 가입
     *
     * @param member 회원 정보
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
     * @return 회원 정보
     */
    @Override
    public Member isMember(String email, String password) {
        return memberMapper.readByEmailAndPasswd(email, password);
    }

    /**
     * 전체 회원 리스트 조회
     *
     * @return 회원 리스트
     */
    @Override
    public List<Member> getMemberList() {
        return memberMapper.readAll();
    }

    /**
     * 특정 회원 정보 조회
     *
     * @param id 회원 아이디
     * @return 회원 정보
     */
    @Override
    public Member getMember(int id) {
        return memberMapper.readById(id);
    }

    /**
     * 회원 정보 수정
     *
     * @param member 회원 정보
     */
    @Override
    @Transactional
    public void editMember(Member member) {
        memberMapper.update(member);
        memberHistoryMapper.createByUpdate(member.getId(), MemberHistoryComment.UPDATE.getComment());
    }

    /**
     * 회원 탈퇴
     *
     * @param id 회원 ID
     */
    @Override
    @Transactional
    public void removeMember(int id) {
        Member member = memberMapper.readById(id);
        String memberType = member.getType();

        if (memberType.equals("host")) {
            // 멤버 ID로 카페 찾기
//            cafeMapper.delete(cafeId);
//            cafeHistoryMapper.delete(cafeId);
        } else if (memberType.equals("guest")) {
//             멤버 아이디로 스터디 그룹 전체 찾기
//             if (포지션 = 팀장) {
//             groupMemberListMapper.removeMember(studygroupid);
//            studyGroupMapper.delete(studygroupid);
//            groupHistoryMapper.delete(studygroupid);
//             } else if ( 포지션 = 팀원) {
//            groupMemberListMapper.removeMember(id, studygroupid);
//            studyGroupMapper.decrease(studygroupid);
//            groupHistoryMapper.update(studygroupid);
//             }
        }

        memberMapper.disabled(id);
        memberHistoryMapper.createByUpdate(id, MemberHistoryComment.DELETE.getComment());

    }
}
