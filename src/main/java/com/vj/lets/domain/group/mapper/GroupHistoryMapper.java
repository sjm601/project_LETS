package com.vj.lets.domain.group.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * 스터디 그룹 히스토리 매퍼
 * 
 * @author VJ특공대 이희영
 * @version 1.0
 * @since 2023-09-08 (금)
 */
@Mapper
public interface GroupHistoryMapper {

    /**
     * 스터디 그룹 히스토리 생성
     *
     * @author VJ특공대 이희영
     */
    public void createGroupHistory();

    /**
     * 스터디 그룹 변경 시  히스토리 업데이트
     *
     * @author VJ특공대 이희영
     * @param studyGroupId 스터디 그룹 아이디
     */
    public void updateGroupHistory(int studyGroupId);

    /**
     * 스터디 그룹 삭제 시 히스토리 업데이트
     *
     * @author VJ특공대 이희영
     * @param studyGroupId 스터디 그룹 아이디
     */
    public void deleteGroupHistory(int studyGroupId);
}