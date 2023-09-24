package com.vj.lets.domain.room.service;

import com.vj.lets.domain.room.dto.Room;

import java.util.List;

/**
 * 카페 룸 관련 비즈니스 로직 처리 및 트랜잭션 관리
 *
 * @author 강소영
 * @version 1.0
 * @since 2023-09-11 (월)
 */
public interface RoomService {

    /**
     * 룸 등록
     *
     * @param room 룸 정보
     */
    public void register(Room room);

    /**
     * 룸 정보 업데이트
     *
     * @param room 룸 정보
     */
    public void editRoom(Room room);

    /**
     * 룸 전체 목록 출력
     *
     * @return 룸 전체 목록
     */
    public List<Room> getRoomList();

    /**
     * 카페 ID로 룸 목록 검색
     *
     * @param id 카페 ID
     * @return 룸 목록
     */
    public List<Room> getSearchCafeRoom(int id);

    /**
     * 룸 ID로 검색
     *
     * @param id 룸 ID
     * @return 검색된 룸 정보
     */
    public Room getSearchRoom(int id);


    /**
     * 룸 삭제
     *
     * @param id 룸 ID
     */
    public void deleteRoom(int id);
}
