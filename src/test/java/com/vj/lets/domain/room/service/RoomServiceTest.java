package com.vj.lets.domain.room.service;//import static org.junit.jupiter.api.Assertions.*;

import com.vj.lets.domain.room.dto.Room;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Room Service Test
 *
 * @author VJ특공대 강소영
 * @version 1.0
 * @since 2023-09-13 (수)
 */
@SpringBootTest
@Slf4j
class RoomServiceTest {

    @Autowired
    private RoomService roomService;

    @Test
    @DisplayName("룸 등록")
    @Disabled
    @Transactional
    void registerTest() {
        //given
        Room room = Room.builder()
                .name("A1")
                .description("제일 큰 룸")
                .imagePath("/01/룸A1")
                .headCount(5)
                .price(15000)
                .cafeId(5)
                .build();
        //when
        roomService.register(room);
        //then
        log.info("등록된 룸 정보 : {}", room);
        assertThat(room).isNotNull();
    }

    @Test
    @DisplayName("룸 정보 변경")
    @Disabled
    @Transactional
    void editRoomTest() {
        //given
        Room room = Room.builder()
                .id(5)
                .headCount(10)
                .price(1000)
                .build();
        //when
        roomService.editRoom(room);
        //then
        log.info("룸 변경 정보 : {}", room);
        assertThat(room).isNotNull();
    }

    @Test
    @DisplayName("룸 전체 리스트 출력")
    @Disabled
    void getRoomList() {
        //given
        //when
        List<Room> list = roomService.getRoomList();
        //then
        log.info("룸 전체 리스트 검색 : {}", list);
        assertThat(list).isNotNull();
    }

    @Test
    @DisplayName("카페 id로 룸 리스트 검색")
    @Disabled
    void getSearchCafeRoomTest() {
        //given
        int id = 1;
        //when
        List<Room> list = roomService.getSearchCafeRoom(id);
        //then
        log.info("카페 id로 검색한 룸 리스트 : {}", list);
        assertThat(list).isNotNull();
    }

    @Test
    @DisplayName("룸 id로 룸 검색")
    @Disabled
    void getSearchRoomTest() {
        //given
        int id = 25;
        //when
        Room room = roomService.getSearchRoom(id);
        //then
        log.info("룸 id로 검색한 룸 리스트: {}", room);
        assertThat(room).isNotNull();
    }

    @Test
    @DisplayName("룸 삭제")
    @Disabled
    @Transactional
    void deleteRoomTest() {
        //given
        int id = 25;
        //when
        roomService.deleteRoom(id);
        //then
    }
}