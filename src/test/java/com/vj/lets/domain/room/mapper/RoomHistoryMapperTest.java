package com.vj.lets.domain.room.mapper;//import static org.junit.jupiter.api.Assertions.*;

import com.vj.lets.domain.room.dto.Room;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Room History Mapper Test
 *
 * @author VJ특공대 강소영
 * @version 1.0
 * @since 2023-09-13 (수)
 */
@SpringBootTest
@Slf4j
class RoomHistoryMapperTest {

    @Autowired
    private RoomHistoryMapper roomHistoryMapper;
    @Autowired
    private RoomMapper roomMapper;

    @Test
    @DisplayName("룸 히스토리 생성")
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
        roomHistoryMapper.create();
        //then
        log.info("등록된 룸 : {}", room);
        assertThat(room).isNotNull();

    }

    @Test
    @DisplayName("룸 히스토리 업데이트")
    @Disabled
    @Transactional
    void updateTest() {
        //given
        int id = 25;
        //when
        roomHistoryMapper.update(id);
        //then
    }

    @Test
    @DisplayName("룸 히스토리 삭제")
    @Disabled
    @Transactional
    void deleteTest() {
        //given
        int id = 25;
        //when
        roomHistoryMapper.delete(id);
        //then
    }
}