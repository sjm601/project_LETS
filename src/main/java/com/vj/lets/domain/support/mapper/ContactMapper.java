package com.vj.lets.domain.support.mapper;

import com.vj.lets.domain.common.web.PageParams;
import com.vj.lets.domain.support.dto.Contact;
import com.vj.lets.domain.support.dto.ContactForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 입점 신청 관련 매퍼 인터페이스
 *
 * @author VJ특공대 김종원
 * @version 1.0
 * @since 2023-09-10 (일)
 */
@Mapper
public interface ContactMapper {

    /**
     * 입점 신청 생성
     *
     * @param contact 입점 신청 정보
     */
    public void create(Contact contact);

    /**
     * 상태 별 전체 입점 신청 갯수 조회
     *
     * @return 입점 신청 갯수
     */
    public int readCountAll(String type);

    /**
     * 전체 입점 신청 목록 조회
     *
     * @param pageParams 페이징 정보
     * @return 입점 신청 목록
     */
    public List<Contact> readAll(PageParams pageParams);

    /**
     * 입점 신청 ID로 입점 신청 정보 조회
     *
     * @param id 입점 신청 ID
     * @return 입점 신청 정보
     */
    public Contact readById(int id);

    /**
     * 이메일, 사업자 번호, 신청 날짜로 입점 신청 조회
     *
     * @param contactForm 입점 신청 입력 폼
     * @return 입점 신청 정보
     */
    public List<Contact> readByMailBNumDate(ContactForm contactForm);

    /**
     * 입점 신청 상태 정보 수정
     *
     * @param id     입점 신청 ID
     * @param status 상태 정보
     */
    public void update(@Param("id") int id, @Param("status") String status);

}
