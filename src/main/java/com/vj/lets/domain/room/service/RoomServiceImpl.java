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

    @Override
    @Transactional
    public void register(Room room) {
        roomMapper.create(room);
        roomHistoryMapper.create();
    }

    @Override
    @Transactional
    public void editRoom(Room room) {
        roomMapper.update(room);
        roomHistoryMapper.update(room.getId());
    }

    @Override
    public List<Room> getRoomList() {
        return roomMapper.findByAll();
    }

    @Override
    public List<Room> getSearchCafeRoom(int id) {
        return roomMapper.findByCafe(id);
    }

    @Override
    public Room getSearchRoom(int id) {
        return roomMapper.findById(id);
    }



    @Override
    @Transactional
    public void deleteRoom(int id) {
        roomMapper.delete(id);
        roomHistoryMapper.delete(id);
    }
}
