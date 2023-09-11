package com.vj.lets.domain.reservation.mapper;

import com.vj.lets.domain.reservation.dto.Reservation;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 예약 Mapper 테스트
 *
 * @author 박상훈
 * @version 1.0
 * @since 2023-09-10 (일)
 */
@SpringBootTest
@Slf4j
class ReservationMapperTest {

    @Autowired
    private ReservationMapper reservationMapper;


    @Test
    @DisplayName("예약하기 - 결제 전")
    @Transactional
    void createTest() {
        // given
        Reservation newReservation = Reservation.builder()
                .id(1)
                .bookingDate("2023-09-11")
                .startTime(11)
                .endTime(13)
                .headCount(4)
                .memberId(1)
                .cafeId(1)
                .roomId(3)
                .build();
        // when
        reservationMapper.create(newReservation);
        // then
        log.info("예약 완료 : {}",newReservation);
        assertThat(newReservation).isNotNull();
    }
}