package com.vj.lets.domain.group.mapper;

import com.vj.lets.domain.group.dto.GroupContact;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 그룹 신청 매퍼
 * 
 * @author VJ특공대 이희영
 * @version 1.0
 * @since 2023-09-11 (월)
 */
@Mapper
public interface GroupContactMapper {

    /**
     * 스터디 그룹 가입 신청
     *
     * @param id 회원 아이디
     * @param studyGroupId 스터디 그룹 아이디
     */
    public void register(@Param("id") int id, @Param("studyGroupId") int studyGroupId);

    /**
     * 스터디 그룹 가입 신청 리스트 조회
     * 
     * @return 조회된 가입 신청 리스트
     */
    public List<GroupContact> findAll(int studyGroupId);

    /**
     * 스터디 그룹 가입 승인
     *
     * @param id 회원 아이디
     * @param studyGroupId 스터디 그룹 아이디
     */
    public void approve(@Param("id") int id, @Param("studyGroupId") int studyGroupId);

    /**
     * 스터디 그룹 가입 거절
     *
     * @param id 회원 아이디
     * @param studyGroupId 스터디 그룹 아이디
     */
    public void refuse(@Param("id") int id, @Param("studyGroupId") int studyGroupId);

    /**
     * 스터디 그룹 가입 신청 내역 삭제
     *
     * @param id 회원 아이디
     * @param studyGroupId 스터디 그룹 아이디
     */
    public void delete(@Param("id") int id, @Param("studyGroupId") int studyGroupId);
}