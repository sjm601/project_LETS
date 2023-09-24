package com.vj.lets.domain.room.mapper;

import com.vj.lets.domain.room.dto.Room;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 카페 룸 관련 매퍼 명세
 *
 * @author VJ특공대 강소영
 * @version 1.0
 * @since 2023-09-11 (월)
 */
@Mapper
public interface RoomMapper {

    /**
     * 룸 생성
     *
     * @param room 룸 정보
     */
    public void create(Room room);

    /**
     * 룸 정보 업데이트
     *
     * @param room 룸 정보
     */
    public void update(Room room);

    /**
     * 룸 전체 조회
     *
     * @return 룸 목록
     */
    public List<Room> findByAll();

    /**
     * 카페 ID로 룸 조회
     *
     * @param id 카페 ID
     * @return 룸 목록
     */
    public List<Room> findByCafe(int id);

    /**
     * 룸 ID로 룸 조회
     *
     * @param id 룸 ID
     * @return 룸 정보
     */
    public Room findById(int id);


    /**
     * 룸 삭제
     *
     * @param id 룸 ID
     */
    public void delete(int id);
}
