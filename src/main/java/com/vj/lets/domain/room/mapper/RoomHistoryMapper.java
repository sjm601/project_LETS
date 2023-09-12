package com.vj.lets.domain.room.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * Room History 테이블 관련 명세
 *
 * @author VJ특공대 강소영
 * @version 1.0
 * @since 2023-09-11 (월)
 */
@Mapper
public interface RoomHistoryMapper {

    /** 룸 생성 */
    public void create();

    /** 룸 업데이트 */
    public void update(int roomId);

    /** 룸 삭제 */
    public void delete(int roomId);
}
