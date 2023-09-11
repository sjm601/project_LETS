package com.vj.lets.domain.room.mapper;

import com.vj.lets.domain.room.dto.Room;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Room 테이블 관련 명세
 *
 * @author VJ특공대 강소영
 * @version 1.0
 * @since 2023-09-11 (월)
 */
@Mapper
public interface RoomMapper {

    /** 룸 생성 */
    public void create(Room room);

    /** 룸 정보 업데이트 */
    public Room update(Room room);

    /** 룸 전체 조회 */
    public List<Room> findByAll();

    /** 카페 id로 룸 조회 */
    public List<Room> findByCafe(int id);

    /** 룸 id로 룸 조회 */
    public Room findById(int id);

    /** 룸 삭제 */
    public void delete(int id);
}
