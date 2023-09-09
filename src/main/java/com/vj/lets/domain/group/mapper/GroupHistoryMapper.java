package com.vj.lets.domain.group.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * 스터디 그룹 히스토리 매퍼
 * 
 * @author 이희영
 * @version 1.0
 * @since 2023-09-08 (금)
 */
@Mapper
public interface GroupHistoryMapper {

    /**
     * 스터디 그룹 히스토리 생성
     */
    public void create();

    /**
     * 스터디 그룹 수정
     * @param studyGroupId
     */
    public void update(int studyGroupId);

    /**
     * 스터디 그룹 삭제
     * @param studyGroupId
     */
    public void delete(int studyGroupId);
}