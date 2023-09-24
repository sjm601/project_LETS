package com.vj.lets.domain.room.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * 카페 룸 히스토리 관련 매퍼 명세
 *
 * @author VJ특공대 강소영
 * @version 1.0
 * @since 2023-09-11 (월)
 */
@Mapper
public interface RoomHistoryMapper {

    /**
     * 룸 생성
     */
    public void create();

    /**
     * 룸 업데이트
     *
     * @param roomId 룸 ID
     */
    public void update(int roomId);

    /**
     * 룸 삭제
     *
     * @param roomId 룸 ID
     */
    public void delete(int roomId);
}
