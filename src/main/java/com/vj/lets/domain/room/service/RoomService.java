package com.vj.lets.domain.room.service;

import com.vj.lets.domain.room.dto.Room;

import java.util.List;

/**
 * Room 관련 비즈니스 로직 처리 및 트랜잭션 관리
 *
 * @author 강소영
 * @version 1.0
 * @since 2023-09-11 (월)
 */
public interface RoomService {

    /** 룸 등록 */
    public void register(Room room);

    /** 룸 정보 업데이트 */
    public void editRoom(Room room);

    /** 룸 전체 리스트 출력 */
    public List<Room> getRoomList();

    /** 카페 id로 룸 리스트 검색 */
    public List<Room> getSearchCafeRoom(int id);

    /** 룸 id로 검색 */
    public Room getSearchRoom(int id);


    /** 룸 삭제 */
    public void deleteRoom(int id);
}
