package com.vj.lets.domain.room.service;

import com.vj.lets.domain.room.dto.Room;
import com.vj.lets.domain.room.mapper.RoomHistoryMapper;
import com.vj.lets.domain.room.mapper.RoomMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Room Service 구현체
 *
 * @author VJ특공대 강소영
 * @version 1.0
 * @since 2023-09-11 (월)
 */
@RequiredArgsConstructor
@Service
public class RoomServiceImpl implements RoomService{

    private final RoomMapper roomMapper;
    private final RoomHistoryMapper roomHistoryMapper;

    /**
     * 룸 등록
     *
     * @param room 룸
     */
    @Override
    @Transactional
    public void register(Room room) {
        roomMapper.create(room);
        roomHistoryMapper.create();
    }

    /**
     * 룸 정보 업데이트
     *
     * @param room 룸
     */
    @Override
    @Transactional
    public void editRoom(Room room) {
        roomMapper.update(room);
        roomHistoryMapper.update(room.getId());
    }

    /**
     * 룸 전체 리스트 출력
     *
     * @return 룸 전체 리스트
     */
    @Override
    public List<Room> getRoomList() {
        return roomMapper.findByAll();
    }

    /**
     * 카페 id로 룸 리스트 검색
     *
     * @param id 카페 ID
     * @return 룸 리스트
     */
    @Override
    public List<Room> getSearchCafeRoom(int id) {
        return roomMapper.findByCafe(id);
    }

    /**
     * 룸 id로 검색
     *
     * @param id 룸 ID
     * @return 검색된 룸
     */
    @Override
    public Room getSearchRoom(int id) {
        return roomMapper.findById(id);
    }

    /**
     * 룸 삭제
     *
     * @param id 룸 ID
     */
    @Override
    @Transactional
    public void deleteRoom(int id) {
        roomMapper.delete(id);
        roomHistoryMapper.delete(id);
    }
}
