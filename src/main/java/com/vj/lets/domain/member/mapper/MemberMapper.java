package com.vj.lets.domain.member.mapper;

import com.vj.lets.domain.member.dto.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 회원 관련 매퍼 인터페이스
 *
 * @author VJ특공대 김종원
 * @version 1.0
 * @since 2023-09-08 (금)
 */
@Mapper
public interface MemberMapper {

    /**
     * 회원 생성
     *
     * @param member 멤버 정보
     */
    public void create(Member member);

    /**
     * 전체 회원 목혹 조회
     *
     * @return 회원 목록
     */
    public List<Member> readAll();

    /**
     * 이메일, 비밀번호로 회원 조회
     *
     * @param email    이메일
     * @param password 비밀번호
     * @return 회원 정보
     */
    public Member readByEmailAndPasswd(@Param("email") String email, @Param("password") String password);

    /**
     * ID로 회원 조회
     *
     * @param id 회원 ID
     * @return 회원 정보
     */
    public Member readById(int id);

    /**
     * 회원 정보 수정
     *
     * @param member 회원 정보
     */
    public void update(Member member);

    /**
     * 회원 비활성화
     *
     * @param id 회원 ID
     */
    public void disabled(int id);

}







