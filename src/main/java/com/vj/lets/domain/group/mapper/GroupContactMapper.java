package com.vj.lets.domain.group.mapper;

import com.vj.lets.domain.group.dto.GroupContact;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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
     * @param id           회원 ID
     * @param studyGroupId 스터디 그룹 ID
     * @author VJ특공대 이희영
     */
    public void contactGroup(@Param("id") int id, @Param("studyGroupId") int studyGroupId);

    /**
     * 스터디 그룹 가입 신청 목록 조회
     *
     * @return 조회된 가입 신청 목록
     * @author VJ특공대 이희영
     */
    public List<Map<String, Object>> findAllGroupContactList(int studyGroupId);

    /**
     * 스터디 그룹 가입 승인
     *
     * @param id           회원 ID
     * @param studyGroupId 스터디 그룹 ID
     * @author VJ특공대 이희영
     */
    public void approveGroupContact(@Param("id") int id, @Param("studyGroupId") int studyGroupId);

    /**
     * 스터디 그룹 가입 거절
     *
     * @param id           회원 ID
     * @param studyGroupId 스터디 그룹 ID
     * @author VJ특공대 이희영
     */
    public void refuseGroupContact(@Param("id") int id, @Param("studyGroupId") int studyGroupId);

    /**
     * 스터디 그룹 가입 신청 내역 삭제
     *
     * @param id           회원 ID
     * @param studyGroupId 스터디 그룹 ID
     * @author VJ특공대 이희영
     */
    public void deleteContact(@Param("id") int id, @Param("studyGroupId") int studyGroupId);

    /**
     * 스터디 가입 신청 내역 존재 여부 확인
     *
     * @param id           회원 ID
     * @param studyGroupId 스터디 그룹 ID
     * @author VJ특공대 이희영
     */
    public GroupContact isAlreadyContact(@Param("id") int id, @Param("studyGroupId") int studyGroupId);
}