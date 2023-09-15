package com.vj.lets.domain.room.mapper;//import static org.junit.jupiter.api.Assertions.*;

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
 * Room Mapper Test
 *
 * @author VJ특공대 강소영
 * @version 1.0
 * @since 2023-09-13 (수)
 */
@SpringBootTest
@Slf4j
class RoomMapperTest {

    @Autowired
    private RoomMapper roomMapper;

    @Test
    @DisplayName("룸 생성")
    @Disabled
    @Transactional
    void createTest() {
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
        roomMapper.create(room);
        //then
        log.info("생성된 룸 : {}", room);
        assertThat(room).isNotNull();
    }

    @Test
    @DisplayName("룸 정보 업데이트")
    @Disabled
    @Transactional
    void updateTest() {
        //given
        Room room = Room.builder()
                .id(5)
                .headCount(10)
                .price(1000)
                .build();
        //when
        roomMapper.update(room);
        //then
        log.info("수정된 룸 : {}", room);
        assertThat(room).isNotNull();
    }

    @Test
    @DisplayName("전체 룸 리스트 검색")
    @Disabled
    void findByAllTest() {
        //given
        //when
        List<Room> list = roomMapper.findByAll();
        //then
        log.info("전체 룸 리스트 : {}", list);
        assertThat(list).isNotNull();
    }

    @Test
    @DisplayName("카페 아이디로 룸 검색")
    @Disabled
    void findByCafeTest() {
        //given
        int id = 1;
        //when
        List<Room> list = roomMapper.findByCafe(id);
        //then
        log.info("카페 아이디로 검색한 룸 리스트 : {}", list);
        assertThat(list).isNotNull();
    }

    @Test
    @DisplayName("룸 아이디로 룸 조회")
    @Disabled
    void findByIdTest() {
        //given
        int id = 1;
        //when
        Room room = roomMapper.findById(id);
        //then
        log.info("룸 아이디로 룸 조회 : {}", room);
        assertThat(room).isNotNull();
    }

    @Test
    @DisplayName("룸 삭제")
    @Disabled
    @Transactional
    void delete() {
        //given
        int id = 25;
        //when
        roomMapper.delete(id);
        //then
    }
}